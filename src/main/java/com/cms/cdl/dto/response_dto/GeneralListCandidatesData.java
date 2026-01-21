package com.cms.cdl.dto.response_dto;

import com.cms.cdl.dto.request_dto.onboarding_req_dto.EONGeneralReqDTO;
import com.cms.cdl.dto.request_dto.onboarding_req_dto.EONOverallStatusReqDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeneralListCandidatesData {
    private String candId;

    private EONOverallStatusReqDTO eonOverallStatusReqDTO;
    private EONGeneralReqDTO eonGeneralReqDTO;
}
