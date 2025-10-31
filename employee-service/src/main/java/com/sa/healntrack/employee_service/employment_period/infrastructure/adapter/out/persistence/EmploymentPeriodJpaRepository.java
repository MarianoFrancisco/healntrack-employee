package com.sa.healntrack.employee_service.employment_period.infrastructure.adapter.out.persistence;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sa.healntrack.employee_service.employment_period.infrastructure.adapter.out.persistence.entity.EmployeeEntity;
import com.sa.healntrack.employee_service.employment_period.infrastructure.adapter.out.persistence.entity.EmploymentPeriodEntity;

public interface EmploymentPeriodJpaRepository extends JpaRepository<EmploymentPeriodEntity, UUID>, JpaSpecificationExecutor<EmploymentPeriodEntity> {
    Optional<EmploymentPeriodEntity> findByEmployeeAndEndDate(EmployeeEntity employee, LocalDate endDate);
}
