package com.sa.healntrack.employee_service.employment_period.application.port.in.find_employees;

import com.sa.healntrack.employee_service.employment_period.domain.Employee;

public interface FindEmployeeByCui {
    Employee findByCui(String cui);
}
