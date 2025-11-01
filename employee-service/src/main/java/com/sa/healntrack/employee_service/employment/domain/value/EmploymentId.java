package com.sa.healntrack.employee_service.employment.domain.value;

import java.util.Objects;
import java.util.UUID;

public record EmploymentId(UUID value) {
    public EmploymentId {
        Objects.requireNonNull(value, "El identificador del periodo de empleo no puede ser nulo");
    }

    public static EmploymentId generate() {
        return new EmploymentId(UUID.randomUUID());
    }
}
