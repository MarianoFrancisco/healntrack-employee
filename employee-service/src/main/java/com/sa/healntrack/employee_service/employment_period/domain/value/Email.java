package com.sa.healntrack.employee_service.employment_period.domain.value;

import java.util.Objects;

public record Email(String value) {
    public Email {
        Objects.requireNonNull(value, "El email no puede ser nulo");
        if (!value.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new IllegalArgumentException("El email no es valido");
        }
    }
}
