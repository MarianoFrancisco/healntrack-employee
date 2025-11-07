package com.sa.healntrack.employee_service.employment.application.exception;

import com.sa.healntrack.employee_service.common.application.exception.EntityNotFoundException;

public class DepartmentManagerNotFoundException extends EntityNotFoundException {
    public DepartmentManagerNotFoundException(String email) {
        super("Gerente de departamento con correo electrónico '" + email + "' no encontrado.");
    }
    
}
