package com.cms.cdl.repository;

import com.cms.cdl.dto.request_dto.onboarding_req_dto.EONOverallStatusReqDTO;
import com.cms.cdl.model.EONOverallStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EONOverallStatusRepository extends JpaRepository<EONOverallStatus,Long> {

    EONOverallStatus findByCandID(String candId);






}


