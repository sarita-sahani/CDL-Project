package com.cms.cdl.dto.request_dto.wrapperDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenericWrapperDTO<T> {
    private int retcode;
    private List<T> retdata;
}
