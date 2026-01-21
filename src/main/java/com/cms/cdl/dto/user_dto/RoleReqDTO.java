package com.cms.cdl.dto.user_dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RoleReqDTO extends BaseEntityReqDTO {
    private String name;
}
