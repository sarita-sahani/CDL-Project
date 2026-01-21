package com.cms.cdl.dto.request_dto.wrapperDTO;

import com.cms.cdl.dto.request_dto.display_dto.JobApplicationDTO;
import com.cms.cdl.dto.request_dto.onboarding_req_dto.EONLanguageReqDTO;
import com.cms.cdl.dto.request_dto.onboarding_req_dto.EONOverallStatusReqDTO;
import com.cms.cdl.dto.response_dto.EONOverallStatusResDTO;
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
public class EONOverallStatusWrapperDTO {
    @JsonProperty("retcode")
    private int retcode;

//    @JsonProperty("retdata")
//    private EONOverallStatusReqDTO retdata;

    @JsonProperty("retdata")
    private List<EONOverallStatusReqDTO> retdata;



}
