package com.cms.cdl.dto.request_dto.onboarding_req_dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public record EONDeclQnrReqDTO(
        @JsonProperty("CandID") String candID,
        @JsonProperty("Relative") String relative,
        @JsonProperty("ExEmp") String exEmp,
        @JsonProperty("PrevAppl") String prevAppl,
        @JsonProperty("ServiceBond") String serviceBond,
        @JsonProperty("Convicted") String convicted,
        @JsonProperty("MemberUnion") String memberUnion,
        @JsonProperty("MajorHosp") String majorHosp,
        @JsonProperty("LastMedCheck") String lastMedCheck,
        @JsonProperty("MajorBreak") String majorBreak,
        @JsonProperty("OWNER_USERID") String ownerUserId,
        @JsonProperty("OWNER_USERORG") String ownerUserOrg
) {
    public static EONDeclQnrReqDTO empty() {
        return new EONDeclQnrReqDTO(
                "", "", "", "", "", "", "", "", "",
                "", "", ""
        );
    }
}
