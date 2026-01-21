package com.cms.cdl.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@Table(name="EONProfressionalExperience")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class EONProfressionalExperience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String candID;

    private String company1;
    private String location1;
    private String desGrad1;
    private String fromDate1;
    private String toDate1;
    private String annualCTC1;
    private String jobRole1;
    private String leaveReason1;

    private String company2;
    private String location2;
    private String desGrad2;
    private String fromDate2;
    private String toDate2;
    private String annualCTC2;
    private String jobRole2;
    private String leaveReason2;

    private String company3;
    private String location3;
    private String desGrad3;
    private String fromDate3;
    private String toDate3;
    private String annualCTC3;
    private String jobRole3;
    private String leaveReason3;

    private String company4;
    private String location4;
    private String desGrad4;
    private String fromDate4;
    private String toDate4;
    private String annualCTC4;
    private String jobRole4;
    private String leaveReason4;

    private String company5;
    private String location5;
    private String desGrad5;
    private String fromDate5;
    private String toDate5;
    private String annualCTC5;
    private String jobRole5;
    private String leaveReason5;

    private String jobAchieve;
    private String jobTrainCert;

    private String ownerUserId;
    private String ownerUserOrg;
}
