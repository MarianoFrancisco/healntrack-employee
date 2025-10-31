package com.sa.healntrack.employee_service.employment_period.application.port.out;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.sa.healntrack.employee_service.employment_period.domain.DepartmentManager;

public interface FindDepartmentManagers {
    List<DepartmentManager> findAllDepartmentManagers();
    Optional<DepartmentManager> findDepartmentManagerById(UUID id);
    boolean existByDepartmentCodeAndIsActive(String departmentCode, boolean isActive);
}
