package com.cms.cdl.service.display_service;

import com.cms.cdl.dto.ItDeclr.IT_Proof_InvestmentDTO;
import com.cms.cdl.dto.request_dto.display_dto.*;
import com.cms.cdl.dto.request_dto.onboarding_req_dto.EONOverallStatusReqDTO;
import com.cms.cdl.dto.response_dto.EONOverallStatusResDTO;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface DisplayService {
    public Mono<List<JobPostDTO>> fetchJobPostData(String intRefData);
    public Mono<List<TechPanelDTO>> fetchTechPanelData();
    public Mono<List<InterviewScheduleDTO>> fetchInterviewScheduleData(int jobAppId);
    public Mono<List<InterviewFeedbackDTO>> fetchInterviewFeedbackData(int jobAppNum);

    Mono<List<JobApplicationDTO>> fetchJobApplicationData(int jobId);


}
