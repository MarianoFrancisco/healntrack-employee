package com.sa.healntrack.employee_service.payroll.domain;

import java.time.LocalDate;
import java.util.Objects;

public record PayrollPeriod(LocalDate startDate, LocalDate endDate) {
    public PayrollPeriod {
        Objects.requireNonNull(startDate, "La fecha de inicio no puede ser nula");
        Objects.requireNonNull(endDate, "La fecha de fin no puede ser nula");
        if (startDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser futura");
        }
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser posterior a la fecha de fin");
        }
    }
}
