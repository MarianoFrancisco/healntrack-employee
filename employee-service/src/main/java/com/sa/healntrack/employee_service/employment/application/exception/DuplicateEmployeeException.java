package com.sa.healntrack.employee_service.employment.application.exception;

import com.sa.healntrack.employee_service.common.application.exception.DuplicateEntityException;

public class DuplicateEmployeeException extends DuplicateEntityException {
    public DuplicateEmployeeException(String cui){
        super("Empleado existente con cui: " + cui);
    }
}
