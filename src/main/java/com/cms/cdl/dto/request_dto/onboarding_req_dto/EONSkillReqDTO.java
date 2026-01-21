package com.cms.cdl.dto.request_dto.onboarding_req_dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public record EONSkillReqDTO(
        @JsonProperty("CandID") String candID,
        @JsonProperty("PSkill1Name") String primarySkill1Name,
        @JsonProperty("PSkill1Level") String primarySkill1Level,
        @JsonProperty("PSkill2Name") String primarySkill2Name,
        @JsonProperty("PSkill2Level") String primarySkill2Level,
        @JsonProperty("PSkill3Name") String primarySkill3Name,
        @JsonProperty("PSkill3Level") String primarySkill3Level,
        @JsonProperty("SSkill1Name") String secondarySkill1Name,
        @JsonProperty("SSkill1Level") String secondarySkill1Level,
        @JsonProperty("SSkill2Name") String secondarySkill2Name,
        @JsonProperty("SSkill2Level") String secondarySkill2Level,
        @JsonProperty("SSkill3Name") String secondarySkill3Name,
        @JsonProperty("SSkill3Level") String secondarySkill3Level,
        @JsonProperty("OWNER_USERID") String ownerUserId,
        @JsonProperty("OWNER_USERORG") String ownerUserOrg
) {
    public static EONSkillReqDTO empty() {
        return new EONSkillReqDTO(
                "", "", "", "", "", "", "",
                "", "", "", "", "", "", "", ""
        );
    }

}
