package com.cms.cdl.dto.request_dto.onboarding_req_dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EONOverallStatusReqDTO{
        @JsonProperty("CandID") String candID;
        @JsonProperty("JOB_ID") Integer jobId;
        @JsonProperty("JOB_APP_ID") Integer jobAppId;

        @JsonProperty("OverallStatus") String overallStatus;
        @JsonProperty("FilePhotoImage") String filePhotoImage;

        @JsonProperty("ONB_SumView") String onbSumView;
        @JsonProperty("PopupViewApp") String popupViewApp;

        @JsonProperty("ONB_TmplView") String onbTmplView;

        @JsonProperty("ONB_xlist") String onbxlist;
        @JsonProperty("PopupViewPost") String popupViewPost;

        @JsonProperty("ONB_admin") String onbAdmin;

        @JsonProperty("HRDeclStatus") String hrDeclStatus;
        @JsonProperty("Remarks") String remarks;

        @JsonProperty("GenCS") String genCS;
        @JsonProperty("GenAS") String genAS;

        @JsonProperty("PersCS") String persCS;
        @JsonProperty("PersAS") String persAS;

        @JsonProperty("EduCS") String eduCS;
        @JsonProperty("EduAS") String eduAS;

        @JsonProperty("SkillCS") String skillCS;
        @JsonProperty("SkillAS") String skillAS;

        @JsonProperty("FamilyCS") String familyCS;
        @JsonProperty("FamilyAS") String familyAS;

        @JsonProperty("BankCS") String bankCS;
        @JsonProperty("BankAS") String bankAS;

        @JsonProperty("PfCS") String pfCS;
        @JsonProperty("PfAS") String pfAS;

        @JsonProperty("LangCS") String langCS;
        @JsonProperty("LangAS") String langAS;

        @JsonProperty("IntCS") String intCS;
        @JsonProperty("IntAS") String intAS;

        @JsonProperty("ProfExpCS") String profExpCS;
        @JsonProperty("ProfExpAS") String profExpAS;

        @JsonProperty("RefCS") String refCS;
        @JsonProperty("RefAS") String refAS;

        @JsonProperty("DocProofCS") String docProofCS;
        @JsonProperty("DocProofAS") String docProofAS;

        @JsonProperty("QuesCS") String quesCS;
        @JsonProperty("QuesAS") String quesAS;

        @JsonProperty("CandDeclCS") String candDeclCS;
        @JsonProperty("CandDeclAS") String candDeclAS;

        @JsonProperty("OWNER_USERID") String ownerUserId;
        @JsonProperty("OWNER_USERORG") String ownerUserOrg;

    public static EONOverallStatusReqDTO empty() {
        return new EONOverallStatusReqDTO(
                "", null, null, "", "", "", "",
                "", "", "", "", "", "", "", "",
                "","","","","","","","","","", "", "", "", "", "",
                "","","","","","","","","","", "", "", ""

        );
    }
}
