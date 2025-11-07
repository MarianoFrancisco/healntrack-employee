package com.sa.healntrack.employee_service.employment.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sa.healntrack.employee_service.employment.application.exception.EmployeeNotFoundException;
import com.sa.healntrack.employee_service.employment.application.port.in.find_employees.FindEmployeeById;
import com.sa.healntrack.employee_service.employment.application.port.out.FindEmployees;
import com.sa.healntrack.employee_service.employment.domain.Employee;

@Service
@Transactional(readOnly = true)
public class FindEmployeeByIdImpl implements FindEmployeeById {

    private final FindEmployees findEmployees;

    public FindEmployeeByIdImpl(FindEmployees findEmployees) {
        this.findEmployees = findEmployees;
    }

    @Override
    public Employee findById(UUID id) {
        return findEmployees.findEmployeeById(id)
            .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

}
