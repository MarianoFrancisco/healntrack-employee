package com.sa.healntrack.employee_service.common.application.exception;

public class DuplicateEntityException extends RuntimeException {

    public DuplicateEntityException() {
        super("Entidad ya existe");
    }

    public DuplicateEntityException(String message) {
        super(message);
    }

    
}