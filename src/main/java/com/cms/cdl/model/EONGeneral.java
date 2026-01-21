package com.cms.cdl.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Entity
@Table(name="EONGeneral")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class EONGeneral {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long candNum;
    private String candID;
    private String candName;
    private String posApplied;
    private String curLocation;
    private String dateOfJoin;

    @Column(name = "totalexp")
    private Integer totalExp;

    @Column(name = "pid_type")
    private String pidType;

    @Column(name = "pid_num")
    private String pidNum;

    @Column(name = "email_id")
    private String emailId;

    @Column(name = "owner_userid")
    private String ownerUserId;

    @Column(name = "owner_userorg")
    private String ownerUserOrg;

    @Column(name = "init_userid")
    private String initUserId;

    @Column(name = "job_id")
    private Integer jobId;

    @Column(name = "job_app_id")
    private Integer jobAppId;

    @Column(name = "joined_empid")
    private String joinedEmpId;

    @Column(name = "joined_date")
    private String joinedDate;

    @Column(name = "hradminremarks")
    private String hrAdminRemarks;

    @Column(name = "owner_user_id")
    private String OwnerUserId;

    @Column(name = "owner_user_org")
    private String OwnerUserOrg;

}
