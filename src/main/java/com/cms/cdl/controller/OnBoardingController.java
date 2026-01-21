package com.cms.cdl.controller;

import com.cms.cdl.dto.employee_dto.EmpReqDTO;
import com.cms.cdl.dto.response_dto.EmployeeDiretoryDetailsDTO;
import com.cms.cdl.dto.user_dto.UserReqDTO;
import com.cms.cdl.dto.response_dto.CandidateFullDetailsDTO;
import com.cms.cdl.dto.response_dto.GeneralListCandidatesData;
import com.cms.cdl.model.*;
import com.cms.cdl.service.onboarding_service.OnboardingService;
import com.cms.cdl.util.OnBoardingOperationsCandidates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/onboarding")
@CrossOrigin("*")
public class OnBoardingController {

    @Autowired
    OnboardingService onboardingService;

    @Autowired
    OnBoardingOperationsCandidates onBoardingOperationsCandidates;

    //http://localhost:9034/onboarding/fetchAllHRCompletedGeneralCandidatesDetails

    @GetMapping("/fetchAllHRCompletedGeneralCandidatesDetails")
    public Mono<List<GeneralListCandidatesData>> fetchAllHRCompletedGeneralCandidatesDetails() {
        return onboardingService.fetchAllHRCompletedGeneralCandidatesDetails();
    }

    //http://localhost:9034/onboarding/allHRCompletedCandidates

    @GetMapping("/fetchAllHRCompletedCandidatesDetails")
    public Mono<List<CandidateFullDetailsDTO>> fetchAllHRCompletedCandidatesDetails() {
        return onboardingService.fetchAllHRCompletedCandidatesDetails();
    }

//save onboarding candidates details in cdl database
    @PostMapping("/saveOnBoardingCandidatesDetails")
    public ResponseEntity<String> saveOnBoardingCandidatesDetails(@RequestBody CandidateFullDetailsDTO dto) throws Exception {
        onboardingService.saveOnBoardingCandidatesDetails(dto);
        System.out.println("onboarding candidates data===="+dto);
        return ResponseEntity.ok("Candidate full details saved successfully.");
    }

    //fetch all candidates data from local database and display in employee directory page////
    @GetMapping("/get/eonPersonal/data/{candId}")
    public EONPersonal fetchEonPersonalData(@PathVariable String candId) {
        return onboardingService.fetchEonPersonalData(candId);
    }

    @GetMapping("/get/eonBank/data/{candId}")
    public EONBank fetchEonBankData(@PathVariable String candId) {
        return onboardingService.fetchEonBankData(candId);
    }

    @GetMapping("/get/eonCandidateDeclaration/data/{candId}")
    public EONCandidateDeclaration fetchEonCandidateDeclarationData(@PathVariable String candId) {
        return onboardingService.fetchEonCandidateDeclarationData(candId);
    }

    @GetMapping("/get/eonDeclQuestionnaire/data/{candId}")
    public EONDeclQuestionnaire fetchEonDeclQuestionnaireData(@PathVariable String candId) {
        return onboardingService.fetchEonDeclQuestionnaireData(candId);
    }
    @GetMapping("/get/eonDocumentProof/data/{candId}")
    public EONDocumentProof fetchEonDocumentProofData(@PathVariable String candId) {
        return onboardingService.fetchEonDocumentProofData(candId);
    }
    @GetMapping("/get/eonEducation/data/{candId}")
    public EONEducation fetchEonEducationData(@PathVariable String candId) {
        return onboardingService.fetchEonEducationData(candId);
    }
    @GetMapping("/get/eonFamily/data/{candId}")
    public EONFamily fetchEonFamilyData(@PathVariable String candId) {
        return onboardingService.fetchEonFamilyData(candId);
    }
    @GetMapping("/get/eonGeneral/data/{candId}")
    public EONGeneral fetchEonGeneralData(@PathVariable String candId) {
        return onboardingService.fetchEonGeneralData(candId);
    }
    @GetMapping("/get/eonHRDeclaration/data/{candId}")
    public EONHRDeclaration fetchEonHRDeclarationData(@PathVariable String candId) {
        return onboardingService.fetchEonHRDeclarationData(candId);
    }
    @GetMapping("/get/eonInterest/data/{candId}")
    public EONInterest fetchEonInterestData(@PathVariable String candId) {
        return onboardingService.fetchEonInterestData(candId);
    }
    @GetMapping("/get/eonLanguage/data/{candId}")
    public EONLanguage fetchEonLanguageData(@PathVariable String candId) {
        return onboardingService.fetchEonLanguageData(candId);
    }
    @GetMapping("/get/eonOverallStatus/data/{candId}")
    public EONOverallStatus fetchEonOverallStatusData(@PathVariable String candId) {
        return onboardingService.fetchEonOverallStatusData(candId);
    }
    @GetMapping("/get/eonPF/data/{candId}")
    public EONPF fetchEonPFData(@PathVariable String candId) {
        return onboardingService.fetchEonPFData(candId);
    }
    @GetMapping("/get/eonProfessionalReference/data/{candId}")
    public EONProfessionalReference fetchEonProfessionalReferenceData(@PathVariable String candId) {
        return onboardingService.fetchEonProfessionalReferenceData(candId);
    }
    @GetMapping("/get/eonProfressionalExperience/data/{candId}")
    public EONProfressionalExperience fetchEonProfressionalExperienceData(@PathVariable String candId) {
        return onboardingService.fetchEonProfressionalExperienceData(candId);
    }
    @GetMapping("/get/eonSkill/data/{candId}")
    public EONSkill fetchEonSkillData(@PathVariable String candId) {
        return onboardingService.fetchEonSkillData(candId);
    }

//end///

    //save employee input data in onboarding service///
    //http://localhost:9034/onboarding/saveEmployeeDataInOnboardingTable
    @PostMapping("/saveEmployeeDataInOnboardingTable1")
    public ResponseEntity<EmployeeOnboardingInfo> saveEmployeeDataInOnboardoingTable(@RequestBody EmployeeOnboardingInfo onboardingInfo) {
        EmployeeOnboardingInfo savedData = onboardingService.saveEmployeeDataInOnboardingTable(onboardingInfo);
        System.out.println("onboarding data===="+savedData);
        return ResponseEntity.ok(savedData);
    }

    @PostMapping("/saveEmployeeDataInOnboardingTable")
    public ResponseEntity<EmployeeDiretoryDetailsDTO> saveEmployeeDataInOnboardoingTable(@RequestBody EmployeeDiretoryDetailsDTO completeDto) {
        // Correctly calls the service method to handle all nested entities
        EmployeeDiretoryDetailsDTO savedData = onboardingService.saveCandidateDetails(completeDto.getCandId(), completeDto);

        System.out.println("onboarding data===="+savedData.getEmployeeOnboardingInfo());

        return ResponseEntity.ok(savedData);
    }

//http://localhost:9034/onboarding/update-overall-status
@CrossOrigin(origins = "*")
@PutMapping("/update-overall-status")
public Mono<String> updateOverallStatus(
        @RequestParam String candId,
        @RequestParam String jobId,
        @RequestParam String applicationId,
        @RequestParam String status) { // Accept the status from the frontend
    Integer jobIdInt = Integer.parseInt(jobId);
    Integer applicationIdInt = Integer.parseInt(applicationId);
    System.out.println("candid==" + candId +
            " aborted cand jobid==" + jobIdInt +
            " applicationid===" + applicationIdInt +
             " status===" + status);

    return onBoardingOperationsCandidates.updateOverallStatusOfSingleCandidate(
            status,
            candId,
            jobIdInt,
            applicationIdInt);
}

    @PostMapping("/saveEmpOnboardingDataInUserService")
    public Mono<ResponseEntity<String>> saveCandidatedatainuserService(@RequestBody UserReqDTO userDTO) {
        System.out.println("Received candidate user data: " + userDTO);
        System.out.println("Received candidate name: " + userDTO.getFirstName());

        return onBoardingOperationsCandidates.saveCandidatedatainuserService(userDTO)
                .map(response -> {
                    System.out.println("Mapped response: " + response);
                    return ResponseEntity.ok("Candidate saved successfully: " + response);
                })
                .onErrorResume(e -> {
                    e.printStackTrace();
                    return Mono.just(ResponseEntity.status(500).body("Error: " + e.getMessage()));
                });

    }

    @PostMapping("/saveEmpOnboardingDataInEmployeeService")
    public Mono<ResponseEntity<String>> saveEmpdataInEmployeeService(@RequestBody EmpReqDTO empReqDTO) {
        System.out.println("Received candidate user data: " + empReqDTO);
        System.out.println("Received candidate name: " + empReqDTO.getFirstName());

        return onBoardingOperationsCandidates.saveEmpdataInEmployeeService(empReqDTO)
                .map(response -> ResponseEntity.ok("Candidate saved successfully: " + response))
                .onErrorResume(e -> {
                    e.printStackTrace(); // For debugging
                    return Mono.just(ResponseEntity.status(500).body("Error: " + e.getMessage()));
                });
    }



//    @PostMapping("/save")
//    public ResponseEntity<String> saveUser(@RequestBody UserResDTO userDTO) {
//        onboardingService.saveCandidate(userDTO);
//        System.out.println("User Saved: " + userDTO.getFirstName());
//        return ResponseEntity.ok("User saved successfully!");
//    }

    //http://localhost:9034/onboarding/get/allCandidates
@GetMapping("/get/allCandidates")
public ResponseEntity<List<EmployeeDiretoryDetailsDTO>> getAllCandidates() {
    List<EmployeeDiretoryDetailsDTO> candidates = onboardingService.getAllCandidates();
    return ResponseEntity.ok(candidates);
}
//http://localhost:9034/onboarding/get/candidate/
    @GetMapping("/get/candidate/{candId}")
    public ResponseEntity<EmployeeDiretoryDetailsDTO> getCandidateDetailsById(@PathVariable String candId) {
        EmployeeDiretoryDetailsDTO candidate = onboardingService.getCandidateDetailsById(candId);
        if (candidate != null) {
            return ResponseEntity.ok(candidate);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // CORRECTED Controller
    @PutMapping("/updateEmployeeDataInOnboardingTable/{candidateId}")
    public ResponseEntity<EmployeeDiretoryDetailsDTO> updateCandidateDetails(
            @PathVariable String candidateId,
            @RequestBody EmployeeDiretoryDetailsDTO updatedDto) {
System.out.println("candiadtes id ====="+candidateId);
        // **REMOVE THE TRY/CATCH BLOCK TEMPORARILY**
        EmployeeDiretoryDetailsDTO result = onboardingService.updateCandidateDetails(candidateId, updatedDto);

        if (result != null) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
// Run this and check the console. The exception will show the precise error.

    // ✅ View one candidate
    @GetMapping("/viewCandidatesDetails/{candID}")
    public EmployeeOnboardingInfo getCandidate(@PathVariable String candID) {
        return onboardingService.getCandidateByCandId(candID);
    }

    // ✅ Edit candidate
    @PutMapping("/updateCandidatesDetails/{candID}")
    public EmployeeOnboardingInfo updateCandidate(@PathVariable String candID, @RequestBody EmployeeOnboardingInfo candidate) {
        return onboardingService.updateCandidate(candID, candidate);
    }

    // ✅ Delete candidate
    @DeleteMapping("/candidates/{candID}")
    public ResponseEntity<Void> deleteCandidate(@PathVariable String candID) {
        onboardingService.deleteCandidate(candID);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/candidates/{candID}/deactivate")
    public ResponseEntity<String> deactivateCandidate(@PathVariable String candID) {
        boolean updated = onboardingService.deactivateCandidate(candID);

        if (!updated) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok("Candidate marked as InActive successfully.");
    }

    // NEW: Endpoint for activating a candidate
    @PutMapping("/candidates/{candID}/activate")
    public ResponseEntity<String> activateCandidate(@PathVariable String candID) {
        boolean updated = onboardingService.activateCandidate(candID); // Call the new service method
        if (!updated) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("Candidate marked as Active successfully.");
    }


}
