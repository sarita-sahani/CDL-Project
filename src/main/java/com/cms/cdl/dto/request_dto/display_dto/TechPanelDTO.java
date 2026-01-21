package com.cms.cdl.dto.request_dto.display_dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TechPanelDTO(
        @JsonProperty("EmpID") String empID,
        @JsonProperty("EmpUserID") String empUserID,
        @JsonProperty("EmpName") String empName,
        @JsonProperty("EmpEmailID") String empEmailID,
        @JsonProperty("Grade") String grade,
        @JsonProperty("Designation") String designation,
        @JsonProperty("OrgUnit") String orgUnit,
        @JsonProperty("OrgSubUnit") String orgSubUnit,
        @JsonProperty("Contact") String contact,
        @JsonProperty("Location") String location,
        @JsonProperty("Skills") String skills,
        @JsonProperty("Roles") String roles,
        @JsonProperty("PreferredSlots") String preferredSlots,
        @JsonProperty("Status") String status,
        @JsonProperty("Remarks") String remarks
) {
}
