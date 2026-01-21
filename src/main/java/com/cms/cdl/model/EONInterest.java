package com.cms.cdl.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="EONInterest")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class EONInterest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String candID;

    private String activity1;
    private String profMem1;
    private String affNGO1;

    private String activity2;
    private String profMem2;
    private String affNGO2;

    private String activity3;
    private String profMem3;
    private String affNGO3;

    private String ownerUserId;
    private String ownerUserOrg;
}
