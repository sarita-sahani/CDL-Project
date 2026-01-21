package com.cms.cdl.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Entity
@Table(name="EONEducation")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class EONEducation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
