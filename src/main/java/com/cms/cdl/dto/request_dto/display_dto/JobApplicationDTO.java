package com.cms.cdl.dto.request_dto.display_dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record JobApplicationDTO(
        @JsonProperty("JOB_APP_ID") int jobAppId,
        @JsonProperty("JOB_ID") int jobId,
        @JsonProperty("REF_TYPE") String refType,
        @JsonProperty("JOB_APP_STAGE") String jobAppStage,
        @JsonProperty("JOB_APP_STATUS") String jobAppStatus,
        @JsonProperty("JobPostedByUserID") String jobPostedByUserId,
        @JsonProperty("CAND_FIRST_NAME") String candFirstName,
        @JsonProperty("CAND_LAST_NAME") String candLastName,
        @JsonProperty("CAND_DOB") String candDOB,
        @JsonProperty("CAND_QUALIFICATION") String candQualification,
        @JsonProperty("CAND_STUDY_INSTITUTE") String candStudyInstitute,
        @JsonProperty("CAND_CUR_ROLE") String candCurRole,
        @JsonProperty("CAND_EXP_DESC") String candExpDesc,
        @JsonProperty("CAND_EXP_YEAR") int candExpYear,
        @JsonProperty("CAND_RESUME") List<String> candResume, // document will come
        @JsonProperty("CAND_EMAIL_ID") String candEmailId,
        @JsonProperty("CAND_ADDRESS") String candAddress,
        @JsonProperty("CAND_MOBILE_NO") String candMobileNo,
        @JsonProperty("APP_JOB_LOCATIONS") String appJobLocations,
        @JsonProperty("PREF_JOB_LOCATIONS") String prefJobLocations,
        @JsonProperty("CAND_NOTICE_PERIOD") int candNoticePeriod,
        @JsonProperty("REF_EMAIL_ID") String refEmailId,
        @JsonProperty("CAND_ROLE_APPL") String candRoleAppl,
        @JsonProperty("SALARY_CUR") float salaryCur,
        @JsonProperty("SALARY_EXP") float salaryExp,
        @JsonProperty("OFFER_INFO") String offerInfo,
        @JsonProperty("LAST_WORKING_DATE") String lastWorkingDate,
        @JsonProperty("ScreenID") String screenID,
        @JsonProperty("ScreenUserID") String screenUserID,
        @JsonProperty("ScreenStatus") String screenStatus,
        @JsonProperty("ScreenRemarks") String screenRemarks,
        @JsonProperty("RESUME_STATUS") String ResumeStatus,
        @JsonProperty("L1_STATUS") String l1Status,
        @JsonProperty("L2_STATUS") String l2Status,
        @JsonProperty("L3_STATUS") String l3Status,
        @JsonProperty("L4_STATUS") String l4Status,
        @JsonProperty("HR_STATUS") String hrStatus,
        @JsonProperty("OFFER_APP_STATUS") String OfferAppStatus,
        @JsonProperty("CommentsHR") String commentsHr,
        @JsonProperty("GROUP_APP_STATUS") String GroupAppStatus,
        @JsonProperty("CommentsGROUP") String commentsGroup,
        @JsonProperty("CommentsTA") String commentsTA,
        @JsonProperty("JOB_APPL_DATE") String jobApplDate,
        @JsonProperty("SELECTED_DATE") String SelectedDate,
        @JsonProperty("OFFERED_DATE") String offeredDate,
        @JsonProperty("JOINED_DATE") String joinedDate,
        @JsonProperty("CLOSURE_DATE") String closureDate,
        @JsonProperty("SELECTION_SLA") int SelectionSla,
        @JsonProperty("SELECTION_SLA_DESC") String SelectionSlaDesc,
        @JsonProperty("JOINED_EMPID") String joinedEmpId,
        @JsonProperty("ONB_USERID") String onbUserId,
        @JsonProperty("OWNER_USERID") String ownerUserId,
        @JsonProperty("OWNER_USERORG") String ownerUserOrg
) {
}
