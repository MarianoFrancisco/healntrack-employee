package com.sa.healntrack.employee_service.payroll.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import com.sa.healntrack.employee_service.employment.domain.Employee;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(of = "id")
public class Payroll {
    private final PayrollId id;
    private final Set<PayrollItem> items;
    private final PayrollPeriod period;
    private final LocalDate payDay;
    private final PayrollType type;
    private BigDecimal totalGrossAmount;
    private BigDecimal totalIgssDeduction;
    private BigDecimal totalIrtraDeduction;
    private BigDecimal totalNetAmount;

    public Payroll(UUID id, Set<PayrollItem> items, LocalDate startDate, LocalDate endDate, LocalDate payDay,
            PayrollType type,
            BigDecimal totalGrossAmount, BigDecimal totalIgssDeduction, BigDecimal totalIrtraDeduction,
            BigDecimal totalNetAmount) {
        this.id = new PayrollId(id);
        this.items = items != null && !items.isEmpty() ? new HashSet<>(items) : new HashSet<>();
        this.period = new PayrollPeriod(startDate, endDate);
        this.payDay = validatePayDay(payDay);
        this.type = Objects.requireNonNull(type, "Tipo de nomina no puede ser nulo");
        this.totalGrossAmount = totalGrossAmount != null ? totalGrossAmount : BigDecimal.ZERO;
        this.totalIgssDeduction = totalIgssDeduction != null ? totalIgssDeduction : BigDecimal.ZERO;
        this.totalIrtraDeduction = totalIrtraDeduction != null ? totalIrtraDeduction : BigDecimal.ZERO;
        this.totalNetAmount = totalNetAmount != null ? totalNetAmount : BigDecimal.ZERO;
    }

    public Payroll(UUID id, LocalDate startDate, LocalDate endDate, LocalDate payDay, PayrollType type) {
        this(id, null, startDate, endDate, payDay, type, null, null, null, null);
    }

    public void addItem(Employee employee, String notes) {
        Objects.requireNonNull(employee, "El empleado no puede ser nulo");

        if (!employee.isActive()) {
            throw new IllegalArgumentException(
                    "No se encuentra contratado actualmente el empleado con cui: " + employee.getCui().value());
        }

        BigDecimal gross = employee.getSalary();
        BigDecimal igss = calculateDeduction(gross, employee.getIgssPercent());
        BigDecimal irtra = calculateDeduction(gross, employee.getIrtraPercent());
        BigDecimal net = gross.subtract(igss).subtract(irtra);

        PayrollItem item = new PayrollItem(
                UUID.randomUUID(),
                this,
                employee,
                employee.getDepartment(),
                gross,
                igss,
                irtra,
                net,
                notes);

        boolean added = items.add(item);
        if (!added) {
            throw new IllegalArgumentException(
                    "El empleado ya está incluido en esta nómina");
        }

        totalGrossAmount = totalGrossAmount.add(gross);
        totalIgssDeduction = totalIgssDeduction.add(igss);
        totalIrtraDeduction = totalIrtraDeduction.add(irtra);
        totalNetAmount = totalNetAmount.add(net);
    }

    private BigDecimal calculateDeduction(BigDecimal base, BigDecimal percent) {
        if (percent == null)
            return BigDecimal.ZERO;
        return base.multiply(percent);
    }

    private LocalDate validatePayDay(LocalDate payDay) {
        Objects.requireNonNull(payDay, "La fecha de pago no puede ser nula");
        if (payDay.isBefore(period.startDate()) || payDay.isAfter(period.endDate())) {
            throw new IllegalArgumentException("La fecha de pago debe estar dentro del periodo de la nomina");
        }
        return payDay;
    }
}
