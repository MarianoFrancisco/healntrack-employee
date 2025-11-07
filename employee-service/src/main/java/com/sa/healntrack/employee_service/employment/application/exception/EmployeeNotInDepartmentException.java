package com.sa.healntrack.employee_service.employment.application.exception;

import com.sa.healntrack.employee_service.common.application.exception.BusinessException;

public class EmployeeNotInDepartmentException extends BusinessException {
    public EmployeeNotInDepartmentException(String cui, String departmentCode){
        super("El empleado con CUI " + cui + " no pertenece al departamento con código " + departmentCode);
    }
}
