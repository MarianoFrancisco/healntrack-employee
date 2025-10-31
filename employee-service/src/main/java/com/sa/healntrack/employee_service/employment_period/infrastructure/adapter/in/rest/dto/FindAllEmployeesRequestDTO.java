package com.sa.healntrack.employee_service.employment_period.infrastructure.adapter.in.rest.dto;

public record FindAllEmployeesRequestDTO(
    String q,
    String department,
    Boolean isActive
) {}
