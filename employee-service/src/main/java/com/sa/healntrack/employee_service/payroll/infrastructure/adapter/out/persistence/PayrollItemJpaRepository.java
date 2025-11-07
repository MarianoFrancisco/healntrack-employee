package com.sa.healntrack.employee_service.payroll.infrastructure.adapter.out.persistence;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PayrollItemJpaRepository extends JpaRepository<PayrollItemEntity, UUID>, JpaSpecificationExecutor<PayrollItemEntity> {
    
}
