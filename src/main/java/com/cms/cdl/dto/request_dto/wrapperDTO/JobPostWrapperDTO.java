package com.cms.cdl.dto.request_dto.wrapperDTO;

import com.cms.cdl.dto.request_dto.display_dto.JobPostDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class JobPostWrapperDTO {
    @JsonProperty("retcode")
    private int retcode;

    @JsonProperty("retdata")
    private Object retdata; // This will capture either a List or a Map

    @JsonProperty("retmessage")
    private String retmessage;

    // Standard getters and setters for Object
    public Object getRetdata() {
        return retdata;
    }

    public void setRetdata(Object retdata) {
        this.retdata = retdata;
    }
}