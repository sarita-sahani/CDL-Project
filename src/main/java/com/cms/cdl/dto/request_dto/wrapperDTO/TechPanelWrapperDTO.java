package com.cms.cdl.dto.request_dto.wrapperDTO;

import com.cms.cdl.dto.request_dto.display_dto.JobPostDTO;
import com.cms.cdl.dto.request_dto.display_dto.TechPanelDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TechPanelWrapperDTO {

    @JsonProperty("retcode")
    private int retcode;

    @JsonProperty("retdata")
    private List<TechPanelDTO> retdata;
    @JsonProperty("retmessage")
    private String retmessage;
}
