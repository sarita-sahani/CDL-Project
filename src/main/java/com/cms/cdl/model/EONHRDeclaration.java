package com.cms.cdl.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name ="EONHRDeclaration")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class EONHRDeclaration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String candID;
    private String nameHR;
    private String empCodeHR;
    private  String location;
    private String verifiedDate;
    private String signatureHR;
    private String ownerUserId;
    private String ownerUserOrg;
}
