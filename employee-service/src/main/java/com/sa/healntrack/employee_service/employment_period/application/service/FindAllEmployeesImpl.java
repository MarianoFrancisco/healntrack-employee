package com.sa.healntrack.employee_service.employment_period.application.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sa.healntrack.employee_service.employment_period.application.port.in.find_employees.FindAllEmployees;
import com.sa.healntrack.employee_service.employment_period.application.port.in.find_employees.FindAllEmployeesQuery;
import com.sa.healntrack.employee_service.employment_period.application.port.out.FindEmployees;
import com.sa.healntrack.employee_service.employment_period.domain.Employee;

@Service
@Transactional(readOnly = true)
public class FindAllEmployeesImpl  implements FindAllEmployees {
    private final FindEmployees findEmployees;

    public FindAllEmployeesImpl(FindEmployees findEmployees) {
        this.findEmployees = findEmployees;
    }

    @Override
    public List<Employee> findAllEmployees(FindAllEmployeesQuery query) {
        return findEmployees.findAllEmployees(query);
    }    
}
