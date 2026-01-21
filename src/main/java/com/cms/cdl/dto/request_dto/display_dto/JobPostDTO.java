package com.cms.cdl.dto.request_dto.display_dto;

import com.cms.cdl.dto.request_dto.onboarding_req_dto.EONSkillReqDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record JobPostDTO(
        @JsonProperty("JOB_ID") int jobId,
        @JsonProperty("JOB_TYPE") String jobType,
        @JsonProperty("PUBLISH_TYPE") String publishType,
        @JsonProperty("JOB_STATUS") String jobStatus,
        @JsonProperty("JOB_ROLE") String jobRole,
        @JsonProperty("JOB_DESC") String jobDesc,
        @JsonProperty("JOB_DOC") List<String> JOB_DOC,
        @JsonProperty("JOB_LOCATIONS") String jobLocations,
        @JsonProperty("OTH_JOB_LOCATIONS") String othJobLocations,
        @JsonProperty("HIRING_DEPT") String hiringDept,
        @JsonProperty("HIRING_MGR") String hiringMgr,
        @JsonProperty("EXP_MIN") int expMin,
        @JsonProperty("EXP_MAX") int expMax,
        @JsonProperty("SALARY_MIN") float salaryMin,
        @JsonProperty("SALARY_MAX") float salaryMax,
        @JsonProperty("INT_REF_DATA") String intRefData,
        @JsonProperty("CUST_NAME") String custName,
        @JsonProperty("PROJ_CODE") String projCode,
        @JsonProperty("PROJ_NAME") String projName,
        @JsonProperty("PROJ_WAVE") String projWave,
        @JsonProperty("RES_REQ_WAVE") String resReqWave,
        @JsonProperty("TOTAL_COUNT") int totalCount,
        @JsonProperty("FULFILLED_COUNT") int fulfilledCount,
        @JsonProperty("PENDING_COUNT") int pendingCount,
        @JsonProperty("SELECTED_COUNT") int selectedCount,
        @JsonProperty("OFFERED_COUNT") int offeredCount,
        @JsonProperty("JOINED_COUNT") int joinedCount,
        @JsonProperty("REJECTED_COUNT") int rejectedCount,
        @JsonProperty("LEAD_TIME") int leadTime,
        @JsonProperty("JOB_PRIORITY") String jobPriority,
        @JsonProperty("SCR_PENDING") int scrPending,
        @JsonProperty("SCR_SELECTED") int scrSelected,
        @JsonProperty("SCR_REJECTED") int scrRejected,
        @JsonProperty("APP_TOTAL_COUNT") int appTotalCount,
        @JsonProperty("APP_PENDING_COUNT") int appPendingCount,
        @JsonProperty("APP_INPROGRESS_COUNT") int appInProgressCount,
        @JsonProperty("APP_PROCESSED_COUNT") int appProcessedCount,
        @JsonProperty("APP_SELECTION_RATIO") float appSelectionRatio,
        @JsonProperty("APP_REJECTION_RATIO") float appRejection,
        @JsonProperty("JOB_POST_DATE") String jobPostDate,
        @JsonProperty("JOB_POST_USERID") String jobPostUserId,
        @JsonProperty("KEY_ROLES") String KEY_ROLES,

                @JsonProperty("KEY_SKILLS") String KEY_SKILLS,
        @JsonProperty("OWNER_USERID") String ownerUserId,
        @JsonProperty("OWNER_USERORG") String ownerUserOrg
) {}
