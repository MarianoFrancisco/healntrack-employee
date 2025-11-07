package com.sa.healntrack.employee_service.payroll.infrastructure.adapter.in.rest.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import com.sa.healntrack.employee_service.payroll.domain.PayrollType;

public record PayrollResponseDTO(
    UUID id,
    LocalDate startDate,
    LocalDate endDate,
    LocalDate payDay,
    PayrollType type,
    BigDecimal totalGrossAmount,
    BigDecimal totalIgssDeduction,
    BigDecimal totalIrtraDeduction,
    BigDecimal totalNetAmount
) {}
