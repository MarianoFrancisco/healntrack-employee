package com.sa.healntrack.employee_service.employment.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

import com.sa.healntrack.employee_service.department.domain.Department;
import com.sa.healntrack.employee_service.employment.domain.value.EmploymentId;

import lombok.Getter;

@Getter
public class Employment {
    private final EmploymentId id;
    private final Employee employee;
    private final Department department;
    private final PeriodType type;
    private final LocalDate startDate;
    private LocalDate endDate;
    private final BigDecimal salary;
    private final String notes;

    public Employment(
            UUID id,
            Employee employee,
            PeriodType type,
            LocalDate startDate,
            BigDecimal salary,
            String notes) {
        this.id = new EmploymentId(id);
        this.employee = Objects.requireNonNull(employee, "El empleado no puede ser nulo");
        this.department = Objects.requireNonNull(employee.getDepartment(), "El departamento no puede ser nulo");
        this.type = Objects.requireNonNull(type, "El tipo del periodo no puede ser nulo");
        this.startDate = validateStartDate(startDate);
        this.endDate = null;
        this.salary = validateSalary(salary);
        this.notes = notes != null ? notes : "";
    }

    public void endPeriod(LocalDate endDate) {
        Objects.requireNonNull(endDate, "La fecha de fin no puede ser nula");
        if (endDate.isBefore(this.startDate)) {
            throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la fecha de inicio");
        }
        this.endDate = endDate;
    }

    private LocalDate validateStartDate(LocalDate startDate) {
        Objects.requireNonNull(startDate, "La fecha de inicio no puede ser nula");
        if (startDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser futura");
        }
        return startDate;
    }

    private BigDecimal validateSalary(BigDecimal salary) {
        if (salary == null || salary.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El salario no puede ser nulo o negativo");
        }
        return salary;
    }
}
