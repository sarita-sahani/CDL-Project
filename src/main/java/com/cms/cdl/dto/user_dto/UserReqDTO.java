package com.cms.cdl.dto.user_dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserReqDTO extends BaseEntityReqDTO {
    private long userId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String personalEmail;
    private String primaryContactNo;
    private String secondaryContactNo;
    private String gender;
    private String dateOfBirth;
    private Long locationId;
    private List<Long> roleId;
    private List<AddReqDTO> addReqDTOList;
    private List<EducationReqDTO> educationReqDTOList;
    private List<ExpReqDTO> expReqDTOList;
    private List<SkillReqDTO> skillReqDTOList;
    private StatutoryDetReqDTO statutoryDetReqDTO;
}
