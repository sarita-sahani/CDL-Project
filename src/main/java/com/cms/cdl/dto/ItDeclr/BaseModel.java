package com.cms.cdl.dto.ItDeclr;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;

@Data
@MappedSuperclass
public class BaseModel {
    @Column(updatable = false)
    private LocalDate createdDate;
    @LastModifiedDate
    @Column(insertable = false)
    private LocalDate modifiedDate;
}