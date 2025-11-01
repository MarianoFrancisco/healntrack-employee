package com.sa.healntrack.employee_service.employment_period.infrastructure.adapter.in.rest.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.*;

public record UpdateEmployeeRequestDTO(
    @Size(max = 200, message = "El nombre completo no puede superar 200 caracteres")
    String fullname,

    @Pattern(regexp = "\\d{8}", message = "El teléfono debe tener 8 dígitos")
    String phoneNumber,

    @DecimalMin(value = "0", message = "El porcentaje de IGSS debe ser positivo")
    @DecimalMax(value = "0.1", message = "El porcentaje de IGSS no puede ser mayor a 0.1")
    BigDecimal igssPercent,

    @DecimalMin(value = "0", message = "El porcentaje de IRTRA debe ser positivo")
    @DecimalMax(value = "0.1", message = "El porcentaje de IRTRA no puede ser mayor a 0.1")
    BigDecimal irtraPercent
) {}
