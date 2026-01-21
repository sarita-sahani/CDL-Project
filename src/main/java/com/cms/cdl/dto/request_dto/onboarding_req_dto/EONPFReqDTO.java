package com.cms.cdl.dto.request_dto.onboarding_req_dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public record EONPFReqDTO(
        @JsonProperty("CandID") String candID,
        @JsonProperty("PrevPFNum") String prevPFNum,
        @JsonProperty("PrevESICNum") String prevESICNum,
        @JsonProperty("UAN") String uan,
        @JsonProperty("MemberEPS") String memberEPS,
        @JsonProperty("OWNER_USERID") String ownerUserId,
        @JsonProperty("OWNER_USERORG") String ownerUserOrg
) {
    public static EONPFReqDTO empty() {
        return new EONPFReqDTO(
                "", "", "", "", "", "", ""
        );
    }
}
