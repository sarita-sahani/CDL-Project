package com.cms.cdl.dto.response_dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EONDocProofResDTO {
    private String candID;

    private String voterID;
//    private String fileVoter;
    private Long fileVoterDocId;

    private String panCard;
//    private String filePAN;
    private Long filePANDocId;

    private String drivingID;
//    private String fileDriving;
    private Long fileDrivingDocId;
    private String aadharCard;
//    private String fileAadhar;
    private Long aadharDocId;
    private String empIDCard;
//    private String fileEmp;
    private Long fileEmpDocId;

    private String passportNum;
//    private String filePassport;
    private Long filePassportDocId;

    private String addrProofType1;
//    private String fileAddrProof1;
    private Long fileAddrProof1DocId;

    private String addrProofType2;
//    private String fileAddrProof2;
    private Long fileAddrProof2DocId;

    private String addrProofType3;
//    private String fileAddrProof3;
    private Long fileAddrProof3DocId;

    private String ownerUserId;
    private String ownerUserOrg;
}
