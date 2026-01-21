package com.cms.cdl.repository;

import com.cms.cdl.dto.request_dto.onboarding_req_dto.EONCandDeclReqDTO;
import com.cms.cdl.model.EONCandidateDeclaration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EONCandDeclRepository extends JpaRepository<EONCandidateDeclaration, String> {
    EONCandidateDeclaration findByCandID(String candId);

}
