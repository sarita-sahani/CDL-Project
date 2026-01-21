package com.cms.cdl.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name="EONPersonal")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class EONPersonal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String candID;
    private String firstName;
    private String middleName;
    private String lastName;
    private String emailAddr;
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dob;
    private String maritalStatus;
    private Integer noOfDependents;
    private String bloodGroup;
    private String emrContactNum;
    private String aadharNum;
    private String panNum;
    private String passportNum;
    private String passportExpiry;
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
