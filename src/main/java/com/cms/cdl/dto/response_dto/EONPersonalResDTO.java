package com.cms.cdl.dto.response_dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EONPersonalResDTO {
    private String candID;
    private String firstName;
    private String middleName;
    private String lastName;
    private String emailAddr;
    private LocalDate dob;
    private String maritalStatus;
    private Integer noOfDependents;
    private String bloodGroup;
    private String emrContactNum;
    private String aadharNum;
    private String panNum;
    private String passportNum;
    private String passportExpiry;
    //private String filePhotoImage;
    private Long filePhotoImageDocID;
    private String curAddress;
    private String curCity;
    private String curState;
    private String curPinCode;
    private String curContactNum;
    private String permAddress;
    private String permCity;
    private String permState;
    private String permPinCode;
    private String permContactNum;
    private String OwnerUserId;
    private String OwnerUserOrg;
}
