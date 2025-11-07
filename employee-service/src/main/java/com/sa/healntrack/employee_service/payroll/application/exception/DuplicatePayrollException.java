package com.sa.healntrack.employee_service.payroll.application.exception;

import java.time.LocalDate;

import com.sa.healntrack.employee_service.common.application.exception.DuplicateEntityException;

public class DuplicatePayrollException extends DuplicateEntityException {
    public DuplicatePayrollException(LocalDate startDate, LocalDate endDate) {
        super("Ya existe una nomina pagada para el periodo " + startDate + " - " + endDate);
    }
}
