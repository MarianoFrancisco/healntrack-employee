package com.sa.healntrack.employee_service.vacation.application.exception;

import com.sa.healntrack.employee_service.common.application.exception.BusinessException;

public class EmployeeHasApprovedVacationException extends BusinessException {
    public EmployeeHasApprovedVacationException(String employeeCui, int year) {
        super(String.format(
                "El empleado con CUI %s ya tiene vacaciones aprobadas o firmadas para el año %d. No puede solicitar nuevas vacaciones en el mismo año.",
                employeeCui, year));
    }
}
