package com.cms.cdl.dto.request_dto.display_dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class JobApplicationPageDTO {

    // UI metadata
    private String template;
    private String templateFile;

    // session / user info
    private Map<String, Object> sessionInfo;

    // dropdown master data
    private Map<String, Object> dropdownConfig;

    // attr_list config
    //private List<Map<String, Object>> attrList;

    // 🔥 ACTUAL JOB APPLICANT RECORDS
    private List<JobApplicationDTO> records;
}