package com.sa.healntrack.employee_service.employment_period.infrastructure.adapter.in.rest.dto;

import java.time.LocalDate;
import java.util.UUID;

public record DepartmentManagerResponseDTO(
    UUID id,
    String departmentCode,
    String departmentName,
    String employeeCui,
    String employeeFullName,
    LocalDate startDate,
    LocalDate endDate,
    boolean isActive
) {}
