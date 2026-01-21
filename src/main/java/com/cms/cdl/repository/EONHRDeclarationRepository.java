package com.cms.cdl.repository;

import com.cms.cdl.dto.request_dto.onboarding_req_dto.EONHRDeclReqDTO;
import com.cms.cdl.model.EONHRDeclaration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EONHRDeclarationRepository extends JpaRepository<EONHRDeclaration, String> {

    EONHRDeclaration findByCandID(String candId);

}
