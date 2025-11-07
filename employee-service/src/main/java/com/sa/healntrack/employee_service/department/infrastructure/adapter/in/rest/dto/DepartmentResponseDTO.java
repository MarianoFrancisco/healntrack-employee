package com.sa.healntrack.employee_service.department.infrastructure.adapter.in.rest.dto;

public record DepartmentResponseDTO(
        String code,
        String name,
        String description,
        boolean isActive
) {}

