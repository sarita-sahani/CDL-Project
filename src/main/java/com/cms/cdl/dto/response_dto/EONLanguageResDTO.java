package com.cms.cdl.dto.response_dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EONLanguageResDTO {
    private String candID;

    private String lang1Name;
    private String lang1Level;

    private String lang2Name;
    private String lang2Level;

    private String lang3Name;
    private String lang3Level;

    private String lang4Name;
    private String lang4Level;

    private String lang5Name;
    private String lang5Level;

    private String ownerUserId;
    private String ownerUserOrg;
}
