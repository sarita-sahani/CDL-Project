package com.cms.cdl.repository;

import com.cms.cdl.dto.request_dto.onboarding_req_dto.EONFamilyReqDTO;
import com.cms.cdl.model.EONFamily;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EONFamilyRepository extends JpaRepository<EONFamily, String> {
    EONFamily findByCandID(String candId);

}
