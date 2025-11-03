package com.sa.healntrack.employee_service.employment.application.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sa.healntrack.employee_service.department.application.exception.DepartmentNotFoundException;
import com.sa.healntrack.employee_service.department.application.port.out.persistence.FindDepartments;
import com.sa.healntrack.employee_service.department.domain.Department;
import com.sa.healntrack.employee_service.employment.application.exception.DuplicateEmailException;
import com.sa.healntrack.employee_service.employment.application.exception.DuplicateEmployeeException;
import com.sa.healntrack.employee_service.employment.application.mapper.EmployeeMapper;
import com.sa.healntrack.employee_service.employment.application.port.in.hire_employee.HireEmployee;
import com.sa.healntrack.employee_service.employment.application.port.in.hire_employee.HireEmployeeCommand;
import com.sa.healntrack.employee_service.employment.application.port.out.FindEmployees;
import com.sa.healntrack.employee_service.employment.application.port.out.StoreEmployee;
import com.sa.healntrack.employee_service.employment.application.port.out.StoreEmployment;
import com.sa.healntrack.employee_service.employment.domain.Employee;
import com.sa.healntrack.employee_service.employment.domain.Employment;
import com.sa.healntrack.employee_service.employment.domain.PeriodType;
import com.sa.healntrack.employee_service.employment.domain.value.EmploymentId;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class HireEmployeeImpl implements HireEmployee {

    private final FindEmployees findEmployees;
    private final FindDepartments findDepartments;
    private final StoreEmployee storeEmployee;
    private final StoreEmployment storeEmployment;

    @Override
    public Employee hireEmployee(HireEmployeeCommand command) {
        if (findEmployees.existByCui(command.cui()) || findEmployees.existByNit(command.nit())) {
            throw new DuplicateEmployeeException(command.cui());
        }

        if (findEmployees.existByEmail(command.email())) {
            throw new DuplicateEmailException(command.email());
        }

        Department department = findDepartments.findDepartmentByCode(command.departmentCode())
                .orElseThrow(() -> new DepartmentNotFoundException(command.departmentCode()));

        Employee employee = EmployeeMapper.toDomain(command, department);
        employee = storeEmployee.save(employee);

        Employment employmentPeriod = new Employment(
            EmploymentId.generate().value(),
            employee,
            PeriodType.CONTRATACION,
            command.startDate() != null ? command.startDate() : LocalDate.now(),
            employee.getSalary(),
            "Contratación inicial del empleado"
        );

        storeEmployment.save(employmentPeriod);
        return employee;
    }
}
