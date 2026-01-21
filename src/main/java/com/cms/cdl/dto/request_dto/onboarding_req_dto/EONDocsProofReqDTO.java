package com.cms.cdl.dto.request_dto.onboarding_req_dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public record EONDocsProofReqDTO(
        @JsonProperty("CandID") String candID,
        @JsonProperty("VoterID") String voterID,
        @JsonProperty("FileVoter") List<String> fileVoter,

        @JsonProperty("PANCard") String panCard,
        @JsonProperty("FilePAN") List<String> filePAN,

        @JsonProperty("DrivingID") String drivingID,
        @JsonProperty("FileDriving") List<String> fileDriving,

        @JsonProperty("AadharCard") String aadharCard,
        @JsonProperty("FileAadhar") List<String> fileAadhar,

        @JsonProperty("EmpIDCard") String empIDCard,
        @JsonProperty("FileEmp") List<String> fileEmp,

        @JsonProperty("PassportNum") String passportNum,
        @JsonProperty("FilePassport") List<String> filePassport,

        @JsonProperty("AddrProofType1") String addrProofType1,
        @JsonProperty("FileAddrProof1") List<String> fileAddrProof1,

        @JsonProperty("AddrProofType2") String addrProofType2,
        @JsonProperty("FileAddrProof2") List<String> fileAddrProof2,

        @JsonProperty("AddrProofType3") String addrProofType3,
        @JsonProperty("FileAddrProof3") List<String> fileAddrProof3,

        @JsonProperty("OWNER_USERID") String ownerUserId,
        @JsonProperty("OWNER_USERORG") String ownerUserOrg
) {
    public static EONDocsProofReqDTO empty() {
        return new EONDocsProofReqDTO(
                "",             // candID
                "",             // voterID
                List.of(),      // fileVoter
                "",             // panCard
                List.of(),      // filePAN
                "",             // drivingID
                List.of(),      // fileDriving
                "",             // aadharCard
                List.of(),      // fileAadhar
                "",             // empIDCard
                List.of(),      // fileEmp
                "",             // passportNum
                List.of(),      // filePassport
                "",             // addrProofType1
                List.of(),      // fileAddrProof1
                "",             // addrProofType2
                List.of(),      // fileAddrProof2
                "",             // addrProofType3
                List.of(),      // fileAddrProof3
                "",             // ownerUserId
                ""              // ownerUserOrg
        );
    }
}
