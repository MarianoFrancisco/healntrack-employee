package com.sa.healntrack.employee_service.employment_period.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

import com.sa.healntrack.employee_service.employment_period.domain.value.EmploymentPeriodId;

import lombok.Getter;

@Getter
public class EmploymentPeriod {
    private final EmploymentPeriodId id;
    private final Employee employee;
    private final PeriodType periodType;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final BigDecimal salary;
    private final String notes;

    public EmploymentPeriod(
            UUID id,
            Employee employee,
            PeriodType periodType,
            LocalDate startDate,
            LocalDate endDate,
            BigDecimal salary,
            String notes) {
        this.id = new EmploymentPeriodId(id);
        this.employee = Objects.requireNonNull(employee, "El empleado no puede ser nulo");
        this.periodType = Objects.requireNonNull(periodType, "El tipo del periodo no puede ser nulo");
        this.startDate = validateStartDate(startDate);
        this.endDate = validateEndDate(endDate);
        this.salary = validateSalary(salary);
        this.notes = notes;
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

    private BigDecimal validateSalary(BigDecimal salary) {
        if (salary == null || salary.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El salario no puede ser nulo o negativo");
        }
        return salary;
    }
}
