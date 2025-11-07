package com.sa.healntrack.employee_service.vacation.infrastructure.adapter.in.rest.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ReviewVacationRequestDTO(

    @NotBlank(message = "El CUI del revisor es obligatorio")
    @Size(min = 13, max = 13, message = "El CUI debe tener 13 caracteres")
    String reviewerCui,

    @NotNull(message = "La fecha de revisión es obligatoria")
    LocalDate reviewedAt
) {}
