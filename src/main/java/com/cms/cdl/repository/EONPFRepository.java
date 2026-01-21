package com.cms.cdl.repository;

import com.cms.cdl.dto.request_dto.onboarding_req_dto.EONPFReqDTO;
import com.cms.cdl.model.EONPF;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EONPFRepository extends JpaRepository<EONPF, String> {

    EONPF findByCandID(String candId);
}
