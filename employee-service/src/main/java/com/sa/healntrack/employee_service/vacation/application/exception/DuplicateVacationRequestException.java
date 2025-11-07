package com.sa.healntrack.employee_service.vacation.application.exception;

import com.sa.healntrack.employee_service.common.application.exception.DuplicateEntityException;

public class DuplicateVacationRequestException extends DuplicateEntityException {
    public DuplicateVacationRequestException(String employeeCui) {
        super("Ya existe una solicitud de vacaciones pendiente para el empleado con CUI: " + employeeCui);
    }
    
}
