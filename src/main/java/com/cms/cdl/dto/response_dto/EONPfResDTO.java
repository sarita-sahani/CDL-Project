package com.cms.cdl.dto.response_dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EONPfResDTO {
    private String candID;

    private String prevPFNum;
    private String prevESICNum;
    private String uan;
    private String memberEPS;
    private String ownerUserId;
    private String ownerUserOrg;
}
