package com.cms.cdl.configuration;

import com.cms.cdl.deserializer.GenericRetdataDeserializer;
import com.cms.cdl.dto.request_dto.onboarding_req_dto.*;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class RetdataModule extends SimpleModule {
    public RetdataModule() {
        // Register one for each DTO

        SimpleModule module = new SimpleModule();
        module.addDeserializer(EONPersonalReqDTO.class, new GenericRetdataDeserializer<>(EONPersonalReqDTO.class));
        module.addDeserializer(EONGeneralReqDTO.class, new GenericRetdataDeserializer<>(EONGeneralReqDTO.class));
        module.addDeserializer(EONEducationReqDTO.class, new GenericRetdataDeserializer<>(EONEducationReqDTO.class));
        module.addDeserializer(EONSkillReqDTO.class, new GenericRetdataDeserializer<>(EONSkillReqDTO.class));
        module.addDeserializer(EONFamilyReqDTO.class, new GenericRetdataDeserializer<>(EONFamilyReqDTO.class));
        module.addDeserializer(EONBankReqDTO.class, new GenericRetdataDeserializer<>(EONBankReqDTO.class));
        module.addDeserializer(EONPFReqDTO.class, new GenericRetdataDeserializer<>(EONPFReqDTO.class));
        module.addDeserializer(EONLanguageReqDTO.class, new GenericRetdataDeserializer<>(EONLanguageReqDTO.class));
        module.addDeserializer(EONInterestReqDTO.class, new GenericRetdataDeserializer<>(EONInterestReqDTO.class));
        module.addDeserializer(EONProfExpReqDTO.class, new GenericRetdataDeserializer<>(EONProfExpReqDTO.class));
        module.addDeserializer(EONProfRefReqDTO.class, new GenericRetdataDeserializer<>(EONProfRefReqDTO.class));
        module.addDeserializer(EONDocsProofReqDTO.class, new GenericRetdataDeserializer<>(EONDocsProofReqDTO.class));
        module.addDeserializer(EONDeclQnrReqDTO.class, new GenericRetdataDeserializer<>(EONDeclQnrReqDTO.class));
        module.addDeserializer(EONCandDeclReqDTO.class, new GenericRetdataDeserializer<>(EONCandDeclReqDTO.class));
        module.addDeserializer(EONHRDeclReqDTO.class, new GenericRetdataDeserializer<>(EONHRDeclReqDTO.class));
    }
}