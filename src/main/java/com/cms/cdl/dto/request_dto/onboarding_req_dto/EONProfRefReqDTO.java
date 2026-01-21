package com.cms.cdl.dto.request_dto.onboarding_req_dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public record EONProfRefReqDTO(
        @JsonProperty("CandID") String candID,
        @JsonProperty("RefName1") String refName1,
        @JsonProperty("RefOrg1") String refOrg1,
        @JsonProperty("RefDesOcc1") String refDesOcc1,
        @JsonProperty("RefAddr1") String refAddr1,
        @JsonProperty("RefContactNum1") String refContactNum1,
        @JsonProperty("RefEmail1") String refEmail1,
        @JsonProperty("RefName2") String refName2,
        @JsonProperty("RefOrg2") String refOrg2,
        @JsonProperty("RefDesOcc2") String refDesOcc2,
        @JsonProperty("RefAddr2") String refAddr2,
        @JsonProperty("RefContactNum2") String refContactNum2,
        @JsonProperty("RefEmail2") String refEmail2,
        @JsonProperty("RefName3") String refName3,
        @JsonProperty("RefOrg3") String refOrg3,
        @JsonProperty("RefDesOcc3") String refDesOcc3,
        @JsonProperty("RefAddr3") String refAddr3,
        @JsonProperty("RefContactNum3") String refContactNum3,
        @JsonProperty("RefEmail3") String refEmail3,
        @JsonProperty("OWNER_USERID") String ownerUserId,
        @JsonProperty("OWNER_USERORG") String ownerUserOrg
) {
    public static EONProfRefReqDTO empty() {
        return new EONProfRefReqDTO(
                "", "", "", "", "", "", "", "", "",
                "", "", "", "", "", "", "", "",
                "", "", "",""

        );
    }
}
