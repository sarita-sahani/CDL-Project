package com.cms.cdl.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name ="EONSkill")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class EONSkill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String candID;

    private String primarySkill1Name;
    private String primarySkill1Level;

    private String primarySkill2Name;
    private String primarySkill2Level;

    private String primarySkill3Name;
    private String primarySkill3Level;

    private String secondarySkill1Name;
    private String secondarySkill1Level;

    private String secondarySkill2Name;
    private String secondarySkill2Level;

    private String secondarySkill3Name;
    private String secondarySkill3Level;

    private String ownerUserId;
    private String ownerUserOrg;
}
