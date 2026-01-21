package com.cms.cdl.dto.ItDeclr;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor


public class IT_Proof_InvestmentDTO {
    private Long documentProfId;
//    private Long empId;
private String empCode;

    private Double revisedAmount;
    private String financialYear;
    private Long itDecId;
    private Boolean status;  // integer
    private Boolean isSubmitted;

}
