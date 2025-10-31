package com.sa.healntrack.employee_service.employment_period.application.exception;

public class EmployeeNotInDepartmentException extends RuntimeException {
    public EmployeeNotInDepartmentException(String cui, String departmentCode){
        super("El empleado con CUI " + cui + " no pertenece al departamento con código " + departmentCode);
    }
}
