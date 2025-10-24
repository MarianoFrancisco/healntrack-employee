package com.sa.healntrack.employee_service.department.infrastructure.adapter.in.rest.dto;

public record FindDepartmentRequestDTO(
        String code,
        String name,
        Boolean isActive
) {}
