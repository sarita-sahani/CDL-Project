package com.cms.cdl.dto.user_dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationReqDTO {
    private String country;
    private String state;
    private String district;
    private String city;
    private String locationName;
}
