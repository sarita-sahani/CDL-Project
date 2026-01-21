package com.cms.cdl.repository;


import com.cms.cdl.model.EmployeeOnboardingInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeOnboardingRepository extends JpaRepository<EmployeeOnboardingInfo, Long> {
    Optional<EmployeeOnboardingInfo> findByEmpOnboardingRefNum(String empOnboardingRefNum);

    EmployeeOnboardingInfo findByCandID(String candId);

    void deleteByCandID(String candID);


}
