package com.sa.healntrack.employee_service.employment.application.exception;

import com.sa.healntrack.employee_service.common.application.exception.DuplicateEntityException;

public class DuplicateEmailException extends DuplicateEntityException {
    public DuplicateEmailException(String email){
        super("Empleado existente con email " + email);
    }
}
