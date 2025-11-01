package com.sa.healntrack.employee_service.employment.infrastructure.adapter.in.rest.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import com.sa.healntrack.employee_service.employment.domain.PeriodType;

public record EmploymentResponseDTO(
    UUID id,
    String employeeCUi,
    String employeeFullname,
    String departmentCode,
    String departmentName,
    PeriodType type,
    LocalDate startDate,
    LocalDate endDate,
    BigDecimal salary,
    String notes
) {}
