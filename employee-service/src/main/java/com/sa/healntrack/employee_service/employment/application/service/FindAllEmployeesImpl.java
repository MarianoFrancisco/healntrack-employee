package com.sa.healntrack.employee_service.employment.application.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sa.healntrack.employee_service.employment.application.port.in.find_employees.FindAllEmployees;
import com.sa.healntrack.employee_service.employment.application.port.in.find_employees.FindAllEmployeesQuery;
import com.sa.healntrack.employee_service.employment.application.port.out.FindEmployees;
import com.sa.healntrack.employee_service.employment.domain.Employee;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FindAllEmployeesImpl  implements FindAllEmployees {
    private final FindEmployees findEmployees;

    @Override
    public List<Employee> findAllEmployees(FindAllEmployeesQuery query) {
        return findEmployees.findAllEmployees(query);
    }    
}
