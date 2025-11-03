package com.sa.healntrack.employee_service.vacation.infrastructure.adapter.in.rest.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ReviewVacationRequestDTO(

    @NotBlank(message = "El email del revisor es obligatorio")
    @Email(message = "El email del revisor debe ser válido")
    String reviewerEmail,

    @NotNull(message = "La fecha de revisión es obligatoria")
    LocalDate reviewedAt
) {}
