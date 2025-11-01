package com.sa.healntrack.employee_service.employment.domain.value;

import java.util.Objects;
import java.util.UUID;

public record DepartmentManagerId(UUID value) {
    public DepartmentManagerId {
        Objects.requireNonNull(value, "El identificador del gerente de departamento no puede ser nulo");
    }

    public static DepartmentManagerId generate() {
        return new DepartmentManagerId(UUID.randomUUID());
    }
}
