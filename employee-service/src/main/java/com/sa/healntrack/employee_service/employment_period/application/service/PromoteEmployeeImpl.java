package com.sa.healntrack.employee_service.employment_period.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sa.healntrack.employee_service.common.application.exception.EntityNotFoundException;
import com.sa.healntrack.employee_service.common.application.exception.InvalidDateRangeException;
import com.sa.healntrack.employee_service.department.application.exception.DepartmentNotFoundException;
import com.sa.healntrack.employee_service.department.application.port.out.persistence.FindDepartments;
import com.sa.healntrack.employee_service.department.domain.Department;
import com.sa.healntrack.employee_service.employment_period.application.exception.DuplicateManagerException;
import com.sa.healntrack.employee_service.employment_period.application.exception.EmployeeNotFoundException;
import com.sa.healntrack.employee_service.employment_period.application.exception.EmployeeNotInDepartmentException;
import com.sa.healntrack.employee_service.employment_period.application.port.in.promote_employee.PromoteEmployee;
import com.sa.healntrack.employee_service.employment_period.application.port.in.promote_employee.PromoteEmployeeCommand;
import com.sa.healntrack.employee_service.employment_period.application.port.out.FindDepartmentManagers;
import com.sa.healntrack.employee_service.employment_period.application.port.out.FindEmployees;
import com.sa.healntrack.employee_service.employment_period.application.port.out.FindEmploymentPeriods;
import com.sa.healntrack.employee_service.employment_period.application.port.out.StoreDepartmentManager;
import com.sa.healntrack.employee_service.employment_period.application.port.out.StoreEmployee;
import com.sa.healntrack.employee_service.employment_period.application.port.out.StoreEmploymentPeriod;
import com.sa.healntrack.employee_service.employment_period.domain.DepartmentManager;
import com.sa.healntrack.employee_service.employment_period.domain.Employee;
import com.sa.healntrack.employee_service.employment_period.domain.EmploymentPeriod;
import com.sa.healntrack.employee_service.employment_period.domain.PeriodType;

@Service
@Transactional(rollbackFor = Exception.class)
public class PromoteEmployeeImpl implements PromoteEmployee {

    private final FindEmployees findEmployees;
    private final FindEmploymentPeriods findEmploymentPeriods;
    private final FindDepartmentManagers findDepartmentManagers;
    private final FindDepartments findDepartments;
    private final StoreEmploymentPeriod storeEmploymentPeriod;
    private final StoreDepartmentManager storeDepartmentManager;
    private final StoreEmployee storeEmployee;

    public PromoteEmployeeImpl(
            FindEmployees findEmployees,
            FindEmploymentPeriods findEmploymentPeriods,
            FindDepartmentManagers findDepartmentManagers,
            FindDepartments findDepartments,
            StoreEmploymentPeriod storeEmploymentPeriod,
            StoreDepartmentManager storeDepartmentManager,
            StoreEmployee storeEmployee) {
        this.findEmployees = findEmployees;
        this.findEmploymentPeriods = findEmploymentPeriods;
        this.findDepartmentManagers = findDepartmentManagers;
        this.findDepartments = findDepartments;
        this.storeEmploymentPeriod = storeEmploymentPeriod;
        this.storeDepartmentManager = storeDepartmentManager;
        this.storeEmployee = storeEmployee;
    }

    @Override
    public Employee promoteEmployee(String cui, PromoteEmployeeCommand command) {
        Employee employee = findEmployees.findEmployeeByCui(cui)
                .orElseThrow(() -> new EmployeeNotFoundException(cui));

        Department department = findDepartments.findDepartmentByCode(command.departmentCode())
                .orElseThrow(() -> new DepartmentNotFoundException(command.departmentCode()));
        
        if (!department.getCode().value().equals(employee.getDepartment().getCode().value())) {
            throw new EmployeeNotInDepartmentException(cui, department.getCode().value());
        }

        if (findDepartmentManagers.existsByDepartmentAndIsActive(department, true)) {
            throw new DuplicateManagerException(department.getCode().toString());
        }

        EmploymentPeriod lastPeriod = findEmploymentPeriods.findByEmployeeAndEndDate(employee, null)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró un EmploymentPeriod activo"));

        if (!command.date().isAfter(lastPeriod.getStartDate())) {
            throw new InvalidDateRangeException(lastPeriod.getStartDate(), command.date(),
                    "última fecha de inicio", "fecha de ascenso");
        }

        employee.increaseSalary(command.salaryIncrease());
        storeEmployee.save(employee);

        lastPeriod.endPeriod(command.date());
        storeEmploymentPeriod.save(lastPeriod);

        EmploymentPeriod promotionPeriod = new EmploymentPeriod(
                UUID.randomUUID(),
                employee,
                PeriodType.ASENSO,
                command.date(),
                employee.getSalary(),
                command.notes() != null ? command.notes() : "Ascenso del empleado");
        storeEmploymentPeriod.save(promotionPeriod);

        DepartmentManager newManager = new DepartmentManager(
                UUID.randomUUID(),
                employee,
                department,
                command.date());
        storeDepartmentManager.save(newManager);

        return employee;
    }
}
