package com.sa.healntrack.employee_service.employment_period.application.port.out;

import com.sa.healntrack.employee_service.employment_period.domain.Employee;

public interface StoreEmployee {
    Employee save(Employee employee);
}
