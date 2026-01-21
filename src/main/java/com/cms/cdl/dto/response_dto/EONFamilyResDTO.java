package com.cms.cdl.dto.response_dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EONFamilyResDTO {
    private String candID;
    private String fatherName;
    private Integer fatherAge;
    private String fatherEdu;

    private String motherName;
    private Integer motherAge;
    private String motherEdu;

    private String sisterName;
    private Integer sisterAge;
    private String sisterEdu;

    private String brotherName;
    private Integer brotherAge;
    private String brotherEdu;

    private String spouseName;
    private Integer spouseAge;
    private String spouseEdu;

    private String child1Name;
    private Integer child1Age;
    private String child1Edu;

    private String child2Name;
    private Integer child2Age;
    private String child2Edu;

    private String child3Name;
    private Integer child3Age;
    private String child3Edu;

    private String majorIllness;

    private String ownerUserId;
    private String ownerUserOrg;
}
