package com.cms.cdl.dto.employee_dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrganizationReqDTO extends BaseEntityReqDTO {
    private String organizationName;
    private String organizationHierarchy;
}
