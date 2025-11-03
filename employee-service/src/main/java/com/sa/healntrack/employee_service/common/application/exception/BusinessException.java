package com.sa.healntrack.employee_service.common.application.exception;

public class BusinessException extends RuntimeException {
    public BusinessException(){
        super("Ocurrió un error de negocio");
    }

    public BusinessException(String message) {
        super(message);
    }
}
