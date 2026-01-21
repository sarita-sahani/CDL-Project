package com.cms.cdl.dto.response_dto;

import com.cms.cdl.dto.request_dto.onboarding_req_dto.*;
import com.cms.cdl.model.EmployeeOnboardingInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidateFullDetailsDTO {
    private String candId;

    private EONOverallStatusReqDTO eonOverallStatusReqDTO;
    private EONGeneralReqDTO eonGeneralReqDTO;
    private EONPersonalReqDTO eonPersonalReqDTO;
    private EONEducationReqDTO eonEducationReqDTO;
    private EONSkillReqDTO eonSkillReqDTO;
    private EONFamilyReqDTO eonFamilyReqDTO;
    private EONBankReqDTO eonBankReqDTO;
    private EONPFReqDTO eonpfReqDTO;
    private EONLanguageReqDTO eonLanguageReqDTO;
    private EONInterestReqDTO eonInterestReqDTO;
    private EONProfExpReqDTO eonProfExpReqDTO;
    private EONProfRefReqDTO eonProfRefReqDTO;
    private EONDocsProofReqDTO eonDocsProofReqDTO;
    private EONDeclQnrReqDTO eonDeclQnrReqDTO;
    private EONCandDeclReqDTO eonCandDeclReqDTO;
    private EONHRDeclReqDTO eonhrDeclReqDTO;
    private EmployeeOnboardingInfo employeeOnboardingInfo;


}

