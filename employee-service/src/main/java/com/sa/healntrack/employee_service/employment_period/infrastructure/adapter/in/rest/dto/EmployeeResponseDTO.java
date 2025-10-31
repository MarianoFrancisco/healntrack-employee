package com.sa.healntrack.employee_service.employment_period.infrastructure.adapter.in.rest.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import com.sa.healntrack.employee_service.department.infrastructure.adapter.in.rest.dto.DepartmentResponseDTO;

public record EmployeeResponseDTO(
    UUID id,
    String cui,
    String nit,
    String fullname,
    String email,
    String phoneNumber,
    LocalDate birthDate,
    DepartmentResponseDTO department,
    BigDecimal salary,
    BigDecimal igssPercent,
    BigDecimal irtraPercent,
    boolean isActive
) {}
