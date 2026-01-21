package com.cms.cdl.dto.request_dto.display_dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public record InterviewFeedbackDTO(
        @JsonProperty("FeedbackNum") int feedbackNum,
        @JsonProperty("IntNum") int intNum,
        @JsonProperty("JobAppNum") int jobAppNum,
        @JsonProperty("JobPostNum") int jobPostNum,
        @JsonProperty("JobRating") String jobRating,
        @JsonProperty("JobRemarks") String jobRemarks,
        @JsonProperty("TechRating") String techRating,
        @JsonProperty("TechRemarks") String techRemarks,
        @JsonProperty("LogicalRating") String logicalRating,
        @JsonProperty("LogicalRemarks") String logicalRemarks,
        @JsonProperty("ManagementRating") String managementRating,
        @JsonProperty("ManagementRemarks") String managementRemarks,
        @JsonProperty("SoftCommRating") String softCommRating,
        @JsonProperty("SoftCommRemarks") String softCommRemarks,
        @JsonProperty("OverallRating") String overallRating,
        @JsonProperty("OverallRemarks") String overallRemarks,
        @JsonProperty("SelStatus") String selStatus,
        @JsonProperty("OtherRemarks") String otherRemarks,
        @JsonProperty("FeedbackByUser") String feedbackByUser,
        @JsonProperty("FeedbackDate") String feedbackDate,
        @JsonProperty("FeedbackType") String feedbackType,
        @JsonProperty("FeedbackStatus") String feedbackStatus,
        @JsonProperty("Scheduled_UserID") String scheduledUserID,
        @JsonProperty("form_status") String formStatus,
        @JsonProperty("OWNER_USERID") String ownerUserId,
        @JsonProperty("OWNER_USERORG") String ownerUserOrg
) {
}
