package com.cms.cdl.dto.user_dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddReqDTO extends BaseEntityReqDTO{
    private String houseNo;
    private String street1;
    private String street2;
    private String landmark;
    private String country;
    private String state;
    private String district;
    private String city;
    private Integer pinCode;
}
