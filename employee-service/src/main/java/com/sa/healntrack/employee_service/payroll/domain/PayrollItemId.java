package com.sa.healntrack.employee_service.payroll.domain;

import java.util.Objects;
import java.util.UUID;

public record PayrollItemId(UUID value) {
    public PayrollItemId {
        Objects.requireNonNull(value, "El identificador del ítem de nómina no puede ser nulo");
    }

    public static PayrollItemId generate() {
        return new PayrollItemId(UUID.randomUUID());
    }
}
