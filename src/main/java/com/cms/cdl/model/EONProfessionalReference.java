package com.cms.cdl.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "EONProfessionalReference")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class EONProfessionalReference {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
