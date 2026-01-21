package com.cms.cdl.util;

import com.cms.cdl.dto.document_dto.DocumentDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.InputStream;
import java.net.URL;
import java.util.List;
import org.springframework.http.HttpStatusCode;
import reactor.core.publisher.Mono;

@Component
public class UrlToMultipartFileConvert {

    @Value("${eon.base.API}")
    private String eonBaseAPI;
    @Value("${doc.upload.API}")
    private String dmsUrl;
    @Value("${doc.onboarding-Directory}")
    private String onboardingDirectory;

    public static ByteArrayResource convertUrlToResource(String fileUrl, String fileName) throws Exception {

        // Use WebClient to fetch the file bytes
        byte[] fileBytes = WebClient.create()
                .get()
                .uri(fileUrl)
                // You can add headers here if the resource server requires them (e.g., .header("Authorization", "Bearer token"))
                .retrieve()
                .onStatus(HttpStatusCode::isError, response ->
                        response.bodyToMono(String.class).flatMap(body ->
                                Mono.error(new Exception("Failed to download file from " + fileUrl + ". HTTP Status: " + response.statusCode() + ". Body: " + body))
                        )
                )
                .bodyToMono(byte[].class)
                .block(); // Blocking is acceptable in this utility method

        if (fileBytes == null) {
            throw new Exception("File download returned null bytes from URL: " + fileUrl);
        }

        // Return the ByteArrayResource
        return new ByteArrayResource(fileBytes) {
            @Override
            public String getFilename() {
                return fileName;
            }
        };
    }

//    public static ByteArrayResource convertUrlToResource(String fileUrl, String fileName) throws Exception {
//        URL url = new URL(fileUrl);
//        byte[] fileBytes;
//
//        try (InputStream inputStream = url.openStream()) {
//            fileBytes = inputStream.readAllBytes();
//        }
//
//        return new ByteArrayResource(fileBytes) {
//            @Override
//            public String getFilename() {
//                return fileName;
//            }
//        };
//    }

    public List<DocumentDTO> handleFileForwarding(String fileUrl, String fileName, String candId) throws Exception {
//        String baseU = "http://43.205.24.208:5000";
//        String dmsUrl = "http://localhost:9091/documents/upload/";
//        String fileName = "app_12_normal Resume.docx";

        //String docURL = eonBaseAPI+fileUrl;
        // Use the potentially corrected docURL construction
        String docURL = eonBaseAPI + fileUrl;

        // Make sure to eliminate any double slashes here if necessary
        docURL = docURL.replace("//", "/").replace("http:/", "http://").replace("https:/", "https://");



////add filename from response, empcode and other data
        ByteArrayResource fileResource = convertUrlToResource(docURL, fileName);

        // Step 2: Build DocumentDTO JSON string
        DocumentDTO documentDTO = new DocumentDTO();
        documentDTO.setEmpCode(candId);
        documentDTO.setEmpOrg("");
        documentDTO.setLocation("");

        String documentDTOJson = new ObjectMapper().writeValueAsString(documentDTO);

        MultipartBodyBuilder builder = new MultipartBodyBuilder();

        // Add the file
        builder.part("files", fileResource).filename(fileName).contentType(MediaType.APPLICATION_OCTET_STREAM);

        // Add JSON DTO as a string part
        builder.part("documentDTO", documentDTOJson).contentType(MediaType.APPLICATION_JSON);

        ResponseEntity<List<DocumentDTO>> documentDTOResponseEntity = WebClient.create().post()
                .uri(dmsUrl + onboardingDirectory)
                .body(BodyInserters.fromMultipartData(builder.build()))
                .retrieve()
                .toEntity(new ParameterizedTypeReference<List<DocumentDTO>>() {
                })
                .block();
        if (documentDTOResponseEntity != null && documentDTOResponseEntity.hasBody()) {
            return documentDTOResponseEntity.getBody();
        } else {
            return null;
        }
    }
}