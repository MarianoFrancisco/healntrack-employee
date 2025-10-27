package com.sa.healntrack.employee_service.employment_period.domain;

import java.time.LocalDate;
import java.util.UUID;

import com.sa.healntrack.employee_service.employment_period.domain.value.DepartmentManagerId;

import lombok.Getter;

@Getter
public class DepartmentManager {
    private final DepartmentManagerId id;
    private final Employee employee;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private boolean isActive;

    public DepartmentManager(
            UUID id,
            Employee employee,
            LocalDate startDate) {
        this.id = new DepartmentManagerId(id);
        this.employee = employee;
        this.startDate = validateStartDate(startDate);
        this.endDate = validateEndDate(null);
        this.isActive = true;
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
        if (endDate != null && endDate.isBefore(this.startDate)) {
            throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la fecha de inicio");
        }
        return endDate;
    }
}
