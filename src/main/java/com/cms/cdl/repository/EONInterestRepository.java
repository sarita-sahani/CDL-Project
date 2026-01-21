package com.cms.cdl.repository;

import com.cms.cdl.dto.request_dto.onboarding_req_dto.EONInterestReqDTO;
import com.cms.cdl.model.EONInterest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EONInterestRepository extends JpaRepository<EONInterest, String> {

    EONInterest findByCandID(String candId);

}
