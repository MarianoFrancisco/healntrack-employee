package com.sa.healntrack.employee_service.payroll.application.exception;

import java.time.LocalDate;

import com.sa.healntrack.employee_service.common.application.exception.BusinessException;

public class PeriodOverlapsException extends BusinessException {
    public PeriodOverlapsException(
        LocalDate newStartDate,
        LocalDate newEndDate,
        LocalDate existingStartDate,
        LocalDate existingEndDate
    ) {
        super("El periodo " + newStartDate + " - " + newEndDate +
              " se solapa con una nómina con periodo " + existingStartDate + " - " + existingEndDate);
    }
    
}
