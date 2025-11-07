package com.sa.healntrack.employee_service.vacation.application.exception;

import com.sa.healntrack.employee_service.common.application.exception.BusinessException;

public class InvalidVacationDurationException extends BusinessException {
    public InvalidVacationDurationException(int expectedDays) {
        super("La cantidad de días solicitados debe ser igual a " + expectedDays + " días.");
    }
    
}
