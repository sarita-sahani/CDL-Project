package com.cms.cdl.dto.user_dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class StatutoryDetReqDTO extends BaseEntityReqDTO{
    private String panNumber;
    private String aadhaarNumber;
    private String nameAsPerPanCard;
    private String uan;
    private String pfNo;
    private String esicNo;
    private String fatherOrHusbandName;
    private String relationshipWithPerson;
    private String earlierMemberOfPF;
    private String internationalWorker;
    private String speciallyAbled;
    private String pfLinkedBankName;
    private String pfLinkedBankAccountNo;
    private String pfLinkedBankIfsc;
    private String lwdOfPreviousCompany;
}
