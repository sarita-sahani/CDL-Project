package com.cms.cdl.dto.request_dto.onboarding_req_dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public record EONGeneralReqDTO(
        @JsonProperty("CandNum") Long candNum,
        @JsonProperty("CandID") String candID,
        @JsonProperty("CandName") String candName,
        @JsonProperty("PosApplied")String posApplied,
        @JsonProperty("CurLocation") String curLocation,
        @JsonProperty("DateOfJoin") String dateOfJoin,
        @JsonProperty("TotalExp") Integer totalExp,
        @JsonProperty("pid_type") String pidType,
        @JsonProperty("pid_num") String pidNum,
        @JsonProperty("email_id") String emailId,
        @JsonProperty("INIT_USERID")  String initUserId,
        @JsonProperty("JOB_ID") Integer jobId,
        @JsonProperty("JOB_APP_ID") Integer jobAppId,
        @JsonProperty("JOINED_EMPID") String joinedEmpId,
        @JsonProperty("JOINED_DATE") String joinedDate,
        @JsonProperty("HRAdminRemarks") String hrAdminRemarks,
        @JsonProperty("OWNER_USERID") String OwnerUserId,
        @JsonProperty("OWNER_USERORG") String OwnerUserOrg
) {
    public static EONGeneralReqDTO empty() {
        return new EONGeneralReqDTO(
                0L, "", "", "", "", "", 0, "", "", "", "", 0, 0, "", "", "", "", ""
        );
    }

}
