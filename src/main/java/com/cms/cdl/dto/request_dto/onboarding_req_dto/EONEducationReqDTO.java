package com.cms.cdl.dto.request_dto.onboarding_req_dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
@JsonIgnoreProperties(ignoreUnknown = true)
public record EONEducationReqDTO(

        @JsonProperty("CandID") String candID,
        @JsonProperty("SSCInstituteName") String sscInstituteName,
        @JsonProperty("SSCSubjects") String sscSubjects,
        @JsonProperty("SSCPeriodFrom") String sscPeriodFrom,
        @JsonProperty("SSCPeriodTo") String sscPeriodTo,
        @JsonProperty("SSCMarks") Double sscMarks,

        @JsonProperty("HSCInstituteName") String hscInstituteName,
        @JsonProperty("HSCSubjects") String hscSubjects,
        @JsonProperty("HSCPeriodFrom") String hscPeriodFrom,
        @JsonProperty("HSCPeriodTo") String hscPeriodTo,
        @JsonProperty("HSCMarks") Double hscMarks,

        @JsonProperty("GRInstituteName") String grInstituteName,
        @JsonProperty("GRSubjects ") String grSubjects,
        @JsonProperty("GRPeriodFrom") String grPeriodFrom,
        @JsonProperty("GRPeriodTo") String grPeriodTo,
        @JsonProperty("GRMarks") Double grMarks,

        @JsonProperty("PGInstituteName") String pgInstituteName,
        @JsonProperty("PGSubjects") String pgSubjects,
        @JsonProperty("PGPeriodFrom") String pgPeriodFrom,
        @JsonProperty("PGPeriodTo") String pgPeriodTo,
        @JsonProperty("PGMarks") Double pgMarks,

        @JsonProperty("DPInstituteName") String dpInstituteName,
        @JsonProperty("DPSubjects") String dpSubjects,
        @JsonProperty("DPPeriodFrom") String dpPeriodFrom,
        @JsonProperty("DPPeriodTo") String dpPeriodTo,
        @JsonProperty("DPMarks") Double dpMarks,

        @JsonProperty("OTInstituteName") String otInstituteName,
        @JsonProperty("OTSubjects") String otSubjects,
        @JsonProperty("OTPeriodFrom") String otPeriodFrom,
        @JsonProperty("OTPeriodTo") String otPeriodTo,
        @JsonProperty("OTMarks") Double otMarks,

        @JsonProperty("OWNER_USERID") String ownerUserId,
        @JsonProperty("OWNER_USERORG") String ownerUserOrg
) {
    public static EONEducationReqDTO empty() {
        return new EONEducationReqDTO(
                "", "", "", "", "", 0.0,
                "", "", "", "", 0.0,
                "", "", "", "", 0.0,
                "", "", "", "", 0.0,
                "", "", "", "", 0.0,
                "", "", "", "", 0.0,
                "", ""
        );
    }


}
