package com.sa.healntrack.employee_service.department.infrastructure.adapter.in.rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CreateDepartmentRequestDTO(

        @NotBlank(message = "El nombre es obligatorio")
        @Size(max = 50, message = "El nombre debe tener máximo 50 caracteres")
        String name,

        @NotBlank(message = "El código es obligatorio")
        @Pattern(regexp = "^[A-Z]{3}-\\d{3}$", message = "El código debe tener el formato XXX-000")
        String code,

        @Size(max = 255, message = "La descripción debe tener máximo 255 caracteres")
        String description
) {}

