package com.sa.healntrack.employee_service.employment_period.domain.value;

import java.util.Objects;
import java.util.UUID;

public record EmploymentPeriodId(UUID value) {
    public EmploymentPeriodId {
        Objects.requireNonNull(value, "El identificador del periodo de empleo no puede ser nulo");
    }

    public static EmploymentPeriodId generate() {
        return new EmploymentPeriodId(UUID.randomUUID());
    }
}
