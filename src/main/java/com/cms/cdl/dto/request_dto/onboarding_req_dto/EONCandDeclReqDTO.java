package com.cms.cdl.dto.request_dto.onboarding_req_dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
@JsonIgnoreProperties(ignoreUnknown = true)
public record EONCandDeclReqDTO(
        @JsonProperty("CandID") String candID,
        @JsonProperty("DeclDate") String declDate,
        @JsonProperty("DeclPlace") String declPlace,
        @JsonProperty("CandSignature")  String candSignature,
        @JsonProperty("OWNER_USERID")  String ownerUserId,
        @JsonProperty("OWNER_USERORG") String ownerUserOrg
) {
    public static EONCandDeclReqDTO empty() {
        return new EONCandDeclReqDTO(
                "", "", "", "", "", ""
        );
    }
}
