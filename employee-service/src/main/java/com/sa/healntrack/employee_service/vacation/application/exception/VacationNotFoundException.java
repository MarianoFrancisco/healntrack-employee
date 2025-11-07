package com.sa.healntrack.employee_service.vacation.application.exception;

import java.util.UUID;

import com.sa.healntrack.employee_service.common.application.exception.EntityNotFoundException;

public class VacationNotFoundException extends EntityNotFoundException {
    public VacationNotFoundException(UUID id) {
        super("Solicitud de vacaciones con ID '" + id + "' no encontrada.");
    }
    
}
