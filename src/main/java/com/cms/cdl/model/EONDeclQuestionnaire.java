package com.cms.cdl.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "EONDeclQuestionnaire")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class EONDeclQuestionnaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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

