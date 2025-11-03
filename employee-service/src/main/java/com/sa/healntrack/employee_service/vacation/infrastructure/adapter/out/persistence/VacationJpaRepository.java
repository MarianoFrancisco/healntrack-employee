package com.sa.healntrack.employee_service.vacation.infrastructure.adapter.out.persistence;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sa.healntrack.employee_service.employment.infrastructure.adapter.out.persistence.entity.EmployeeEntity;
import com.sa.healntrack.employee_service.vacation.domain.VacationStatus;

public interface VacationJpaRepository extends JpaRepository<VacationEntity, UUID>, JpaSpecificationExecutor<VacationEntity>{
    boolean existsByEmployeeAndStatus(EmployeeEntity employee, VacationStatus status);
}
