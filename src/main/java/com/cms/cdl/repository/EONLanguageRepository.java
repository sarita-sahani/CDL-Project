package com.cms.cdl.repository;

import com.cms.cdl.dto.request_dto.onboarding_req_dto.EONLanguageReqDTO;
import com.cms.cdl.model.EONLanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EONLanguageRepository extends JpaRepository<EONLanguage, String> {

    EONLanguage findByCandID(String candId);

}
