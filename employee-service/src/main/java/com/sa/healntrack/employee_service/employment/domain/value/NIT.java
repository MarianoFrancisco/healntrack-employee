package com.sa.healntrack.employee_service.employment.domain.value;

import java.util.Objects;

public record NIT(String value) {
    public NIT {
        Objects.requireNonNull(value, "El NIT no puede ser nulo");
        if (!value.matches("\\d{9}")) {
            throw new IllegalArgumentException("El NIT debe tener exactamente 9 digitos");
        }
    }
}
