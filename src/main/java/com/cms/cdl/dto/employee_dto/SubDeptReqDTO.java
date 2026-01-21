package com.cms.cdl.dto.employee_dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SubDeptReqDTO extends BaseEntityReqDTO{
    private String subDeptName;
    private String orgUnits;
}
