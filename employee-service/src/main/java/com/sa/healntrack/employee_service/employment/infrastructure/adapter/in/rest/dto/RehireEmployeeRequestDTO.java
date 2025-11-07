package com.sa.healntrack.employee_service.employment.infrastructure.adapter.in.rest.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.*;

public record RehireEmployeeRequestDTO(
    @NotBlank(message = "El teléfono es obligatorio")
    @Pattern(regexp = "\\d{8}", message = "El teléfono debe tener 8 dígitos")
    String phoneNumber,

    @NotBlank(message = "El código es obligatorio")
    @Pattern(regexp = "^[A-Z]{3}-\\d{3}$", message = "El código debe tener el formato XXX-000")
    String departmentCode,

    @NotNull(message = "El salario no puede ser nulo")
    @DecimalMin(value = "0.0", inclusive = true, message = "El salario no puede ser negativo")
    BigDecimal newSalary,

    @DecimalMin(value = "0.0", inclusive = true, message = "El porcentaje de IGSS no puede ser negativo")
    @DecimalMax(value = "0.15", message = "El porcentaje de IGSS no puede ser mayor al 15%")
    BigDecimal igssPercent,

    @DecimalMin(value = "0.0", inclusive = true, message = "El porcentaje de IRTRA no puede ser negativo")
    @DecimalMax(value = "0.15", message = "El porcentaje de IRTRA no puede ser mayor al 15%")
    BigDecimal irtraPercent,
    
    LocalDate startDate,
    String notes
) {}
