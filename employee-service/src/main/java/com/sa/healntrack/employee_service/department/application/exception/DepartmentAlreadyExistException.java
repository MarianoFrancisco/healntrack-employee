package com.sa.healntrack.employee_service.department.application.exception;

import com.sa.healntrack.employee_service.common.application.exception.EntityAlreadyExistsException;

public class DepartmentAlreadyExistException extends EntityAlreadyExistsException {

    public DepartmentAlreadyExistException(String code) {
        super("El departamento con código " + code + " ya existe");
    }
    
}
