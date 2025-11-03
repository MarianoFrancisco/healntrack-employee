package com.sa.healntrack.employee_service.vacation.domain;

import java.util.Objects;
import java.util.UUID;

public record VacationId(UUID value) {
    public VacationId {
        Objects.requireNonNull(value, "El identificador de la solicitud de vacaciones no puede ser nulo");
    }

    public static VacationId generate() {
        return new VacationId(UUID.randomUUID());
    }
}
