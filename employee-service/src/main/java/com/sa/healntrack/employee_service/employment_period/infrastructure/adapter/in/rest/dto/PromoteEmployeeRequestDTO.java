package com.sa.healntrack.employee_service.employment_period.infrastructure.adapter.in.rest.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.*;

public record PromoteEmployeeRequestDTO(
    @NotNull(message = "Aumento de salario es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El aumento de salario debe ser mayor que 0")
    BigDecimal salaryIncrease,

    @NotBlank(message = "Código de departamento es obligatorio")
    @Pattern(regexp = "^[A-Z]{3}-\\d{3}$", message = "El código debe tener el formato XXX-000")
    String departmentCode,

    LocalDate date,
    String notes
) {
    
}
