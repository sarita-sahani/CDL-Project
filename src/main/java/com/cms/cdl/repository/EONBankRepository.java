package com.cms.cdl.repository;

import com.cms.cdl.dto.request_dto.onboarding_req_dto.EONBankReqDTO;
import com.cms.cdl.model.EONBank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EONBankRepository extends JpaRepository<EONBank, String> {

   EONBank findByCandID(String candId);

}
