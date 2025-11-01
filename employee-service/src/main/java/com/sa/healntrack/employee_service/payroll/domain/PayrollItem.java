package com.sa.healntrack.employee_service.payroll.domain;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

import com.sa.healntrack.employee_service.employment_period.domain.Employee;

import lombok.Getter;

@Getter
public class PayrollItem {
    private final PayrollItemId id;
    private final Payroll payroll;
    private final Employee employee;
    private final BigDecimal grossSalary;
    private final BigDecimal igssDeduction;
    private final BigDecimal irtraDeduction;
    private final BigDecimal netSalary;
    private final String notes;
    public PayrollItem(
            UUID id,
            Payroll payroll,
            Employee employee,
            BigDecimal grossSalary,
            BigDecimal igssDeduction,
            BigDecimal irtraDeduction,
            BigDecimal netSalary,
            String notes) {
        this.id = new PayrollItemId(id);
        this.payroll = Objects.requireNonNull(payroll, "La nomina no puede ser nula");
        this.employee = Objects.requireNonNull(employee, "El empleado no puede ser nulo");
        this.grossSalary = Objects.requireNonNull(grossSalary, "El salario bruto no puede ser nulo");
        this.igssDeduction = Objects.requireNonNull(igssDeduction, "La deducción de IGSS no puede ser nula");
        this.irtraDeduction = Objects.requireNonNull(irtraDeduction, "La deducción de IRTRA no puede ser nula");
        this.netSalary = Objects.requireNonNull(netSalary, "El salario neto no puede ser nulo");
        this.notes = notes;
    }

}
