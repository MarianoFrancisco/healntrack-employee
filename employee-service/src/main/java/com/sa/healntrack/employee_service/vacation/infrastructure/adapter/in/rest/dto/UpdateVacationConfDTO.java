package com.sa.healntrack.employee_service.vacation.infrastructure.adapter.in.rest.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record UpdateVacationConfDTO(
    @NotNull(message = "El valor no puede ser nulo")
    @Min(value = 1, message = "El valor debe ser mayor que cero")
    Integer value
) {}
