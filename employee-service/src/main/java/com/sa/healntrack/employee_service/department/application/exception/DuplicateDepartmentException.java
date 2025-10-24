package com.sa.healntrack.employee_service.department.application.exception;

import com.sa.healntrack.employee_service.common.application.exception.DuplicateEntityException;

public class DuplicateDepartmentException extends DuplicateEntityException {

    public DuplicateDepartmentException(String code) {
        super("El departamento con código " + code + " ya existe");
    }
    
}
