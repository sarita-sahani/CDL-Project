package com.cms.cdl.dto.request_dto.onboarding_req_dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
@JsonIgnoreProperties(ignoreUnknown = true)
public record EONProfExpReqDTO(
        @JsonProperty("CandID") String candID,
        @JsonProperty("Company1") String company1,
        @JsonProperty("Location1") String location1,
        @JsonProperty("DesGrad1") String desGrad1,
        @JsonProperty("FromDate1") String fromDate1,
        @JsonProperty("ToDate1") String toDate1,
        @JsonProperty("AnnualCTC1") String annualCTC1,
        @JsonProperty("JobRole1") String jobRole1,
        @JsonProperty("LeaveReason1") String leaveReason1,
        @JsonProperty("Company2") String company2,
        @JsonProperty("Location2") String location2,
        @JsonProperty("DesGrad2") String desGrad2,
        @JsonProperty("FromDate2") String fromDate2,
        @JsonProperty("ToDate2") String toDate2,
        @JsonProperty("AnnualCTC2") String annualCTC2,
        @JsonProperty("JobRole2") String jobRole2,
        @JsonProperty("LeaveReason2") String leaveReason2,
        @JsonProperty("Company3") String company3,
        @JsonProperty("Location3") String location3,
        @JsonProperty("DesGrad3") String desGrad3,
        @JsonProperty("FromDate3") String fromDate3,
        @JsonProperty("ToDate3") String toDate3,
        @JsonProperty("AnnualCTC3") String annualCTC3,
        @JsonProperty("JobRole3") String jobRole3,
        @JsonProperty("LeaveReason3") String leaveReason3,
        @JsonProperty("Company4") String company4,
        @JsonProperty("Location4") String location4,
        @JsonProperty("DesGrad4") String desGrad4,
        @JsonProperty("FromDate4") String fromDate4,
        @JsonProperty("ToDate4") String toDate4,
        @JsonProperty("AnnualCTC4") String annualCTC4,
        @JsonProperty("JobRole4") String jobRole4,
        @JsonProperty("LeaveReason4") String leaveReason4,
        @JsonProperty("Company5") String company5,
        @JsonProperty("Location5") String location5,
        @JsonProperty("DesGrad5") String desGrad5,
        @JsonProperty("FromDate5") String fromDate5,
        @JsonProperty("ToDate5") String toDate5,
        @JsonProperty("AnnualCTC5") String annualCTC5,
        @JsonProperty("JobRole5") String jobRole5,
        @JsonProperty("LeaveReason5") String leaveReason5,
        @JsonProperty("JobAchieve") String jobAchieve,
        @JsonProperty("JobTrainCert") String jobTrainCert,

        @JsonProperty("OWNER_USERID") String ownerUserId,
        @JsonProperty("OWNER_USERORG") String ownerUserOrg
) {
    public static EONProfExpReqDTO empty() {
        return new EONProfExpReqDTO(
                "", "", "", "", "", "", "", "", "",
                "", "", "", "", "", "", "", "",
                "", "", "", "", "", "", "", "",
                "", "", "", "", "", "", "", "",
                "", "", "", "", "", "", "", "",
                "", "", "",""
        );
    }

}
