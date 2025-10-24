package com.sa.healntrack.employee_service.department.infrastructure.adapter.out.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DepartmentJpaRepository extends JpaRepository<DepartmentEntity, String>, JpaSpecificationExecutor<DepartmentEntity> {
    Optional<DepartmentEntity> findByCode(String code);
    boolean existsByCode(String code);
}
