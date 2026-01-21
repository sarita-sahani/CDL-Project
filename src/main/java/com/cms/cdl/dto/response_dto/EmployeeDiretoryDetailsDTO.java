package com.cms.cdl.dto.response_dto;

import com.cms.cdl.model.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeDiretoryDetailsDTO {
    private String candId;
    private EONGeneral general;
    private EONPersonal personal;
    private EONFamily family;
    private EONBank bank;
    private EONCandidateDeclaration candidateDeclaration;
    private EONDeclQuestionnaire declQuestionnaire;
    private EONDocumentProof documentProof;
    private EONEducation education;
    private EONHRDeclaration hrDeclaration;
    private EONInterest interest;
    private EONLanguage language;
    private EONOverallStatus overallStatus;
    private EONPF pf;
    private EONProfessionalReference profReference;
    private EONProfressionalExperience profExp;
    private EONSkill skill;
    private EmployeeOnboardingInfo employeeOnboardingInfo;
}
