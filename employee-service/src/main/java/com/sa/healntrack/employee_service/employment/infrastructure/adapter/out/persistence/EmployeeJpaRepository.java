package com.sa.healntrack.employee_service.employment.infrastructure.adapter.out.persistence;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sa.healntrack.employee_service.employment.infrastructure.adapter.out.persistence.entity.EmployeeEntity;

public interface EmployeeJpaRepository extends JpaRepository<EmployeeEntity, UUID>, JpaSpecificationExecutor<EmployeeEntity> {
    Optional<EmployeeEntity> findByCui(String cui);
    Optional<EmployeeEntity> findByEmail(String email);
    boolean existsByNit(String nit);
    boolean existsByEmail(String email);
    boolean existsByCui(String cui);
    boolean existsByCuiAndIsActive(String cui, boolean isActive);
}
