package com.cms.cdl.dto.employee_dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class DependentDetailsReqDTO extends BaseEntityReqDTO{
    private String dependentName;
    private String dependentRelationship;
    private String dependentDateOfBirth;
}
