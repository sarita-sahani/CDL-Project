package com.cms.cdl.dto.response_dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EONDeclQnrResDTO {
    private String candID;

    private String relative;
    private String exEmp;
    private String prevAppl;

    private String serviceBond;
    private String convicted;
    private String memberUnion;

    private String majorHosp;
    private String lastMedCheck;
    private String majorBreak;

    private String ownerUserId;
    private String ownerUserOrg;
}
