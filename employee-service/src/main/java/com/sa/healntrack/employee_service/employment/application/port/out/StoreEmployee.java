package com.sa.healntrack.employee_service.employment.application.port.out;

import com.sa.healntrack.employee_service.employment.domain.Employee;

public interface StoreEmployee {
    Employee save(Employee employee);
}
