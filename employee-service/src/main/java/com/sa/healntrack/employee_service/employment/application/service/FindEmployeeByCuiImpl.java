package com.sa.healntrack.employee_service.employment.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sa.healntrack.employee_service.employment.application.exception.EmployeeNotFoundException;
import com.sa.healntrack.employee_service.employment.application.port.in.find_employees.FindEmployeeByCui;
import com.sa.healntrack.employee_service.employment.application.port.out.FindEmployees;
import com.sa.healntrack.employee_service.employment.domain.Employee;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FindEmployeeByCuiImpl implements FindEmployeeByCui {
    private final FindEmployees findEmployees;

    @Override
    public Employee findByCui(String cui) {
        return findEmployees.findEmployeeByCui(cui)
            .orElseThrow(() -> new EmployeeNotFoundException(cui));
    }
    
}
