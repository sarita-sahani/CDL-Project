package com.cms.cdl.util;

import com.cms.cdl.dto.employee_dto.EmpReqDTO;
import com.cms.cdl.dto.user_dto.UserReqDTO;
import com.cms.cdl.dto.request_dto.onboarding_req_dto.*;
import com.cms.cdl.dto.request_dto.wrapperDTO.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Component
public class OnBoardingOperationsCandidates {

    @Value("${generate.session-id.API}")
    private String sessionIdAPI;
    @Value("${onboarding.personal.base-API}")
    private String onboardingPersonalBaseAPI;

    @Value("${onboarding.general.base-API}")
    private String onboardingGeneralBaseAPI;

    @Value("${onboarding.education.base-API}")
    private String onboardingEducationBaseAPI;

    @Value("${onboarding.skill.base-API}")
    private String onboardingSkillBaseAPI;
    @Value("${onboarding.family.base-API}")
    private String onboardingFamilyBaseAPI;

    @Value("${onboarding.bank.base-API}")
    private String onboardingBankBaseAPI;
    @Value("${onboarding.pf.base-API}")
    private String onboardingPfBaseAPI;
    @Value("${onboarding.language.base-API}")
    private String onboardingLanguageBaseAPI;
    @Value("${onboarding.interest.base-API}")
    private String onboardingInterestBaseAPI;
    @Value("${onboarding.prof-exp.base-API}")
    private String onboardingProfExpBaseAPI;
    @Value("${onboarding.prof-ref.base-API}")
    private String onboardingProfRefBaseAPI;
    @Value("${onboarding.documents.base-API}")
    private String onboardingDocumentsBaseAPI;
    @Value("${onboarding.questionnaire.base-API}")
    private String onboardingQuestionnaireBaseAPI;
    @Value("${onboarding.candidate-declaration.base-API}")
    private String onboardingCandDeclBaseAPI;
    @Value("${onboarding.hr-declaration.base-API}")
    private String onboardingHrDeclBaseAPI;
    @Value("${onboarding.overall-status.base-API}")
    private String onboardingOverallStatusBaseAPI;

    @Value("${onboarding.overall-status-list.base-API}")
    private String onboardingOverallStatusListBaseAPI;

    @Value("${abort.employee.base-API}")
    private String abortEmployeeBaseAPI;

    @Value("${user.onboarding.add-API}")
    private String addOnboardingDatainUserTable;

    @Value("${employee.onboarding.add-API}")
    private String addOnboardingDatainEmpTable;


    @Autowired
    private DisplayOperations displayOperations;

    public String eonOverallStatusAPI = "&page_name=onbstatus_data_toc&CandID=cid7&CandNum=7";
    public String eonOverallStatusListAPI = "&user_id=admin&page_name=list_onb_data&CandID=_NA_&email_id=_NA_&JOB_ID=_NA_&JOB_APP_ID=_NA_";

    //public String abortEmployeeAPI = "&user_id=admin&page_name=onbstatus_data_toc&CandID=";
    public String abortEmployeeAPI = "&user_id=admin&CandID=";

    private final WebClient webClient;

    @Autowired
    public OnBoardingOperationsCandidates(WebClient webClient) {
        this.webClient = webClient;
    }

    public <T> Mono<T> fetchFromRetdata(String url, Class<T> clazz, T emptyInstance) {
        return webClient.get()
                .uri(url)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class)
                .flatMap(response -> {
                    try {
                        if (response == null || response.contains("There is no result")) {
                            return Mono.just(emptyInstance);
                        }

                        ObjectMapper mapper = new ObjectMapper();
                        JsonNode retdata = mapper.readTree(response).path("retdata");

                        if (retdata.isMissingNode() || retdata.isNull()) {
                            return Mono.just(emptyInstance);
                        }

                        T dto = retdata.isObject()
                                ? mapper.treeToValue(retdata, clazz)
                                : retdata.isArray() && retdata.size() > 0
                                ? mapper.treeToValue(retdata.get(0), clazz)
                                : emptyInstance;

                        return Mono.just(dto);
                    } catch (Exception e) {
                        return Mono.just(emptyInstance);
                    }
                })
                .onErrorResume(ex -> Mono.just(emptyInstance));
    }


    public CompletableFuture<EONPersonalReqDTO> fetchEonPersonalData(String candId, String sessionId) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                String fullUri = onboardingPersonalBaseAPI + sessionId +
                        "&user_id=admin&page_name=modpers_onboard_data&CandID=" + candId + "&CandNum=_NA_";

                String rawResponse = webClient.get()
                        .uri(fullUri)
                        .accept(MediaType.APPLICATION_JSON)
                        .retrieve()
                        .bodyToMono(String.class)
                        .block();

                if (rawResponse == null || rawResponse.contains("There is no result")) {
                    return EONPersonalReqDTO.empty();
                }

                ObjectMapper mapper = new ObjectMapper();
                JsonNode retdataNode = mapper.readTree(rawResponse).path("retdata");

                if (retdataNode.isMissingNode() || retdataNode.isNull() ||
                        (retdataNode.isTextual() && retdataNode.asText().contains("no result"))) {
                    return EONPersonalReqDTO.empty();
                }

                return retdataNode.isObject()
                        ? mapper.treeToValue(retdataNode, EONPersonalReqDTO.class)
                        : retdataNode.isArray() && retdataNode.size() > 0
                        ? mapper.treeToValue(retdataNode.get(0), EONPersonalReqDTO.class)
                        : EONPersonalReqDTO.empty();

            } catch (Exception e) {
                System.err.println("Error fetching personal data for " + candId + ": " + e.getMessage());
                return EONPersonalReqDTO.empty();
            }
        });
    }


    public Mono<EONGeneralReqDTO> fetchEonGeneralData(String candId, String sessionId) {

        String fullUri = onboardingGeneralBaseAPI + sessionId +
                "&user_id=admin&page_name=modgen_onboard_data&CandID=" + candId + "&CandNum=_NA_";

        return fetchFromRetdata(fullUri, EONGeneralReqDTO.class, EONGeneralReqDTO.empty());
    }

    public Mono<EONEducationReqDTO> fetchEonEducationData(String candId, String sessionId) {
        String fullUri = onboardingEducationBaseAPI + sessionId +
                "&user_id=admin&page_name=modedu_onboard_data&CandID=" + candId + "&CandNum=_NA_";

        return fetchFromRetdata(fullUri, EONEducationReqDTO.class, EONEducationReqDTO.empty());

    }


    public Mono<EONSkillReqDTO> fetchEonSkillData(String candId, String sessionId) {
        String fullUri = onboardingSkillBaseAPI + sessionId + "&user_id=admin&page_name=modskill_onboard_data&CandID=" + candId + "&CandNum=_NA_";
        return fetchFromRetdata(fullUri, EONSkillReqDTO.class, EONSkillReqDTO.empty());
    }

    public Mono<EONFamilyReqDTO> fetchEonFamilyData(String candId, String sessionId) {

        String fullUri = onboardingFamilyBaseAPI + sessionId + "&user_id=admin&page_name=modfam_onboard_data&CandID=" + candId + "&CandNum=_NA_";
        return fetchFromRetdata(fullUri, EONFamilyReqDTO.class, EONFamilyReqDTO.empty());

    }

    public Mono<EONBankReqDTO> fetchEonBankData(String candId, String sessionId) {
        String fullUri = onboardingBankBaseAPI + sessionId + "&user_id=admin&page_name=modbank_onboard_data&CandID=" + candId + "&CandNum=_NA_";
        return fetchFromRetdata(fullUri, EONBankReqDTO.class, EONBankReqDTO.empty());


    }

    public Mono<EONPFReqDTO> fetchEonPfData(String candId, String sessionId) {
        String fullUri = onboardingPfBaseAPI + sessionId + "&user_id=admin&page_name=modpf_onboard_data&CandID=" + candId + "&CandNum=_NA_";
        return fetchFromRetdata(fullUri, EONPFReqDTO.class, EONPFReqDTO.empty());

    }

    public Mono<EONLanguageReqDTO> fetchEonLangData(String candId, String sessionId) {
        String fullUri = onboardingLanguageBaseAPI + sessionId + "&user_id=admin&page_name=modlang_onboard_data&CandID=" + candId + "&CandNum=_NA_";
        return fetchFromRetdata(fullUri, EONLanguageReqDTO.class, EONLanguageReqDTO.empty());

    }

    public Mono<EONInterestReqDTO> fetchEonInterestData(String candId, String sessionId) {
        String fullUri = onboardingInterestBaseAPI + sessionId + "&user_id=admin&page_name=modint_onboard_data&CandID=" + candId + "&CandNum=_NA_";
        return fetchFromRetdata(fullUri, EONInterestReqDTO.class, EONInterestReqDTO.empty());


    }

    public Mono<EONProfExpReqDTO> fetchEonProfExpData(String candId, String sessionId) {
        String fullUri = onboardingProfExpBaseAPI + sessionId + "&user_id=admin&page_name=modexp_onboard_data&CandID=" + candId + "&CandNum=_NA_";
        return fetchFromRetdata(fullUri, EONProfExpReqDTO.class, EONProfExpReqDTO.empty());


    }

    public Mono<EONProfRefReqDTO> fetchEonProfRefData(String candId, String sessionId) {
        String fullUri = onboardingProfRefBaseAPI + sessionId + "&user_id=admin&page_name=modref_onboard_data&CandID=" + candId + "&CandNum=_NA_";
        return fetchFromRetdata(fullUri, EONProfRefReqDTO.class, EONProfRefReqDTO.empty());

    }

    public CompletableFuture<EONDocsProofReqDTO> fetchEonDocsProofData(String candId, String sessionId) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                String fullUri = onboardingDocumentsBaseAPI + sessionId
                        + "&user_id=admin&page_name=moddoc_onboard_data&CandID=" + candId + "&CandNum=_NA_";

//                return webClient.get()
//                        .uri(fullUri)
//                        .accept(MediaType.APPLICATION_JSON)
//                        .retrieve()
//                        .bodyToMono(EONDocsProofWrapperDTO.class)
//                        .map(EONDocsProofWrapperDTO::getRetdata)
//                        .onErrorResume(ex -> {
//                            System.err.println("Failed to fetch document proof data for " + candId + ": " + ex.getMessage());
//                            return Mono.just(EONDocsProofReqDTO.empty());
//                        })
//                        .block(); // block to get result inside sync CompletableFuture

                return webClient.get()
                        .uri(fullUri)
                        .accept(MediaType.APPLICATION_JSON)
                        .exchangeToMono(clientResponse -> {
                            if (clientResponse.statusCode().is2xxSuccessful()) {
                                return clientResponse.bodyToMono(String.class).flatMap(body -> {
                                    if (body != null && body.contains("There is no result to return")) {
                                        System.out.println("No data returned for " + candId);
                                        return Mono.just(EONDocsProofReqDTO.empty());
                                    }
                                    try {
                                        EONDocsProofWrapperDTO wrapper = new ObjectMapper().readValue(body, EONDocsProofWrapperDTO.class);
                                        return Mono.just(wrapper.getRetdata());
                                    } catch (Exception e) {
                                        System.err.println("JSON parsing failed for " + candId + ": " + e.getMessage());
                                        return Mono.just(EONDocsProofReqDTO.empty());
                                    }
                                });
                            } else {
                                return Mono.just(EONDocsProofReqDTO.empty());
                            }
                        })
                        .onErrorResume(ex -> {
                            System.err.println("Error during call for " + candId + ": " + ex.getMessage());
                            return Mono.just(EONDocsProofReqDTO.empty());
                        })
                        .block();

            } catch (Exception e) {
                throw new RuntimeException("Failed to fetch data", e);
            }
        });
    }


    public Mono<EONDeclQnrReqDTO> fetchEonDeclQnrData(String candId, String sessionId) {
        String fullUri = onboardingQuestionnaireBaseAPI + sessionId + "&user_id=admin&page_name=moddoc_onboard_data&CandID=" + candId + "&CandNum=_NA_";
        return fetchFromRetdata(fullUri, EONDeclQnrReqDTO.class, EONDeclQnrReqDTO.empty());


    }

    public Mono<EONCandDeclReqDTO> fetchEonCandDeclarationData(String candId, String sessionId) {
        String fullUri = onboardingCandDeclBaseAPI + sessionId +
                "&user_id=admin&page_name=modcdecl_onboard_data&CandID=" + candId + "&CandNum=_NA_";

        return fetchFromRetdata(fullUri, EONCandDeclReqDTO.class, EONCandDeclReqDTO.empty());

    }


    public Mono<EONHRDeclReqDTO> fetchEonHRDeclarationData(String candId, String sessionId) {
        String fullUri = onboardingHrDeclBaseAPI + sessionId + "&user_id=admin&page_name=modhdecl_onboard_data&CandID=" + candId + "&CandNum=_NA_";
        return fetchFromRetdata(fullUri, EONHRDeclReqDTO.class, EONHRDeclReqDTO.empty());

    }

    public Mono<Tuple2<String, List<String>>> overallHRCompletedCandIds() {
        return displayOperations.generateSessionId()
                .flatMap(sessionId ->
                        WebClient.create().get()
                                .uri(onboardingOverallStatusListBaseAPI + sessionId + eonOverallStatusListAPI)
                                .accept(MediaType.APPLICATION_JSON)
                                .retrieve()
                                .bodyToMono(String.class)
                                .doOnNext(json -> System.out.println("RAW JSON = " + json))
                                .doOnError(err -> System.err.println("Error fetching candidate IDs: " + err.getMessage()))
                                .map(json -> {
                                    try {
                                        ObjectMapper objectMapper = new ObjectMapper();
                                        EONOverallStatusWrapperDTO dto = objectMapper.readValue(json, EONOverallStatusWrapperDTO.class);
                                        List<String> candIds = dto.getRetdata().stream()
                                                .filter(d -> "HR-Completed".equalsIgnoreCase(d.getOverallStatus()))
                                                .map(EONOverallStatusReqDTO::getCandID)
                                                .collect(Collectors.toList());
                                        return Tuples.of(sessionId, candIds);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        return Tuples.of(sessionId, Collections.<String>emptyList());
                                    }
                                })
                );
    }
    public Mono<EONOverallStatusReqDTO> overallStatusDataOfSingleCandidates(String candId, String sessionId) {
        String fullUri = onboardingOverallStatusBaseAPI + sessionId +
                "&page_name=onbstatus_data_toc&CandID=" + candId + "&CandNum=_NA_";

        return WebClient.create().get()
                .uri(fullUri)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(JsonNode.class)  // ← Use JsonNode for flexibility
                .flatMap(jsonNode -> {
                    JsonNode retdataNode = jsonNode.get("retdata");

                    ObjectMapper mapper = new ObjectMapper();
                    EONOverallStatusReqDTO result;

                    try {
                        if (retdataNode != null && retdataNode.isObject()) {
                            result = mapper.treeToValue(retdataNode, EONOverallStatusReqDTO.class);
                        } else if (retdataNode != null && retdataNode.isArray()) {
                            for (JsonNode item : retdataNode) {
                                if (item.has("CandID") && candId.equalsIgnoreCase(item.get("CandID").asText())) {
                                    result = mapper.treeToValue(item, EONOverallStatusReqDTO.class);
                                    return Mono.just(result);
                                }
                            }
                            result = EONOverallStatusReqDTO.empty();
                        } else {
                            result = EONOverallStatusReqDTO.empty();
                        }
                    } catch (Exception e) {
                        System.err.println("Deserialization error: " + e.getMessage());
                        result = EONOverallStatusReqDTO.empty();
                    }

                    return Mono.just(result);
                })
                .onErrorResume(ex -> {
                    System.err.println("Error fetching overall status for " + candId + ": " + ex.getMessage());
                    return Mono.just(EONOverallStatusReqDTO.empty());
                });
    }


    public Mono<String> updateOverallStatusOfSingleCandidate(String status, String candId, Integer jobId, Integer applicationId) {
        return displayOperations.generateSessionId()
                .flatMap(sessionId -> {
                    String fullUri = abortEmployeeBaseAPI
                            + sessionId
                            + abortEmployeeAPI
                            + candId
//                            + "&JOB_ID=" + jobId
//                            + "&JOB_APP_ID=" + applicationId
                            +"&OverallStatus=" + status;

                    System.out.println("Calling API: " + fullUri);

                    return WebClient.create()
                            .get()
                            .uri(fullUri)
                            .accept(MediaType.APPLICATION_JSON)
                            .retrieve()
                            .bodyToMono(JsonNode.class)
                            .flatMap(jsonNode -> {
                                // After successfully calling the external API,
                                // return the status you want to show the user.
                                return Mono.just("Employee status has been updated to: " + status);
                            })
                            .onErrorResume(ex -> {
                                System.err.println("Error updating overall status for " + candId + ": " + ex.getMessage());
                                return Mono.just("Error: " + ex.getMessage());
                            });
                });
    }

    public Mono<String> saveCandidatedatainuserService(UserReqDTO userDTO) {
        return webClient.post()
                .uri(addOnboardingDatainUserTable)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(userDTO)
                .exchangeToMono(response -> {
                    System.out.println("User Service HTTP Status: " + response.statusCode());
                    return response.bodyToMono(String.class)
                            .defaultIfEmpty("<<EMPTY BODY>>");
                })
                .doOnNext(res -> System.out.println("Response from User Service: " + res))
                .doOnError(err -> System.err.println("Error calling User Service: " + err.getMessage()));

    }

    public Mono<String> saveEmpdataInEmployeeService(EmpReqDTO empReqDTO) {
        return webClient.post()
                .uri(addOnboardingDatainEmpTable) // use value from application.yml
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(empReqDTO)
                .retrieve()
                .bodyToMono(String.class)
                .doOnNext(res -> System.out.println("Response from User Service: " + res))
                .doOnError(err -> System.err.println("Error calling User Service: " + err.getMessage()));
    }



}


