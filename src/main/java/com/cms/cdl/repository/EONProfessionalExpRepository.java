package com.cms.cdl.repository;

import com.cms.cdl.dto.request_dto.onboarding_req_dto.EONProfExpReqDTO;
import com.cms.cdl.model.EONProfressionalExperience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EONProfessionalExpRepository extends JpaRepository<EONProfressionalExperience, String> {

    EONProfressionalExperience findByCandID(String candId);

}
