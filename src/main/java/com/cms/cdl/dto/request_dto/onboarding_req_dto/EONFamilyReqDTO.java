package com.cms.cdl.dto.request_dto.onboarding_req_dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
public record EONFamilyReqDTO(
        @JsonProperty("CandID") String candID,
        @JsonProperty("FatherName") String fatherName,
        @JsonProperty("FatherAge") Integer fatherAge,
        @JsonProperty("FatherEdu") String fatherEdu,

        @JsonProperty("MotherName") String motherName,
        @JsonProperty("MotherAge") Integer motherAge,
        @JsonProperty("MotherEdu") String motherEdu,

        @JsonProperty("SisterName") String sisterName,
        @JsonProperty("SisterAge") Integer sisterAge,
        @JsonProperty("SisterEdu") String sisterEdu,

        @JsonProperty("BrotherName") String brotherName,
        @JsonProperty("BrotherAge") Integer brotherAge,
        @JsonProperty("BrotherEdu") String brotherEdu,

        @JsonProperty("SpouseName") String spouseName,
        @JsonProperty("SpouseAge") Integer spouseAge,
        @JsonProperty("SpouseEdu") String spouseEdu,

        @JsonProperty("Child1Name") String child1Name,
        @JsonProperty("Child1Age") Integer child1Age,
        @JsonProperty("Child1Edu") String child1Edu,

        @JsonProperty("Child2Name") String child2Name,
        @JsonProperty("Child2Age") Integer child2Age,
        @JsonProperty("Child2Edu") String child2Edu,

        @JsonProperty("Child3Name") String child3Name,
        @JsonProperty("Child3Age") Integer child3Age,
        @JsonProperty("Child3Edu") String child3Edu,

        @JsonProperty("MajorIllness") String majorIllness,

        @JsonProperty("OWNER_USERID") String ownerUserId,
        @JsonProperty("OWNER_USERORG") String ownerUserOrg
) {
    public static EONFamilyReqDTO empty() {
        return new EONFamilyReqDTO(
                "", "", 0, "",
                "", 0, "",
                "", 0, "",
                "", 0, "",
                "", 0, "",
                "", 0, "",
                "", 0, "",
                "", 0, "",
                "", "", ""
        );
    }
}
