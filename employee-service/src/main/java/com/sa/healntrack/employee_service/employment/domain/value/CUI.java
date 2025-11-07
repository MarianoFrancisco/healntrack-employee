package com.sa.healntrack.employee_service.employment.domain.value;

import java.util.Objects;

public record CUI(String value) {
    public CUI {
        Objects.requireNonNull(value, "El CUI no puede ser nulo");
        if (!value.matches("\\d{13}")) {
            throw new IllegalArgumentException("El CUI debe tener exactamente 13 digitos");
        }
    }
}
