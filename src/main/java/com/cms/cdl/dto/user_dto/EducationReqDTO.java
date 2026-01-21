package com.cms.cdl.dto.user_dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class EducationReqDTO extends BaseEntityReqDTO{
    private String name;
    private String fieldOfStudy;
    private String instituteName;
    private String instituteAddress;
    private String academicStartYear;
    private String academicEndYear;
    private String status;
    private String certificate;
    private String docName;
    private String qualification;
}
