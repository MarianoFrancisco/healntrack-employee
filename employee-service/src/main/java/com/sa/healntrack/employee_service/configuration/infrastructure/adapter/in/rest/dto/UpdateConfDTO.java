package com.sa.healntrack.employee_service.configuration.infrastructure.adapter.in.rest.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record UpdateConfDTO(
    @NotNull(message = "El valor no puede ser nulo")
    @Min(value = 1, message = "El valor debe ser mayor que cero")
    Integer value
) {}
