package com.sa.healntrack.employee_service.employment_period.application.service;

import com.sa.healntrack.employee_service.employment_period.application.exception.EmployeeNotFoundException;
import com.sa.healntrack.employee_service.employment_period.application.mapper.EmployeeMapper;
import com.sa.healntrack.employee_service.employment_period.application.port.in.update_employee.UpdateEmployee;
import com.sa.healntrack.employee_service.employment_period.application.port.in.update_employee.UpdateEmployeeCommand;
import com.sa.healntrack.employee_service.employment_period.application.port.out.FindEmployees;
import com.sa.healntrack.employee_service.employment_period.application.port.out.StoreEmployee;
import com.sa.healntrack.employee_service.employment_period.domain.Employee;

public class UpdateEmployeeImpl implements UpdateEmployee{
    private final StoreEmployee storeEmployee;
    private final FindEmployees findEmployees;

    public UpdateEmployeeImpl(StoreEmployee storeEmployee, FindEmployees findEmployees){
        this.storeEmployee = storeEmployee;
        this.findEmployees = findEmployees;
    }

    @Override
    public Employee updateEmployee(String cui, UpdateEmployeeCommand command) {
        Employee existing = findEmployees.findEmployeeByCui(cui)
            .orElseThrow(() -> new EmployeeNotFoundException(cui));
        
        Employee updated = EmployeeMapper.updateEmployee(existing, command);
        return storeEmployee.save(updated);
    }
    
}
