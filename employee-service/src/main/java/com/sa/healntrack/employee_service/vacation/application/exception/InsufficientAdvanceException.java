package com.sa.healntrack.employee_service.vacation.application.exception;

import com.sa.healntrack.employee_service.common.application.exception.BusinessException;

public class InsufficientAdvanceException extends BusinessException {
    public InsufficientAdvanceException(long minAdvanceDays) {
        super("La solicitud debe hacerse con al menos " + minAdvanceDays + " días de anticipación.");
    }
}
