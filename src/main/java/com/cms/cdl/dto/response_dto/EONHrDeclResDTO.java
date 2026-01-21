package com.cms.cdl.dto.response_dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EONHrDeclResDTO {
    private String candID;
    private String nameHR;
    private String empCodeHR;
    private  String location;
    private String verifiedDate;
    private String signatureHR;
    private String ownerUserId;
    private String ownerUserOrg;
}
