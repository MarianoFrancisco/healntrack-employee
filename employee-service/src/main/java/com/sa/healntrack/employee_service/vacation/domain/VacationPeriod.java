package com.sa.healntrack.employee_service.vacation.domain;

import java.time.LocalDate;
import java.util.Objects;

public record VacationPeriod(LocalDate startDate, LocalDate endDate) {
    public VacationPeriod {
        Objects.requireNonNull(startDate, "La fecha de inicio no puede ser nula");
        Objects.requireNonNull(endDate, "La fecha de fin no puede ser nula");
        
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser posterior a la fecha de fin");
        }
    }
}
