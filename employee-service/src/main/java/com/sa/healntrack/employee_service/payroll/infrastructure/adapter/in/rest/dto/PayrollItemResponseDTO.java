package com.sa.healntrack.employee_service.payroll.infrastructure.adapter.in.rest.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record PayrollItemResponseDTO(
    UUID id,
    LocalDate startDate,
    LocalDate endDate,
    LocalDate payDay,
    String employeeCui,
    String employeeName,
    String departmentCode,
    String departmentName,
    BigDecimal grossSalary,
    BigDecimal igssDeduction,
    BigDecimal irtraDeduction,
    BigDecimal netSalary,
    String notes
) {}
