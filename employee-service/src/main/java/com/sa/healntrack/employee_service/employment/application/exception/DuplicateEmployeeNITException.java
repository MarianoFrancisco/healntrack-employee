package com.sa.healntrack.employee_service.employment.application.exception;

import com.sa.healntrack.employee_service.common.application.exception.DuplicateEntityException;

public class DuplicateEmployeeNITException extends DuplicateEntityException {
    public DuplicateEmployeeNITException(String nit){
        super("Empleado existente con NIT " + nit);
    }
    
}
