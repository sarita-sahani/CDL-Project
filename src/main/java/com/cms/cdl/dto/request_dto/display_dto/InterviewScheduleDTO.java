package com.cms.cdl.dto.request_dto.display_dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public record InterviewScheduleDTO(
        @JsonProperty("IntNum") int intNum,
        @JsonProperty("JobAppID") int jobAppID,
        @JsonProperty("JobPostID") int jobPostID,
        @JsonProperty("PanelID") String panelID,
        @JsonProperty("IntDate") String intDate,
        @JsonProperty("StartTime") String startTime,
        @JsonProperty("EndTime") String endTime,
        @JsonProperty("IntMode") String intMode,
        @JsonProperty("MeetingLink") String meetingLink,
        @JsonProperty("MeetingInfo") String meetingInfo,
        @JsonProperty("Remarks") String remarks,
        @JsonProperty("IntStatus") String intStatus,
        @JsonProperty("ScheduledByUser") String scheduledByUser,
        @JsonProperty("ScheduledDate ") String scheduledDate,
        @JsonProperty("OWNER_USERID") String ownerUserID,
        @JsonProperty("OWNER_USERORG") String ownerUserOrg,
        @JsonProperty("MeetingICS") String meetingICS,
        @JsonProperty("PanelUserID") String panelUserID,
        @JsonProperty("IntType") String intType
) {
}

