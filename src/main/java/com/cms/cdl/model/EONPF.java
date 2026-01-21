package com.cms.cdl.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="EONPF")
@JsonIgnoreProperties(ignoreUnknown = true)
public class EONPF {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String candID;

    private String prevPFNum;
    private String prevESICNum;
    private String uan;
    private String memberEPS;
    private String ownerUserId;
    private String ownerUserOrg;
}
