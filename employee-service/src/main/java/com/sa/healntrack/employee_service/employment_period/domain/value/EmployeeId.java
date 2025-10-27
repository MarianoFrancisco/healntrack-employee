package com.sa.healntrack.employee_service.employment_period.domain.value;

import java.util.Objects;
import java.util.UUID;

public record EmployeeId(UUID value) {
    public EmployeeId {
        Objects.requireNonNull(value, "El identificador del empleado no puede ser nulo");
    }

    public static EmployeeId generate() {
        return new EmployeeId(UUID.randomUUID());
    }
}
