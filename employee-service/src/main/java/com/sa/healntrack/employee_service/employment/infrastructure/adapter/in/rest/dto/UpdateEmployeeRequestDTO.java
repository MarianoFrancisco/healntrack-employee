package com.sa.healntrack.employee_service.employment.infrastructure.adapter.in.rest.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.*;

public record UpdateEmployeeRequestDTO(
    @Size(max = 200, message = "El nombre completo no puede superar 200 caracteres")
    String fullname,

    @Pattern(regexp = "\\d{8}", message = "El teléfono debe tener 8 dígitos")
    String phoneNumber,

    @DecimalMin(value = "0.0", inclusive = true, message = "El porcentaje de IGSS no puede ser negativo")
    @DecimalMax(value = "0.15", message = "El porcentaje de IGSS no puede ser mayor al 15%")
    BigDecimal igssPercent,

    @DecimalMin(value = "0.0", inclusive = true, message = "El porcentaje de IRTRA no puede ser negativo")
    @DecimalMax(value = "0.15", message = "El porcentaje de IRTRA no puede ser mayor al 15%")
    BigDecimal irtraPercent
) {}
