package com.cms.cdl.dto.response_dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EONInterestResDTO {
    private String candID;

    private String activity1;
    private String profMem1;
    private String affNGO1;

    private String activity2;
    private String profMem2;
    private String affNGO2;

    private String activity3;
    private String profMem3;
    private String affNGO3;

    private String ownerUserId;
    private String ownerUserOrg;
}
