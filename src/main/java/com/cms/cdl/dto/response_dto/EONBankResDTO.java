package com.cms.cdl.dto.response_dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EONBankResDTO {
    private String candID;

    private String bankName;
    private String branch;
    private String accountNum;
    private String ifscCode;

    private String ownerUserId;
    private String ownerUserOrg;
}
