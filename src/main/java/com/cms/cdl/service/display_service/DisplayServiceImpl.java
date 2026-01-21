package com.cms.cdl.service.display_service;

import com.cms.cdl.dto.request_dto.display_dto.*;
import com.cms.cdl.util.DisplayOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class DisplayServiceImpl implements DisplayService {
    @Autowired
    DisplayOperations displayOperations;

    @Override
    public Mono<List<JobPostDTO>> fetchJobPostData(String intRefData) {
        return displayOperations.fetchJobPostData(intRefData);
    }


    @Override
    public Mono<List<TechPanelDTO>> fetchTechPanelData() {
        return displayOperations.fetchTechPanelData();
    }

    @Override
    public Mono<List<InterviewScheduleDTO>> fetchInterviewScheduleData(int jobAppId) {
        return displayOperations.fetchInterviewScheduleData(jobAppId);
    }

    @Override
    public Mono<List<InterviewFeedbackDTO>> fetchInterviewFeedbackData(int jobAppNum) {
        return displayOperations.fetchInterviewFeedbackData(jobAppNum);
    }

    @Override
    public Mono<List<JobApplicationDTO>> fetchJobApplicationData(int jobId) {
        return displayOperations.fetchJobApplicationData(jobId);
    }







}