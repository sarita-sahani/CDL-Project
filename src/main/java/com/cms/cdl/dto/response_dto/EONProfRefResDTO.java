package com.cms.cdl.dto.response_dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EONProfRefResDTO {
    private String candID;

    private String refName1;
    private String refOrg1;
    private String refDesOcc1;
    private String refAddr1;
    private String refContactNum1;
    private String refEmail1;

    private String refName2;
    private String refOrg2;
    private String refDesOcc2;
    private String refAddr2;
    private String refContactNum2;
    private String refEmail2;

    private String refName3;
    private String refOrg3;
    private String refDesOcc3;
    private String refAddr3;
    private String refContactNum3;
    private String refEmail3;

    private String ownerUserId;
    private String ownerUserOrg;
}
