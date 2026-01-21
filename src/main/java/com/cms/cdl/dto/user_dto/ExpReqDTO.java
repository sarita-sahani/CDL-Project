package com.cms.cdl.dto.user_dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExpReqDTO extends BaseEntityReqDTO{
    private String experience;
    private String companyName;
    private String companyAddress;
    private String dateOfJoining;
    private String dateOfReliving;
    private String jobTitle;
    private String certification;
//    @Column(nullable = true)
//    private Long expDocId;
}
