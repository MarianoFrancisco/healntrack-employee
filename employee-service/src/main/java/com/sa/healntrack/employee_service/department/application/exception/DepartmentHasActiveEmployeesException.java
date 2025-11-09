package com.sa.healntrack.employee_service.department.application.exception;

import com.sa.healntrack.employee_service.common.application.exception.BusinessException;

public class DepartmentHasActiveEmployeesException extends BusinessException {
    public DepartmentHasActiveEmployeesException(String departmentCode) {
        super("No se puede desactivar el departamento con código " + departmentCode + 
              " porque hay empleados activos asignados a él.");
    }
    
}
