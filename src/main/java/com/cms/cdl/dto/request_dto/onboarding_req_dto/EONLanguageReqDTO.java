package com.cms.cdl.dto.request_dto.onboarding_req_dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public record EONLanguageReqDTO(
        @JsonProperty("CandID") String candID,
        @JsonProperty("Lang1Name") String lang1Name,
        @JsonProperty("Lang1Level") String lang1Level,

        @JsonProperty("Lang2Name") String lang2Name,
        @JsonProperty("Lang2Level") String lang2Level,

        @JsonProperty("Lang3Name") String lang3Name,
        @JsonProperty("Lang3Level") String lang3Level,

        @JsonProperty("Lang4Name") String lang4Name,
        @JsonProperty("Lang4Level") String lang4Level,

        @JsonProperty("Lang5Name") String lang5Name,
        @JsonProperty("Lang5Level") String lang5Level,

        @JsonProperty("OWNER_USERID") String ownerUserId,
        @JsonProperty("OWNER_USERORG") String ownerUserOrg
) {
    public static EONLanguageReqDTO empty() {
        return new EONLanguageReqDTO(
                "", "", "", "", "", "", "", "", "", "", "", "",""
        );
    }
}
