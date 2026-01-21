package com.cms.cdl.mapper;

import com.cms.cdl.dto.request_dto.onboarding_req_dto.*;
import com.cms.cdl.dto.response_dto.*;
import com.cms.cdl.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@Mapper(componentModel = "spring")
public interface OnBoardingMapper {

    @Mapping(source = "dob", target = "dob", qualifiedByName = "mapDobToDate")
    EONPersonal toEntity(EONPersonalReqDTO eonPersonalReqDTO);
    EONPersonalResDTO toEonPersonalRes(EONPersonal eonPersonal);
    EONGeneral toEonGeneralEntity(EONGeneralReqDTO eonGeneralReqDTO);
    EONGeneralResDTO toEonGeneralRes(EONGeneral eonGeneral);

    EONEducationResDTO toEonEducationRes(EONEducation eonGeneral);

    EONSkill toEonSkillEntity(EONSkillReqDTO eonSkillReqDTO);

    EONSkillResDTO toEonSkillRes(EONSkill eonSkill);

    EONFamily toEonFamilyEntity(EONFamilyReqDTO eonFamilyReqDTO);

    EONFamilyResDTO toEonFamilyRes(EONFamily eonFamily);

    EONBank toEonBankEntity(EONBankReqDTO eonBankReqDTO);

    EONBankResDTO toEonBankRes(EONBank eonBank);

    EONPF toEonPfEntity(EONPFReqDTO eonpfReqDTO);

    EONPfResDTO toEonPfRes(EONPF eonpf);

    EONLanguage toEonLangEntity(EONLanguageReqDTO eonLanguageReqDTO);

    EONLanguageResDTO toEonLangRes(EONLanguage eonLanguage);

    EONInterest toEonInterestEntity(EONInterestReqDTO eonInterestReqDTO);

    EONInterestResDTO toEonInterestRes(EONInterest eonInterest);

    EONProfressionalExperience toEonProfExpEntity(EONProfExpReqDTO eonProfExpReqDTO);

    EONProfExpResDTO toEonProfExpRes(EONProfressionalExperience eonProfressionalExperience);

    EONProfessionalReference toEonProfRefEntity(EONProfRefReqDTO eonProfRefReqDTO);

    EONProfRefResDTO toEonProfRefRes(EONProfessionalReference eonProfessionalReference);

    EONDocumentProof toEonDocsProofEntity(EONDocsProofReqDTO eonDocsProofReqDTO);

    EONDocProofResDTO toEonDocsProofRes(EONDocumentProof eonDocumentProof);

    EONDeclQuestionnaire toEonDeclQnrEntity(EONDeclQnrReqDTO eonDeclQnrReqDTO);

    EONDeclQnrResDTO toEonDeclQnrRes(EONDeclQuestionnaire eonDeclQuestionnaire);

    EONCandDeclResDTO toEonCandDeclarationRes(EONCandidateDeclaration eonCandidateDeclaration);

    EONHRDeclaration toEonHrDeclarationEntity(EONHRDeclReqDTO eonhrDeclReqDTO);

    EONHrDeclResDTO toEonHrDeclarationRes(EONHRDeclaration eonhrDeclaration);


    EONGeneral toGeneralEntity(EONGeneralReqDTO eonGeneralReqDTO);

    EONPersonal toPersonalEntity(EONPersonalReqDTO eonPersonalReqDTO);

    EONEducation toEducationEntity(EONEducationReqDTO eonEducationReqDTO);

    EONSkill toSkillEntity(EONSkillReqDTO dto);
    EONFamily toFamilyEntity(EONFamilyReqDTO dto);
    EONBank toBankEntity(EONBankReqDTO dto);
    EONPF toPFEntity(EONPFReqDTO dto);
    EONLanguage toLanguageEntity(EONLanguageReqDTO dto);
    EONInterest  toInterestEntity(EONInterestReqDTO dto);
    EONProfressionalExperience toProfExpEntity(EONProfExpReqDTO dto);
    EONProfessionalReference toProfRefEntity(EONProfRefReqDTO dto);
//    EONDocumentProof toDocsProofEntity(EONDocsProofReqDTO dto);
    EONDeclQuestionnaire toDeclQnrEntity(EONDeclQnrReqDTO dto);
    EONCandidateDeclaration toCandDeclEntity(EONCandDeclReqDTO dto);
    EONHRDeclaration toHRDeclEntity(EONHRDeclReqDTO dto);
    EONOverallStatus toOverallStatusEntity(EONOverallStatusReqDTO dto);

    @Named("mapDobToDate")
    default LocalDate mapDobToDate(String dob) {
        if (dob == null || dob.isBlank()) return null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss z", Locale.ENGLISH);
        return LocalDate.parse(dob, formatter);
    }

    // This will copy non-null fields from source to target
    void updateEmployeeOnboardingInfoFromDto(EmployeeOnboardingInfo source,
                                             @MappingTarget EmployeeOnboardingInfo target);
}