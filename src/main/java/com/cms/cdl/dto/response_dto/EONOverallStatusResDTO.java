package com.cms.cdl.dto.response_dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EONOverallStatusResDTO {
    private String candID;

    private Integer jobId;
    private Integer jobAppId;

    private String overallStatus;
    private String hrDeclStatus;
    private String remarks;

    private String genCS;
    private String genAS;

    private String persCS;
    private String persAS;

    private String eduCS;
    private String eduAS;

    private String skillCS;
    private String skillAS;

    private String familyCS;
    private String familyAS;

    private String bankCS;
    private String bankAS;

    private String pfCS;
    private String pfAS;

    private String langCS;
    private String langAS;

    private String intCS;
    private String intAS;

    private String profExpCS;
    private String profExpAS;

    private String refCS;
    private String refAS;

    private String docProofCS;
    private String docProofAS;

    private String quesCS;
    private String quesAS;

    private String candDeclCS;
    private String candDeclAS;

    private String ownerUserId;
    private String ownerUserOrg;
}
