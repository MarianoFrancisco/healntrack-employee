package com.sa.healntrack.employee_service.employment.application.port.in.find_employees;

import com.sa.healntrack.employee_service.employment.domain.Employee;

public interface FindEmployeeByCui {
    Employee findByCui(String cui);
}
