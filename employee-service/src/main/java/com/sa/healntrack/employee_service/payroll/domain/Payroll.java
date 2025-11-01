package com.sa.healntrack.employee_service.payroll.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import lombok.Getter;

@Getter
public class Payroll {
    private final PayrollId id;
    private final List<PayrollItem> items;
    private final PayrollPeriod period;
    private BigDecimal totalGrossAmount;
    private BigDecimal totalIgssDeduction;
    private BigDecimal totalIrtraDeduction;
    private BigDecimal totalNetAmount;

    public Payroll(UUID id, LocalDate startDate, LocalDate endDate) {
        this.id = new PayrollId(id);
        this.period = new PayrollPeriod(startDate, endDate);
        this.items = new ArrayList<>();
        this.totalGrossAmount = BigDecimal.ZERO;
        this.totalIgssDeduction = BigDecimal.ZERO;
        this.totalIrtraDeduction = BigDecimal.ZERO;
        this.totalNetAmount = BigDecimal.ZERO;
    }
}
