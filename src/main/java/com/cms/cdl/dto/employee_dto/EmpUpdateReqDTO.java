package com.cms.cdl.dto.employee_dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmpUpdateReqDTO extends BaseEntityReqDTO {
    private String empCode;
    private String primaryContactNo;
    private String secondaryContactNo;
    private String gender;
    private String bloodGroup;
    private String emergencyContactNo;
    private String emergencyContactName;
    private String relationWithEmergencyContact;
    private String passportNumber;
    private String age;
    private String maritalStatus;
}
