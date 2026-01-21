package com.cms.cdl.util;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Component
public class ITRStatusOperations {
    @Value("${IT.proof.investment.status-API}")
    private String itProofInvestmentStatusBaseUrl;
    @Value("${IT.declaration.status-API}")
    private String itDeclarationStatusApi;

    private final WebClient webClient;

    @Autowired
    public ITRStatusOperations(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public Mono<String> getDeclarationSubmissionStatus(String empCode) {
        return webClient.get()
                .uri(itDeclarationStatusApi + empCode)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .map(json -> {
                    String data = json.path("data").asText(); // will return "Submitted" or something else
                    System.out.println("declaration data==="+json.path("data"));
                    String financialYear = getPreviousFinancialYear();
                    if ("Submitted".equalsIgnoreCase(data)) {
                        return "Submitted";
                    } else {
                        return "Not Submitted. Please fill your IT Declaration for financial year:" + financialYear;
                    }
                })
                .onErrorResume(ex -> {
                    System.out.println("Error: " + ex.getMessage());
                    return Mono.just("No data found. Please fill your IT Declaration for financial year current financial year");
                });
    }

    public Mono<String> getItInvestmentProofSubmissionStatus(String empCode) {
        return webClient.get()
                .uri(itProofInvestmentStatusBaseUrl + empCode)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .map(json -> {
                    String data = json.path("data").asText(); // will return "Submitted" or something else
                    String financialYear = getPreviousFinancialYear();
                    if ("Submitted".equalsIgnoreCase(data)) {
                        return "Submitted";
                    } else {
                        return "Not Submitted. Please fill your IT Investment proof submission for financial year:" + financialYear;
                    }
                })
                .onErrorResume(ex -> {
                    System.out.println("Error: " + ex.getMessage());
                    return Mono.just("No data found. Please fill your IT Investment proof submission for financial year current financial year");
                });
    }


    public String getPreviousFinancialYear() {
        LocalDate now = LocalDate.now();
        int currentYear = now.getMonthValue() >= 4 ? now.getYear() : now.getYear() - 1;
        //int currentYear=2024;
        int previousYear = currentYear - 1;
        return previousYear + "-" + String.valueOf(currentYear).substring(2); // e.g. 2024-25
    }

}
