package com.sa.healntrack.employee_service.configuration.application.exception;

import com.sa.healntrack.employee_service.common.application.exception.EntityNotFoundException;

public class ConfNotFoundException extends EntityNotFoundException {
    public ConfNotFoundException(String key) {
        super("Configuracion con la llave '" + key + "' no encontrada.");
    }
    
}
