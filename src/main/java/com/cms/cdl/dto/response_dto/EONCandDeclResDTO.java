package com.cms.cdl.dto.response_dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EONCandDeclResDTO {
    private String candID;
    private String declDate;
    private String declPlace;
    private String candSignature;
    private String ownerUserId;
    private String ownerUserOrg;
}
