package com.sa.healntrack.employee_service.common.application.exception;

public class EntityAlreadyExistsException extends RuntimeException {

    public EntityAlreadyExistsException() {
        super("Entidad ya existe");
    }

    public EntityAlreadyExistsException(String message) {
        super(message);
    }

    
}