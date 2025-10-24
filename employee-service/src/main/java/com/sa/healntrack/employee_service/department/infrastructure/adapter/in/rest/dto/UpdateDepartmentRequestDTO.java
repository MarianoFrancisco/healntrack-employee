package com.sa.healntrack.employee_service.department.infrastructure.adapter.in.rest.dto;

import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.NotBlank;

public record UpdateDepartmentRequestDTO(

        @NotBlank(message = "El código es obligatorio")
        @Pattern(regexp = "^[A-Z]{3}-\\d{3}$", message = "El código debe tener el formato XXX-000")
        String code,

        @Size(max = 50, message = "El nombre debe tener máximo 50 caracteres")
        String name,

        @Size(max = 255, message = "La descripción debe tener máximo 255 caracteres")
        String description
) {}
