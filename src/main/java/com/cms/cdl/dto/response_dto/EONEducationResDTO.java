package com.cms.cdl.dto.response_dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EONEducationResDTO {
    private String candID;
    private String sscInstituteName;
    private String sscSubjects;
    private String sscPeriodFrom;
    private String sscPeriodTo;
    private Double sscMarks;

    private String hscInstituteName;
    private String hscSubjects;
    private String hscPeriodFrom;
    private String hscPeriodTo;
    private Double hscMarks;

    private String grInstituteName;
    private String grSubjects;
    private String grPeriodFrom;
    private String grPeriodTo;
    private Double grMarks;

    private String pgInstituteName;
    private String pgSubjects;
    private String pgPeriodFrom;
    private String pgPeriodTo;
    private Double pgMarks;

    private String dpInstituteName;
    private String dpSubjects;
    private String dpPeriodFrom;
    private String dpPeriodTo;
    private Double dpMarks;

    private String otInstituteName;
    private String otSubjects;
    private String otPeriodFrom;
    private String otPeriodTo;
    private Double otMarks;

    private String ownerUserId;
    private String ownerUserOrg;
}
