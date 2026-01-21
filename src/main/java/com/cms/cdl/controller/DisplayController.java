package com.cms.cdl.controller;

import com.cms.cdl.dto.request_dto.display_dto.*;
import com.cms.cdl.service.display_service.DisplayService;
import com.cms.cdl.util.DisplayOperations;
import com.cms.cdl.util.ITRStatusOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/display")
@CrossOrigin("*")
public class DisplayController {
    @Autowired
    DisplayService displayService;
    @Autowired
    DisplayOperations displayOperations;

    // http://localhost:9034/display/fetch/jobPost/data
//    @GetMapping("/fetch/jobPost/data")
//    public ResponseEntity<?> fetchJobPostData(){
//        return new ResponseEntity<>(displayService.fetchJobPostData(), HttpStatus.OK);
//    }
    @GetMapping("/fetch/jobPost/data/{intRefData}")
    public Mono<ResponseEntity<List<JobPostDTO>>> fetchJobPostData(@PathVariable(required = false) String intRefData) {
        return displayService.fetchJobPostData(intRefData)
                .map(list -> ResponseEntity.ok().body(list));
    }

    // http://localhost:9034/display/interview/feedback/data/{jobAppNum}
    @GetMapping("/interview/feedback/data/{jobAppNum}")
    public Mono<ResponseEntity<List<InterviewFeedbackDTO>>> fetchInterviewFeedbackData(@PathVariable int jobAppNum) {
        return displayService.fetchInterviewFeedbackData(jobAppNum)
                .map(list -> ResponseEntity.ok().body(list));
    }
    //http://localhost:9034/display/fetch/techPanel/data
    @GetMapping("/fetch/techPanel/data")
    public Mono<ResponseEntity<List<TechPanelDTO>>> fetchTechPanelData() {
        return displayService.fetchTechPanelData()
                .map(list -> ResponseEntity.ok().body(list));
    }

    //http://localhost:9034/display/fetch/interviewSchedule/data/{jobAppId}
    @GetMapping("/fetch/interviewSchedule/data/{jobAppId}")
    public Mono<ResponseEntity<List<InterviewScheduleDTO>>> fetchInterviewScheduleData(@PathVariable int jobAppId) {
        return displayService.fetchInterviewScheduleData(jobAppId)
                .map(list -> ResponseEntity.ok().body(list));
    }

    //http://localhost:9034/display/fetch/jobApplication/data
    @GetMapping("/fetch/jobApplication/data/{jobId}")
    public Mono<ResponseEntity<List<JobApplicationDTO>>> fetchJobApplicationData(@PathVariable int jobId) {
        return displayService.fetchJobApplicationData(jobId)
                .map(list -> ResponseEntity.ok().body(list));
    }

    //http://localhost:9034/display/getItInvestmentProofSubmissionStatus

    //to get emp information from employee service based on empId
//    @GetMapping("/getItInvestmentProofSubmissionStatus/{empCode}")
//    public ResponseEntity<IT_Proof_InvestmentDTO> getItInvestmentProofSubmissionStatus(@PathVariable String empCode)
//            throws ExecutionException, InterruptedException {
//        System.out.println("Enter empId: " + empCode);
//        IT_Proof_InvestmentDTO investmentproofstatus = displayService.getItInvestmentProofSubmissionStatus(empCode);
//        return ResponseEntity.ok(investmentproofstatus);
//    }


//    @GetMapping("/getItInvestmentProofSubmissionStatus/{empCode}")
//    public Mono<ResponseEntity<String>> getItInvestmentProofSubmissionStatus(@PathVariable String empCode) {
//        return displayService.getItInvestmentProofSubmissionStatus(empCode)
//                .map(ResponseEntity::ok)
//                .defaultIfEmpty(ResponseEntity.notFound().build());
//    }

    @Autowired
    private ITRStatusOperations itDeclarationOperations;

    @GetMapping("/it-declaration-status/{empCode}")
    public Mono<ResponseEntity<String>> getDeclarationSubmissionStatus(@PathVariable String empCode) {
        return itDeclarationOperations.getDeclarationSubmissionStatus(empCode)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/it-investment-proof-submission-status/{empCode}")
    public Mono<ResponseEntity<String>> getItInvestmentProofSubmissionStatus(@PathVariable String empCode) {
        return itDeclarationOperations.getItInvestmentProofSubmissionStatus(empCode)
                .map(ResponseEntity::ok);
    }
}
