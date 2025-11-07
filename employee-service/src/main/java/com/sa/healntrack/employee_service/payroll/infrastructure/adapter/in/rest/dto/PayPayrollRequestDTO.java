package com.sa.healntrack.employee_service.payroll.infrastructure.adapter.in.rest.dto;

import java.time.LocalDate;

import com.sa.healntrack.employee_service.payroll.domain.PayrollType;

import jakarta.validation.constraints.NotNull;


public record PayPayrollRequestDTO(
    @NotNull(message = "Fecha de inicio del periodo es obligatoria")
    LocalDate startDate,

    @NotNull(message = "Fecha de fin del periodo es obligatoria")
    LocalDate endDate,

    @NotNull(message = "Fecha de pago es obligatoria")
    LocalDate payDay,

    @NotNull(message = "Tipo de nómina es obligatorio")
    PayrollType type
) {}
