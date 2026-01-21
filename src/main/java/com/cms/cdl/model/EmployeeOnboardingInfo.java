package com.cms.cdl.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="EmployeeOnboardingInfo")
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeOnboardingInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String candID;
    private String empOnboardingRefNum;
    private String status;

    // === SAP & COMPANY IDENTIFIERS ===
    private String companyCode; //
    private String officialEmailId; //
    private String organisationCode;
    private String organisationHierarchy;

    // === ORGANIZATIONAL UNIT & DESCRIPTIONS ===
    private String orgUnit; //
    private String orgUnitDesc; //
    private String mainDepartment;
    private String deptDesc; //
    private String subDepartment;
    private String subDeptDesc; //

    // === PROJECT & WBS ELEMENTS ===
    private String wbsElement; //
    private String wbsElementText; //
    private String project;
    private String projectDesc; //
    private String designation;

    // === CATEGORIZATION & PAYROLL ===
    private String classification; //
    private String category; //
    private String eeSubgroup;
    private String eeSubGrpName; //
    private String segment; //
    private String grade;
    private String employmentStatus;
    private String payrollAreaType;

    // === REPORTING HIERARCHY ===
    private String rptManagerName;
    private String rptMgrEmployeeCode;
    private String rptManagerEmailId;
    private String buHead;
    private String buHeadECode; //
    private String buHeadName; //
    private String buHeadEmail; //

    // === SKILLS & EDUCATION (If not stored in separate Skill table) ===
    private String qualification; //
    private String certification1; //
    private String certification2; //
    private String otherCertifications; //

    // === STATUTORY & DATES ===
    private String gender;
    private String fullNameAsPerPAN;
    private String currentPfNumber;
    private String currentESICNumber;
    private LocalDate joiningDate;
    private LocalDate confirmationDate;

    // === PROBATION ===
    private Boolean isProbationApplicable;
    private String probationPeriod;
    private String maxProbationExtension;

    // === EXIT INFORMATION ===
    private String resignationStatus; //
    private LocalDate dateOfResignation; //
    private LocalDate dateOfLeaving; //
    private String exitStatus; //

    // === EXCEL EXPORT TRACKING ===
    private String excelExportStatus; //

    // === APPLICABILITY FLAGS ===
    private String mlwfApplicability;
    private String gmcApplicability;
    private String gtlApplicability;
    private String gpaApplicability;
    private String wcApplicability;
    private String hiringHr;
}
