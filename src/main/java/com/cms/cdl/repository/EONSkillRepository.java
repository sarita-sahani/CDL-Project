package com.cms.cdl.repository;

import com.cms.cdl.dto.request_dto.onboarding_req_dto.EONSkillReqDTO;
import com.cms.cdl.model.EONSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EONSkillRepository extends JpaRepository<EONSkill, String> {

    EONSkill findByCandID(String candId);

}
