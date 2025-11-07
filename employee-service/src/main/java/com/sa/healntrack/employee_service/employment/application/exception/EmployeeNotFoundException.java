package com.sa.healntrack.employee_service.employment.application.exception;

import java.util.UUID;

import com.sa.healntrack.employee_service.common.application.exception.EntityNotFoundException;

public class EmployeeNotFoundException extends EntityNotFoundException{

    public EmployeeNotFoundException(String cui){
        super("Empleado con cui " + cui + " no encontrado");
    }

    public EmployeeNotFoundException(UUID id){
        super("Empleado con id " + id + " no encontrado");
    }
    
}
