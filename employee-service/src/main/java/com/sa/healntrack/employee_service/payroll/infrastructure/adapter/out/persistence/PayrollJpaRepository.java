package com.sa.healntrack.employee_service.payroll.infrastructure.adapter.out.persistence;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PayrollJpaRepository extends JpaRepository<PayrollEntity, UUID>, JpaSpecificationExecutor<PayrollEntity> {
    boolean existsByStartDateAndEndDate(LocalDate startDate, LocalDate endDate);
}
