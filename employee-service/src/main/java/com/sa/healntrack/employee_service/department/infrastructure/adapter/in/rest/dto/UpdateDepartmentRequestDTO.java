package com.sa.healntrack.employee_service.department.infrastructure.adapter.in.rest.dto;

import jakarta.validation.constraints.Size;

public record UpdateDepartmentRequestDTO(

        @Size(max = 50, message = "El nombre debe tener máximo 50 caracteres")
        String name,

        @Size(max = 255, message = "La descripción debe tener máximo 255 caracteres")
        String description
) {}
