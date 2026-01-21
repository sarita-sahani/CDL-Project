package com.cms.cdl.dto.request_dto.wrapperDTO;

import com.cms.cdl.dto.request_dto.display_dto.JobApplicationDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class JobApplicationWrapperDTO {
    @JsonProperty("retcode")
    private int retcode;

    @JsonProperty("retdata")
    private List<JobApplicationDTO> retdata;
    @JsonProperty("retmessage")
    private String retmessage;

}
