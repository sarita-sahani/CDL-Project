package com.cms.cdl.dto.request_dto.onboarding_req_dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public record EONBankReqDTO(
        @JsonProperty("CandID") String candID,
        @JsonProperty("BankName") String bankName,
        @JsonProperty("Branch") String branch,
        @JsonProperty("AccountNum") String accountNum,
        @JsonProperty("IFSCCode") String ifscCode,
        @JsonProperty("OWNER_USERID") String ownerUserId,
        @JsonProperty("OWNER_USERORG") String ownerUserOrg
) {
    public static EONBankReqDTO empty() {
        return new EONBankReqDTO(
                "", "", "", "", "", "", ""
        );
    }
}
