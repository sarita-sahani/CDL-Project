package com.cms.cdl.util;

import com.cms.cdl.dto.request_dto.onboarding_req_dto.*;
import com.cms.cdl.dto.request_dto.wrapperDTO.*;
import com.fasterxml.jackson.databind.ObjectMapper;
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
public class OnboardingOperations {

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


    public String eonPersonalAPI = "&user_id=admin&page_name=modpers_onboard_data&CandID=cid7&CandNum=7";

    public String eonGeneralAPI = "&user_id=admin&page_name=modgen_onboard_data&CandID=cid7";

    public String eonEducationAPI = "&user_id=admin&page_name=modedu_onboard_data&CandID=cid7";

    public String eonSkillAPI = "&user_id=admin&page_name=modskill_onboard_data&CandID=cid7&CandNum=_NA_";

    public String eonFamilyAPI = "&user_id=admin&page_name=modfam_onboard_data&CandID=cid7&CandNum=_NA_";
    public String eonBankAPI = "&user_id=admin&page_name=modbank_onboard_data&CandID=cid7&CandNum=_NA_";

    public String eonPfAPI = "&user_id=admin&page_name=modpf_onboard_data&CandID=cid7&CandNum=_NA_";
    public String eonLanguageAPI = "&user_id=admin&page_name=modlang_onboard_data&CandID=cid7&CandNum=_NA_";
    public String eonInterestAPI = "&user_id=admin&page_name=modint_onboard_data&CandID=cid7&CandNum=_NA_";
    public String eonProfExpAPI = "&user_id=admin&page_name=modexp_onboard_data&CandID=cid7&CandNum=_NA_";
    public String eonProfRefAPI = "&user_id=admin&page_name=modref_onboard_data&CandID=cid7&CandNum=_NA_";
    public String eonDocumentsAPI = "&user_id=admin&page_name=moddoc_onboard_data&CandID=cid7&CandNum=_NA_";
    public String eonQuestionnaireAPI = "&user_id=admin&page_name=moddoc_onboard_data&CandID=cid7&CandNum=_NA_";
    public String eonCandDeclAPI = "&user_id=admin&page_name=modcdecl_onboard_data&CandID=cid6&CandNum=_NA_";
    public String eonHrDeclAPI = "&user_id=admin&page_name=modhdecl_onboard_data&CandID=cid6&CandNum=_NA_";
    public String eonOverallStatusAPI = "&page_name=onbstatus_data_toc&CandID=cid7&CandNum=7";

    public String eonOverallStatusListAPI ="&user_id=admin&page_name=list_onb_data&CandID=_NA_&email_id=_NA_&JOB_ID=_NA_&JOB_APP_ID=_NA_";

    @Autowired
    private DisplayOperations displayOperations;

    //EON Personal data///
    public CompletableFuture<EONPersonalReqDTO> fetchAndSaveEonPersonalData() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                String sessionId = displayOperations.generateSessionId().block();  // blocking to get sessionId
                // Build full URI with sessionId and values from requestDTO
                String fullUri = onboardingPersonalBaseAPI + sessionId + eonPersonalAPI;

                // Synchronously retrieve the response from WebClient
                EONPersonalWrapperDTO wrapperDTO = WebClient.create()
                        .get()
                        .uri(fullUri)
                        .accept(MediaType.APPLICATION_JSON)
                        .retrieve()
                        .bodyToMono(EONPersonalWrapperDTO.class)
                        .block(); // Blocking here to wait for the response

                if (wrapperDTO != null) {
                    return wrapperDTO.getRetdata(); // Return the DTO
                } else {
                    throw new RuntimeException("No data found");
                }
            } catch (Exception e) {
                throw new RuntimeException("Failed to fetch data", e);
            }
        });
    }

    public Mono<EONGeneralReqDTO> fetchAndSaveEonGeneralData() {
        return displayOperations.generateSessionId()
                .flatMap(sessionId -> {
                    String fullUri = onboardingGeneralBaseAPI + sessionId + eonGeneralAPI  + "&CandNum=_NA_";
                    return WebClient.create()
                            .get()
                            .uri(fullUri)
                            .accept(MediaType.APPLICATION_JSON)
                            .retrieve()
                            .bodyToMono(EONGeneralWrapperDTO.class)
                            .map(EONGeneralWrapperDTO::getRetdata);
                });
    }

    public Mono<EONEducationReqDTO> fetchAndSaveEonEducationData() {
        return displayOperations.generateSessionId()
                .flatMap(sessionId -> {
                    String fullUri = onboardingEducationBaseAPI + sessionId + eonEducationAPI  + "&CandNum=_NA_";
                    return WebClient.create()
                            .get()
                            .uri(fullUri)
                            .accept(MediaType.APPLICATION_JSON)
                            .retrieve()
                            .bodyToMono(EONEducationWrapperDTO.class)
                            .map(EONEducationWrapperDTO::getRetdata);
                });
    }

    public Mono<EONSkillReqDTO> fetchAndSaveEonSkillData() {
        return displayOperations.generateSessionId()
                .flatMap(sessionId -> {
                    String fullUri = onboardingSkillBaseAPI + sessionId + eonSkillAPI;
                    return WebClient.create()
                            .get()
                            .uri(fullUri)
                            .accept(MediaType.APPLICATION_JSON)
                            .retrieve()
                            .bodyToMono(EONSkillWrapperDTO.class)
                            .map(EONSkillWrapperDTO::getRetdata);
                });
    }

    public Mono<EONFamilyReqDTO> fetchAndSaveEonFamilyData() {
        return displayOperations.generateSessionId()
                .flatMap(sessionId -> {
                    String fullUri = onboardingFamilyBaseAPI + sessionId + eonFamilyAPI;
                    return WebClient.create()
                            .get()
                            .uri(fullUri)
                            .accept(MediaType.APPLICATION_JSON)
                            .retrieve()
                            .bodyToMono(EONFamilyWrapperDTO.class)
                            .map(EONFamilyWrapperDTO::getRetdata);
                });
    }

    public Mono<EONBankReqDTO> fetchAndSaveEonBankData() {
        return displayOperations.generateSessionId()
                .flatMap(sessionId -> {
                    String fullUri = onboardingBankBaseAPI + sessionId + eonBankAPI;
                    return WebClient.create()
                            .get()
                            .uri(fullUri)
                            .accept(MediaType.APPLICATION_JSON)
                            .retrieve()
                            .bodyToMono(EONBankWrapperDTO.class)
                            .map(EONBankWrapperDTO::getRetdata);
                });
    }

    public Mono<EONPFReqDTO> fetchAndSaveEonPfData() {
        return displayOperations.generateSessionId()
                .flatMap(sessionId -> {
                    String fullUri = onboardingPfBaseAPI + sessionId + eonPfAPI;
                    return WebClient.create()
                            .get()
                            .uri(fullUri)
                            .accept(MediaType.APPLICATION_JSON)
                            .retrieve()
                            .bodyToMono(EONPfWrapperDTO.class)
                            .map(EONPfWrapperDTO::getRetdata);
                });
    }

    public Mono<EONLanguageReqDTO> fetchAndSaveEonLangData() {
        return displayOperations.generateSessionId()
                .flatMap(sessionId -> {
                    String fullUri = onboardingLanguageBaseAPI + sessionId + eonLanguageAPI;
                    return WebClient.create()
                            .get()
                            .uri(fullUri)
                            .accept(MediaType.APPLICATION_JSON)
                            .retrieve()
                            .bodyToMono(EONLangWrapperDTO.class)
                            .map(EONLangWrapperDTO::getRetdata);
                });
    }

    public Mono<EONInterestReqDTO> fetchAndSaveEonInterestData() {
        return displayOperations.generateSessionId()
                .flatMap(sessionId -> {
                    String fullUri = onboardingInterestBaseAPI + sessionId + eonInterestAPI;
                    return WebClient.create()
                            .get()
                            .uri(fullUri)
                            .accept(MediaType.APPLICATION_JSON)
                            .retrieve()
                            .bodyToMono(EONIntWrapperDTO.class)
                            .map(EONIntWrapperDTO::getRetdata);
                });
    }

    public Mono<EONProfExpReqDTO> fetchAndSaveEonProfExpData() {
        return displayOperations.generateSessionId()
                .flatMap(sessionId -> {
                    String fullUri = onboardingProfExpBaseAPI + sessionId + eonProfExpAPI;
                    return WebClient.create()
                            .get()
                            .uri(fullUri)
                            .accept(MediaType.APPLICATION_JSON)
                            .retrieve()
                            .bodyToMono(EONProfExpWrapperDTO.class)
                            .map(EONProfExpWrapperDTO::getRetdata);
                });
    }

    public Mono<EONProfRefReqDTO> fetchAndSaveEonProfRefData() {
        return displayOperations.generateSessionId()
                .flatMap(sessionId -> {
                    String fullUri = onboardingProfRefBaseAPI + sessionId + eonProfRefAPI;
                    return WebClient.create()
                            .get()
                            .uri(fullUri)
                            .accept(MediaType.APPLICATION_JSON)
                            .retrieve()
                            .bodyToMono(EONProfRefWrapperDTO.class)
                            .map(EONProfRefWrapperDTO::getRetdata);
                });
    }

    public CompletableFuture<EONDocsProofReqDTO> fetchAndSaveEonDocsProofData() {
//        return displayOperations.generateSessionId()
//                .flatMap(sessionId -> {
//                    String fullUri = onboardingDocumentsBaseAPI + sessionId + eonDocumentsAPI;
//                    return WebClient.create()
//                            .get()
//                            .uri(fullUri)
//                            .accept(MediaType.APPLICATION_JSON)
//                            .retrieve()
//                            .bodyToMono(EONDocsProofWrapperDTO.class)
//                            .map(EONDocsProofWrapperDTO::getRetdata);
//                });
//    }
        return CompletableFuture.supplyAsync(() -> {
            try {
                String sessionId = displayOperations.generateSessionId().block();  // blocking to get sessionId
                // Build full URI with sessionId and values from requestDTO
                String fullUri = onboardingDocumentsBaseAPI + sessionId + eonDocumentsAPI;

                // Synchronously retrieve the response from WebClient
                EONDocsProofWrapperDTO wrapperDTO = WebClient.create()
                        .get()
                        .uri(fullUri)
                        .accept(MediaType.APPLICATION_JSON)
                        .retrieve()
                        .bodyToMono(EONDocsProofWrapperDTO.class)
                        .block(); // Blocking here to wait for the response

                if (wrapperDTO != null) {
                    return wrapperDTO.getRetdata(); // Return the DTO
                } else {
                    throw new RuntimeException("No data found");
                }
            } catch (Exception e) {
                throw new RuntimeException("Failed to fetch data", e);
            }
        });
    }

    public Mono<EONDeclQnrReqDTO> fetchAndSaveEonDeclQnrData() {
        return displayOperations.generateSessionId()
                .flatMap(sessionId -> {
                    String fullUri = onboardingQuestionnaireBaseAPI + sessionId + eonQuestionnaireAPI;
                    return WebClient.create()
                            .get()
                            .uri(fullUri)
                            .accept(MediaType.APPLICATION_JSON)
                            .retrieve()
                            .bodyToMono(EONDeclQnrWrapperDTO.class)
                            .map(EONDeclQnrWrapperDTO::getRetdata);
                });
    }

    public Mono<EONCandDeclReqDTO> fetchAndSaveEonCandDeclarationData() {
        return displayOperations.generateSessionId()
                .flatMap(sessionId -> {
                    String fullUri = onboardingCandDeclBaseAPI + sessionId + eonCandDeclAPI;
                    return WebClient.create()
                            .get()
                            .uri(fullUri)
                            .accept(MediaType.APPLICATION_JSON)
                            .retrieve()
                            .bodyToMono(EONCandDeclWrapperDTO.class)
                            .map(EONCandDeclWrapperDTO::getRetdata);
                });
    }

    public Mono<EONHRDeclReqDTO> fetchAndSaveEonHRDeclarationData() {
        return displayOperations.generateSessionId()
                .flatMap(sessionId -> {
                    String fullUri = onboardingHrDeclBaseAPI + sessionId + eonHrDeclAPI;
                    return WebClient.create()
                            .get()
                            .uri(fullUri)
                            .accept(MediaType.APPLICATION_JSON)
                            .retrieve()
                            .bodyToMono(EONHrDeclWrapperDTO.class)
                            .map(EONHrDeclWrapperDTO::getRetdata);
                });
    }

//    public Mono<EONOverallStatusReqDTO> fetchAndSaveEonOverallStatusData() {
//        return displayOperations.generateSessionId()
//                .flatMap(sessionId -> {
//                    String fullUri = onboardingOverallStatusBaseAPI + sessionId + eonOverallStatusAPI;
//                    return WebClient.create()
//                            .get()
//                            .uri(fullUri)
//                            .accept(MediaType.APPLICATION_JSON)
//                            .retrieve()
//                            .bodyToMono(EONOverallStatusWrapperDTO.class)
//                            .map(EONOverallStatusWrapperDTO::getRetdata);
//                });
//    }

    public Mono<List<EONOverallStatusReqDTO>> overallStatusListData() {
        return displayOperations.generateSessionId()
                .flatMap(sessionId ->
                        WebClient.create().get()
                                .uri(onboardingOverallStatusListBaseAPI + sessionId + eonOverallStatusListAPI)
                                .accept(MediaType.APPLICATION_JSON)
                                .retrieve()
                                .bodyToMono(String.class)
                                .doOnNext(json -> System.out.println("RAW JSON = " + json))
                                .map(json -> {
                                    try {
                                        ObjectMapper objectMapper = new ObjectMapper();
                                        EONOverallStatusWrapperDTO dto = objectMapper.readValue(json, EONOverallStatusWrapperDTO.class);
                                        return dto.getRetdata().stream()
                                                .filter(d -> "HR-Completed".equalsIgnoreCase(d.getOverallStatus()))
                                                .collect(Collectors.toList());
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        return Collections.emptyList();
                                    }
                                })
                );
    }



}
