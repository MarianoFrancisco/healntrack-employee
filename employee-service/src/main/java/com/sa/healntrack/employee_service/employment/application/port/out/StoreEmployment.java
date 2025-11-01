package com.sa.healntrack.employee_service.employment.application.port.out;

import com.sa.healntrack.employee_service.employment.domain.Employment;

public interface StoreEmployment {
    Employment save(Employment employmentPeriod);
}
