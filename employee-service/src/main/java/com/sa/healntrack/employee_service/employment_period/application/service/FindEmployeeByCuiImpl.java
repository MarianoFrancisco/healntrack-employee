package com.sa.healntrack.employee_service.employment_period.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sa.healntrack.employee_service.employment_period.application.exception.EmployeeNotFoundException;
import com.sa.healntrack.employee_service.employment_period.application.port.in.find_employees.FindEmployeeByCui;
import com.sa.healntrack.employee_service.employment_period.application.port.out.FindEmployees;
import com.sa.healntrack.employee_service.employment_period.domain.Employee;

@Service
@Transactional(readOnly = true)
public class FindEmployeeByCuiImpl implements FindEmployeeByCui {
    private final FindEmployees findEmployees;

    public FindEmployeeByCuiImpl(FindEmployees findEmployees){
        this.findEmployees = findEmployees;
    }

    @Override
    public Employee findByCui(String cui) {
        return findEmployees.findEmployeeByCui(cui)
            .orElseThrow(() -> new EmployeeNotFoundException(cui));
    }
    
}
