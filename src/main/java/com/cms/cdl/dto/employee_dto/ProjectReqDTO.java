package com.cms.cdl.dto.employee_dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProjectReqDTO extends BaseEntityReqDTO {
    private String projectName;
    private String projectShortName;
    private String projectCode;
    private String wbsName;
}
