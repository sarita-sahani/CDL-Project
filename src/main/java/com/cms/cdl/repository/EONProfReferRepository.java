package com.cms.cdl.repository;

import com.cms.cdl.dto.request_dto.onboarding_req_dto.EONProfRefReqDTO;
import com.cms.cdl.model.EONProfessionalReference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EONProfReferRepository extends JpaRepository<EONProfessionalReference, String> {
    EONProfessionalReference findByCandID(String candId);

}
