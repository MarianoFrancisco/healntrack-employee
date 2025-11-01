package com.sa.healntrack.employee_service.employment.infrastructure.adapter.in.rest.dto;

import java.time.LocalDate;

public record FindAllDepartmentManagersRequestDTO(
    String employee,
    String department,
    LocalDate startDateFrom,
    LocalDate startDateTo,
    LocalDate endDateFrom,
    LocalDate endDateTo,
    Boolean isActive
) {}
