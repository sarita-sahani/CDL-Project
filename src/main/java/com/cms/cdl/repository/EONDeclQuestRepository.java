package com.cms.cdl.repository;

import com.cms.cdl.dto.request_dto.onboarding_req_dto.EONDeclQnrReqDTO;
import com.cms.cdl.model.EONDeclQuestionnaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EONDeclQuestRepository extends JpaRepository<EONDeclQuestionnaire, String> {

    EONDeclQuestionnaire findByCandID(String candId);

}
