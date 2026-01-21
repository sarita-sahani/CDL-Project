package com.cms.cdl.dto.employee_dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmpFBRReqDTO {
    private String empCode;
    private Long fbrId;
    private String createdBy;
    private String updatedBy;
    private String description;
}
