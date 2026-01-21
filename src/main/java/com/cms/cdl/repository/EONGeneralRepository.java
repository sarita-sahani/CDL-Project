package com.cms.cdl.repository;

import com.cms.cdl.dto.request_dto.onboarding_req_dto.EONGeneralReqDTO;
import com.cms.cdl.model.EONGeneral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EONGeneralRepository extends JpaRepository<EONGeneral,Long> {

    EONGeneral findByCandID(String candId);


}
