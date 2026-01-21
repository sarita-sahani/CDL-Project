package com.cms.cdl.dto.request_dto.onboarding_req_dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public record EONPersonalReqDTO(
        @JsonProperty("CandID") String candID,
        @JsonProperty("FirstName") String firstName,
        @JsonProperty("MiddleName") String middleName,
        @JsonProperty("LastName") String lastName,
        @JsonProperty("EmailAddr") String emailAddr,
        @JsonProperty("DOB") String dob,
        @JsonProperty("MaritalStatus") String maritalStatus,
        @JsonProperty("NoOfDependents") Integer noOfDependents,
        @JsonProperty("BloodGroup") String bloodGroup,
        @JsonProperty("EmrContactNum") String emrContactNum,
        @JsonProperty("AadharNum") String aadharNum,
        @JsonProperty("PANNum") String panNum,
        @JsonProperty("PassportNum") String passportNum,
        @JsonProperty("PassportExpiry") String passportExpiry,
        @JsonProperty("FilePhotoImage") List<String> filePhotoImage,
        @JsonProperty("CurAddress") String curAddress,
        @JsonProperty("CurCity") String curCity,
        @JsonProperty("CurState") String curState,
        @JsonProperty("CurPinCode") String curPinCode,
        @JsonProperty("CurContactNum") String curContactNum,
        @JsonProperty("PermAddress") String permAddress,
        @JsonProperty("PermCity") String permCity,
        @JsonProperty("PermState") String permState,
        @JsonProperty("PermPinCode") String permPinCode,
        @JsonProperty("PermContactNum") String permContactNum,
        @JsonProperty("OWNER_USERID") String ownerUserId
) {
    // ✅ Static factory method to return default/empty instance
    public static EONPersonalReqDTO empty() {
        return new EONPersonalReqDTO(
                "", "", "", "", "", "", "", 0, "", "", "", "", "",
                "", List.of(), "", "", "", "", "", "", "", "", "", "", ""
        );
    }
}
