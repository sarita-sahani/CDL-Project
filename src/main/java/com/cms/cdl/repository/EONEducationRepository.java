package com.cms.cdl.repository;

import com.cms.cdl.dto.request_dto.onboarding_req_dto.EONEducationReqDTO;
import com.cms.cdl.model.EONEducation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EONEducationRepository extends JpaRepository<EONEducation, String> {
    EONEducation findByCandID(String candId);

}
