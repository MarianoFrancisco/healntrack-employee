package com.sa.healntrack.employee_service.employment.application.exception;

import com.sa.healntrack.employee_service.common.application.exception.DuplicateEntityException;

public class DuplicateManagerException extends DuplicateEntityException {
    public DuplicateManagerException(String departmentCode){
        super("Ya existe un manager activo en el departamento con código " + departmentCode);
    }
    
}
