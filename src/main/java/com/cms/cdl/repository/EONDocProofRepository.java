package com.cms.cdl.repository;

import com.cms.cdl.dto.request_dto.onboarding_req_dto.EONDocsProofReqDTO;
import com.cms.cdl.model.EONDocumentProof;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EONDocProofRepository extends JpaRepository<EONDocumentProof,Long> {

    EONDocumentProof findByCandID(String candId);

}
