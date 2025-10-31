package com.sa.healntrack.employee_service.employment_period.infrastructure.adapter.out.persistence;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sa.healntrack.employee_service.department.infrastructure.adapter.out.persistence.DepartmentEntity;
import com.sa.healntrack.employee_service.employment_period.infrastructure.adapter.out.persistence.entity.DepartmentManagerEntity;
import com.sa.healntrack.employee_service.employment_period.infrastructure.adapter.out.persistence.entity.EmployeeEntity;


public interface DepartmentManagerJpaRepository extends JpaRepository<DepartmentManagerEntity, UUID>, JpaSpecificationExecutor<DepartmentManagerEntity> {
    Optional<DepartmentManagerEntity> findByEmployeeAndIsActive(EmployeeEntity employee, boolean isActive);
    boolean existByDepartmentAndIsActive(DepartmentEntity department, boolean isActive);
}
