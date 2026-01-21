package com.cms.cdl.dto.request_dto.onboarding_req_dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public record EONInterestReqDTO(
        @JsonProperty("CandID") String candID,
        @JsonProperty("Activity1") String activity1,
        @JsonProperty("ProfMem1") String profMem1,
        @JsonProperty("AffNGO1") String affNGO1,

        @JsonProperty("Activity2") String activity2,
        @JsonProperty("ProfMem2") String profMem2,
        @JsonProperty("AffNGO2") String affNGO2,

        @JsonProperty("Activity3") String activity3,
        @JsonProperty("ProfMem3") String profMem3,
        @JsonProperty("AffNGO3") String affNGO3,

        @JsonProperty("OWNER_USERID") String ownerUserId,
        @JsonProperty("OWNER_USERORG") String ownerUserOrg
) {
    public static EONInterestReqDTO empty() {
        return new EONInterestReqDTO(
                "", "", "", "", "", "", "", "", "", "", "", ""
        );
    }
}
