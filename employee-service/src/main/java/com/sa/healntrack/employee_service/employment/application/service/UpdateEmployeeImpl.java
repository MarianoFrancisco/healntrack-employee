package com.sa.healntrack.employee_service.employment.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sa.healntrack.employee_service.employment.application.exception.EmployeeNotFoundException;
import com.sa.healntrack.employee_service.employment.application.mapper.EmployeeMapper;
import com.sa.healntrack.employee_service.employment.application.port.in.update_employee.UpdateEmployee;
import com.sa.healntrack.employee_service.employment.application.port.in.update_employee.UpdateEmployeeCommand;
import com.sa.healntrack.employee_service.employment.application.port.out.FindEmployees;
import com.sa.healntrack.employee_service.employment.application.port.out.StoreEmployee;
import com.sa.healntrack.employee_service.employment.domain.Employee;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class UpdateEmployeeImpl implements UpdateEmployee{
    private final StoreEmployee storeEmployee;
    private final FindEmployees findEmployees;

    @Override
    public Employee updateEmployee(String cui, UpdateEmployeeCommand command) {
        Employee existing = findEmployees.findEmployeeByCui(cui)
            .orElseThrow(() -> new EmployeeNotFoundException(cui));
        
        Employee updated = EmployeeMapper.updateEmployee(existing, command);
        return storeEmployee.save(updated);
    }
    
}
