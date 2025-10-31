package com.sa.healntrack.employee_service.employment_period.application.exception;

import com.sa.healntrack.employee_service.common.application.exception.EntityNotFoundException;

public class EmployeeNotFoundException extends EntityNotFoundException{

    public EmployeeNotFoundException(String cui){
        super("Empleado con cui " + cui + " no encontrado");
    }
    
}
