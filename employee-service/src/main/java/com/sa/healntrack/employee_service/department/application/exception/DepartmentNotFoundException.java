package com.sa.healntrack.employee_service.department.application.exception;

import com.sa.healntrack.employee_service.common.application.exception.EntityNotFoundException;

public class DepartmentNotFoundException extends EntityNotFoundException {

    public DepartmentNotFoundException(String code){
        super("Departamento con código " + code + " no encontrado");
    }
    
}
