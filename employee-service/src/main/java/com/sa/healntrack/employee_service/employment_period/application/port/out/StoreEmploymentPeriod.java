package com.sa.healntrack.employee_service.employment_period.application.port.out;

import com.sa.healntrack.employee_service.employment_period.domain.EmploymentPeriod;

public interface StoreEmploymentPeriod {
    EmploymentPeriod save(EmploymentPeriod employmentPeriod);
}
