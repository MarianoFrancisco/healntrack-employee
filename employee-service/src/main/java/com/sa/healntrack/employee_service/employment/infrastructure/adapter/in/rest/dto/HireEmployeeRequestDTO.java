package com.sa.healntrack.employee_service.employment.infrastructure.adapter.in.rest.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public record HireEmployeeRequestDTO(

    @NotBlank(message = "El CUI es obligatorio")
    @Size(min = 13, max = 13, message = "El CUI debe tener 13 caracteres")
    String cui,

    @NotBlank(message = "El NIT es obligatorio")
    @Size(min = 9, max = 9, message = "El NIT debe tener 9 caracteres")
    String nit,

    @NotBlank(message = "El nombre completo es obligatorio")
    @Size(max = 200, message = "El nombre completo no puede superar 200 caracteres")
    String fullname,

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email debe ser válido")
    @Size(max = 100, message = "El email no puede superar 100 caracteres")
    String email,

    @NotBlank(message = "El teléfono es obligatorio")
    @Pattern(regexp = "\\d{8}", message = "El teléfono debe tener 8 dígitos")
    String phoneNumber,

    @NotNull(message = "La fecha de nacimiento no puede ser nula")
    @Past(message = "La fecha de nacimiento debe ser anterior a hoy")
    LocalDate birthDate,

    @NotBlank(message = "El código es obligatorio")
    @Pattern(regexp = "^[A-Z]{3}-\\d{3}$", message = "El código debe tener el formato XXX-000")
    String departmentCode,

    LocalDate startDate,

    @NotNull(message = "El salario no puede ser nulo")
    @DecimalMin(value = "0.0", inclusive = true, message = "El salario no puede ser negativo")
    BigDecimal salary,

    @DecimalMin(value = "0.0", inclusive = true, message = "El porcentaje de IGSS no puede ser negativo")
    @DecimalMax(value = "0.15", message = "El porcentaje de IGSS no puede ser mayor al 15%")
    BigDecimal igssPercent,

    @DecimalMin(value = "0.0", inclusive = true, message = "El porcentaje de IRTRA no puede ser negativo")
    @DecimalMax(value = "0.15", message = "El porcentaje de IRTRA no puede ser mayor al 15%")
    BigDecimal irtraPercent,

    String notes
) {}

