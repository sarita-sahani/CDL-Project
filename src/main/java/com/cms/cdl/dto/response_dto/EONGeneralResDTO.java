package com.cms.cdl.dto.response_dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EONGeneralResDTO {

    private String candID;
    private Long candNum;
    private String candName;
    private String posApplied;
    private String curLocation;
    private String dateOfJoin;
    private Integer totalExp;
    private String pidType;
    private String pidNum;
    private String emailId;
    private String ownerUserId;
    private String ownerUserOrg;
    private String initUserId;
    private Integer jobId;
    private Integer jobAppId;
    private String joinedEmpId;
    private LocalDate joinedDate;
    private String hrAdminRemarks;
    private String OwnerUserId;
    private String OwnerUserOrg;
}
