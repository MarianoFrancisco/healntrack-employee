package com.sa.healntrack.employee_service.employment.infrastructure.adapter.out.persistence;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sa.healntrack.employee_service.employment.infrastructure.adapter.out.persistence.entity.EmployeeEntity;
import com.sa.healntrack.employee_service.employment.infrastructure.adapter.out.persistence.entity.EmploymentEntity;

public interface EmploymentJpaRepository extends JpaRepository<EmploymentEntity, UUID>, JpaSpecificationExecutor<EmploymentEntity> {
    Optional<EmploymentEntity> findByEmployeeAndEndDate(EmployeeEntity employee, LocalDate endDate);
}
