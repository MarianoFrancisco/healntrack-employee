package com.sa.healntrack.employee_service.employment.infrastructure.adapter.in.rest.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.*;

public record SalaryIncreaseRequestDTO(
    @NotNull(message = "El aumento de salario es obligatorio")
    @DecimalMin(value = "0.0", inclusive = true, message = "El aumento de salario no puede ser negativo")
    BigDecimal salaryIncrease,
    
    @NotNull(message = "La fecha del aumento es obligatoria")
    LocalDate date,
    String notes
) {}
