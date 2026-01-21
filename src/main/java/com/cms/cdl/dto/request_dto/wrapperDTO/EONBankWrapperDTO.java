package com.cms.cdl.dto.request_dto.wrapperDTO;

import com.cms.cdl.dto.request_dto.onboarding_req_dto.EONBankReqDTO;
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
public class EONBankWrapperDTO {
    @JsonProperty("retcode")
    private int retcode;

    @JsonProperty("retdata")
    private EONBankReqDTO retdata;
}
