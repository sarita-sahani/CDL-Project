package com.cms.cdl.repository;

import com.cms.cdl.dto.request_dto.onboarding_req_dto.EONPersonalReqDTO;
import com.cms.cdl.model.EONPersonal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EONPersonalRepository extends JpaRepository<EONPersonal, String> {

   EONPersonal findByCandID(String candId);


}
