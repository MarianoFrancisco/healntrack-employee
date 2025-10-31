package com.sa.healntrack.employee_service.employment_period.application.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sa.healntrack.employee_service.department.application.exception.DepartmentNotFoundException;
import com.sa.healntrack.employee_service.department.application.port.out.persistence.FindDepartments;
import com.sa.healntrack.employee_service.department.domain.Department;
import com.sa.healntrack.employee_service.employment_period.application.exception.DuplicateEmailException;
import com.sa.healntrack.employee_service.employment_period.application.exception.DuplicateEmployeeException;
import com.sa.healntrack.employee_service.employment_period.application.mapper.EmployeeMapper;
import com.sa.healntrack.employee_service.employment_period.application.port.in.hire_employee.HireEmployee;
import com.sa.healntrack.employee_service.employment_period.application.port.in.hire_employee.HireEmployeeCommand;
import com.sa.healntrack.employee_service.employment_period.application.port.out.FindEmployees;
import com.sa.healntrack.employee_service.employment_period.application.port.out.StoreEmployee;
import com.sa.healntrack.employee_service.employment_period.application.port.out.StoreEmploymentPeriod;
import com.sa.healntrack.employee_service.employment_period.domain.Employee;
import com.sa.healntrack.employee_service.employment_period.domain.EmploymentPeriod;
import com.sa.healntrack.employee_service.employment_period.domain.PeriodType;
import com.sa.healntrack.employee_service.employment_period.domain.value.EmploymentPeriodId;

@Service
@Transactional(rollbackFor = Exception.class)
public class HireEmployeeImpl implements HireEmployee {

    private final FindEmployees findEmployees;
    private final FindDepartments findDepartments;
    private final StoreEmployee storeEmployee;
    private final StoreEmploymentPeriod storeEmploymentPeriod;

    public HireEmployeeImpl(
        FindEmployees findEmployees,
        FindDepartments findDepartments,
        StoreEmployee storeEmployee,
        StoreEmploymentPeriod storeEmploymentPeriod
    ) {
        this.findEmployees = findEmployees;
        this.findDepartments = findDepartments;
        this.storeEmployee = storeEmployee;
        this.storeEmploymentPeriod = storeEmploymentPeriod;
    }

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

        EmploymentPeriod employmentPeriod = new EmploymentPeriod(
            EmploymentPeriodId.generate().value(),
            employee,
            PeriodType.CONTRATACION,
            command.startDate() != null ? command.startDate() : LocalDate.now(),
            employee.getSalary(),
            "Contratación inicial del empleado"
        );

        storeEmploymentPeriod.save(employmentPeriod);
        return employee;
    }
}
