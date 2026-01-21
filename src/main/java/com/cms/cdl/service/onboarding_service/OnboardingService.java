package com.cms.cdl.service.onboarding_service;

import com.cms.cdl.dto.request_dto.onboarding_req_dto.EONGeneralReqDTO;
import com.cms.cdl.dto.request_dto.onboarding_req_dto.EONPersonalReqDTO;
import com.cms.cdl.dto.response_dto.*;
import com.cms.cdl.model.*;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface OnboardingService {


       Mono<List<CandidateFullDetailsDTO>> fetchAllHRCompletedCandidatesDetails();


    Mono<List<GeneralListCandidatesData>> fetchAllHRCompletedGeneralCandidatesDetails();

    Mono<GeneralListCandidatesData> fetchHRCompletedGeneralCandidateDetails(String candId, String sessionId);

    Mono<CandidateFullDetailsDTO> fetchCandidateDetailsReactive(String candId, String sessionId);

    void saveOnBoardingCandidatesDetails(CandidateFullDetailsDTO dto) throws Exception;

    EONPersonal fetchEonPersonalData(String candId);
    EONBank fetchEonBankData(String candId);
    EONCandidateDeclaration fetchEonCandidateDeclarationData(String candId);
    EONDeclQuestionnaire fetchEonDeclQuestionnaireData(String candId);
    EONDocumentProof fetchEonDocumentProofData(String candId);
    EONEducation fetchEonEducationData(String candId);
    EONFamily fetchEonFamilyData(String candId);
    EONGeneral fetchEonGeneralData(String candId);
    EONHRDeclaration fetchEonHRDeclarationData(String candId);
    EONInterest fetchEonInterestData(String candId);
    EONLanguage fetchEonLanguageData(String candId);
    EONOverallStatus fetchEonOverallStatusData(String candId);
    EONPF fetchEonPFData(String candId);
    EONProfessionalReference fetchEonProfessionalReferenceData(String candId);
    EONProfressionalExperience fetchEonProfressionalExperienceData(String candId);
    EONSkill fetchEonSkillData(String candId);


    EmployeeOnboardingInfo saveEmployeeDataInOnboardingTable(EmployeeOnboardingInfo onboardingInfo);

    @Transactional // Ensures all saves happen together, or none do
    EmployeeDiretoryDetailsDTO saveCandidateDetails(String candId, EmployeeDiretoryDetailsDTO saveDto);

    List<EmployeeDiretoryDetailsDTO> getAllCandidates();

    EmployeeDiretoryDetailsDTO getCandidateDetailsById(String candId);

    EmployeeDiretoryDetailsDTO updateCandidateDetails(String candId, EmployeeDiretoryDetailsDTO updatedDto);

    EmployeeOnboardingInfo getCandidateByCandId(String candID);

    EmployeeOnboardingInfo updateCandidate(String candID, EmployeeOnboardingInfo updated);

    void deleteCandidate(String candID);

    boolean deactivateCandidate(String candID);

    // NEW: activateCandidate method
    @Transactional
    boolean activateCandidate(String candID);
}
