package com.sa.healntrack.employee_service.payroll.domain;

import java.time.LocalDate;
import java.util.UUID;

import lombok.Getter;

@Getter
public class Payroll {
    private final PayrollId id;
    private final LocalDate startDate;
    private final LocalDate endDate;

    public Payroll(UUID id, LocalDate startDate, LocalDate endDate) {
        this.id = new PayrollId(id);
        this.startDate = validateStartDate(startDate);
        this.endDate = validateEndDate(endDate);
    }

   private LocalDate validateStartDate(LocalDate startDate) {
        if (startDate == null) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser nula");
        }
        if (startDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser futura");
        }
        return startDate;
    }

    private LocalDate validateEndDate(LocalDate endDate) {
        if (endDate == null) {
            throw new IllegalArgumentException("La fecha de fin no puede ser nula");
        }
        if (endDate.isBefore(this.startDate)) {
            throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la fecha de inicio");
        }
        return endDate;
    }
}
