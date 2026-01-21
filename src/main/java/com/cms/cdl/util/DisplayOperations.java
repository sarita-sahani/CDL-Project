
package com.cms.cdl.util;

import com.cms.cdl.dto.request_dto.display_dto.*;
import com.cms.cdl.dto.request_dto.onboarding_req_dto.EONDeclQnrReqDTO;
import com.cms.cdl.dto.request_dto.onboarding_req_dto.EONEducationReqDTO;
import com.cms.cdl.dto.request_dto.onboarding_req_dto.EONOverallStatusReqDTO;
import com.cms.cdl.dto.request_dto.wrapperDTO.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.stream.StreamSupport;

@Component
@Slf4j
public class DisplayOperations {
    @Value("${generate.session-id.API}")
    private String sessionIdAPI;
    @Value("${job.post.base-API}")
    private String jobPostBaseAPI;
    @Value("${tech.panel.base-API}")
    private String techPanelBaseAPI;
    @Value("${interview.schedule.base-API}")
    private String interviewScheduleBaseAPI;
    @Value("${interview.feedback.base-API}")
    private String interviewFeedbackBaseAPI;
    @Value("${job.application.base-API}")
    private  String jobApplicationBaseAPI;

    public String jobApplicationAPI = "&user_id=admin&page_name=list_jobappl_data";
    //public String jobApplicationAPI ="&user_id=admin&page_name=mod_jobappl_data&JOB_APP_ID=12";
    public String techPanelAPI = "&user_id=admin&page_name=list_ipanel_data&EmpID=_NA_";
    public String jobPostAPI = "&user_id=admin&page_name=mod_jobpost_data";
   // public String jobPostAPI = "&user_id=admin&page_name=mod_jobpost_data&INT_REF_DATA=1234";
    public String interviewScheduleAPI = "&user_id=admin&page_name=list_intview_data";
    public String interviewFeedbackAPI = "&user_id=admin&page_name=list_intfb_data";

    public Mono<String> generateSessionId() {
        return WebClient.create()
                .get()
                .uri(sessionIdAPI)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {})
                .flatMap(responseMap -> {
                    Object sessionIdObj = responseMap.get("session_id");
                    if (sessionIdObj != null) {
                        return Mono.just(sessionIdObj.toString());
                    } else {
                        System.err.println("Session ID not found in response: " + responseMap);
                        return Mono.error(new RuntimeException("Session ID not found in response"));
                    }
                });
    }

//    public Mono<String> generateSessionId() {
//        return WebClient.create()
//                .get()
//                .uri(sessionIdAPI)
//                .accept(MediaType.APPLICATION_JSON)
//                .retrieve()
//                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {
//                })
//                .map(responseMap -> responseMap.get("session_id").toString());
//    }


    public Mono<List<JobPostDTO>> fetchJobPostData(String intRefData) {
        return generateSessionId()
                .flatMap(sessionId -> {
                    // Construct URL
                    String url = jobPostBaseAPI + sessionId + jobPostAPI;
                    if (intRefData != null && !intRefData.isBlank() && !intRefData.equalsIgnoreCase("all")) {
                        url += "&INT_REF_DATA=" + intRefData;
                    }

                    return WebClient.create().get()
                            .uri(url)
                            .accept(MediaType.APPLICATION_JSON)
                            .retrieve()
                            .bodyToMono(JobPostWrapperDTO.class)
                            .map(wrapper -> {
                                Object rawData = wrapper.getRetdata();

                                // Configure the mapper to ignore unknown properties
                                ObjectMapper mapper = new ObjectMapper();
                                mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

                                if (rawData == null) {
                                    return Collections.emptyList();
                                }

                                if (rawData instanceof List) {
                                    return mapper.convertValue(rawData, new TypeReference<List<JobPostDTO>>() {});
                                } else {
                                    JobPostDTO singleJob = mapper.convertValue(rawData, JobPostDTO.class);
                                    return Collections.singletonList(singleJob);
                                }

                            });
                });
    }
//    public Mono<List<JobPostDTO>> fetchJobPostData(String intRefData) {
//        return generateSessionId()
//                .flatMap(sessionId -> WebClient.create().get()
//                        .uri(jobPostBaseAPI + sessionId + jobPostAPI + intRefData)
//                        .accept(MediaType.APPLICATION_JSON)
//                        .retrieve()
//                        .bodyToMono(JobPostWrapperDTO.class)
//                        .map(JobPostWrapperDTO::getRetdata)
//                );
//
//    }
    public Mono<List<TechPanelDTO>> fetchTechPanelData() {
        return generateSessionId()
                .flatMap(sessionId -> WebClient.create().get()
                        .uri(techPanelBaseAPI + sessionId + techPanelAPI)
                        .accept(MediaType.APPLICATION_JSON)
                        .retrieve()
                        .bodyToMono(TechPanelWrapperDTO.class)
                        .map(TechPanelWrapperDTO::getRetdata)
                );
    }


    public Mono<List<InterviewScheduleDTO>> fetchInterviewScheduleData(int jobAppId) {
        return generateSessionId()
                .flatMap(sessionId -> {
                    // Update with your actual external API URL structure
                    // Assuming it takes JOB_ID as a query param
                    String url = interviewScheduleBaseAPI + sessionId + interviewScheduleAPI + "&JobAppID=" + jobAppId;

                    return WebClient.create().get()
                            .uri(url)
                            .accept(MediaType.APPLICATION_JSON)
                            .retrieve()
                            .bodyToMono(InterviewScheduleWrapperDTO.class)
                            .map(wrapper -> {
                                Object rawData = wrapper.getRetdata();
                                ObjectMapper mapper = new ObjectMapper();
                                // Important: handle unknown fields as we did for JobPost
                                mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

                                if (rawData == null) return Collections.emptyList();

                                if (rawData instanceof List) {
                                    return mapper.convertValue(rawData, new TypeReference<List<InterviewScheduleDTO>>() {});
                                } else {
                                    InterviewScheduleDTO singleApp = mapper.convertValue(rawData, InterviewScheduleDTO.class);
                                    return Collections.singletonList(singleApp);
                                }
                            });
                });
    }


    public Mono<List<InterviewFeedbackDTO>> fetchInterviewFeedbackData(int jobAppNum) {
        return generateSessionId()
                .flatMap(sessionId -> {
                    // Update with your actual external API URL structure
                    // Assuming it takes JOB_ID as a query param
                    String url = interviewFeedbackBaseAPI + sessionId + interviewFeedbackAPI + "&JobAppNum=" + jobAppNum;

                    return WebClient.create().get()
                            .uri(url)
                            .accept(MediaType.APPLICATION_JSON)
                            .retrieve()
                            .bodyToMono(InterviewFeedbackWrapperDTO.class)
                            .map(wrapper -> {
                                Object rawData = wrapper.getRetdata();
                                ObjectMapper mapper = new ObjectMapper();
                                // Important: handle unknown fields as we did for JobPost
                                mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

                                if (rawData == null) return Collections.emptyList();

                                if (rawData instanceof List) {
                                    return mapper.convertValue(rawData, new TypeReference<List<InterviewFeedbackDTO>>() {});
                                } else {
                                    InterviewFeedbackDTO singleApp = mapper.convertValue(rawData, InterviewFeedbackDTO.class);
                                    return Collections.singletonList(singleApp);
                                }
                            });
                });
    }



    public Mono<List<JobApplicationDTO>> fetchJobApplicationData(int jobId) {
        return generateSessionId()
                .flatMap(sessionId -> {
                    // Update with your actual external API URL structure
                    // Assuming it takes JOB_ID as a query param
                    String url = jobApplicationBaseAPI + sessionId + jobApplicationAPI + "&JOB_ID=" + jobId;

                    return WebClient.create().get()
                            .uri(url)
                            .accept(MediaType.APPLICATION_JSON)
                            .retrieve()
                            .bodyToMono(JobApplicationWrapperDTO.class)
                            .map(wrapper -> {
                                Object rawData = wrapper.getRetdata();
                                ObjectMapper mapper = new ObjectMapper();
                                // Important: handle unknown fields as we did for JobPost
                                mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

                                if (rawData == null) return Collections.emptyList();

                                if (rawData instanceof List) {
                                    return mapper.convertValue(rawData, new TypeReference<List<JobApplicationDTO>>() {});
                                } else {
                                    JobApplicationDTO singleApp = mapper.convertValue(rawData, JobApplicationDTO.class);
                                    return Collections.singletonList(singleApp);
                                }
                            });
                });
    }



}
