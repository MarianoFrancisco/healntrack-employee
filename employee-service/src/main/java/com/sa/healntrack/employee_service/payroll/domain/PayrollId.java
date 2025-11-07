package com.sa.healntrack.employee_service.payroll.domain;

import java.util.Objects;
import java.util.UUID;

public record PayrollId(UUID value) {
    public PayrollId {
        Objects.requireNonNull(value, "El identificador de la nómina no puede ser nulo");
    }

    public static PayrollId generate() {
        return new PayrollId(UUID.randomUUID());
    }
}
