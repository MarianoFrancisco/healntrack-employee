package com.sa.healntrack.employee_service.vacation.infrastructure.adapter.in.rest.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.*;

public record RequestVacationRequestDTO(
    @NotBlank(message = "El cui del empleado es obligatorio")
    @Size(min = 13, max = 13, message = "El CUI debe tener 13 caracteres")
    String employeeCui,

    @NotNull(message = "La fecha de solicitud es obligatoria")
    LocalDate requestedAt,

    @NotNull(message = "La fecha de inicio es obligatoria")
    LocalDate startDate,
    
    @NotNull(message = "La fecha de fin es obligatoria")
    LocalDate endDate
) {}
