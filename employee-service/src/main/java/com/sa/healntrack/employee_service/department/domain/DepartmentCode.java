package com.sa.healntrack.employee_service.department.domain;

import java.util.Objects;

public record DepartmentCode(String value) {

    public DepartmentCode {
        Objects.requireNonNull(value, "El código del departamento no puede ser nulo");
        if (!value.matches("^[A-Z]{3}-\\d{3}$")) {
            throw new IllegalArgumentException(
                "El código del departamento debe cumplir el formato XXX-000. Ejemplo: COC-025"
            );
        }
    }
}
