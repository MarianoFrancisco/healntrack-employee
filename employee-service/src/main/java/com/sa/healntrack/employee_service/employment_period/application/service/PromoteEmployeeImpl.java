package com.sa.healntrack.employee_service.employment_period.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sa.healntrack.employee_service.common.application.exception.EntityNotFoundException;
import com.sa.healntrack.employee_service.common.application.exception.InvalidDateRangeException;
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
    private final StoreEmploymentPeriod storeEmploymentPeriod;
    private final StoreDepartmentManager storeDepartmentManager;
    private final StoreEmployee storeEmployee;

    public PromoteEmployeeImpl(
            FindEmployees findEmployees,
            FindEmploymentPeriods findEmploymentPeriods,
            FindDepartmentManagers findDepartmentManagers,
            StoreEmploymentPeriod storeEmploymentPeriod,
            StoreDepartmentManager storeDepartmentManager,
            StoreEmployee storeEmployee) {
        this.findEmployees = findEmployees;
        this.findEmploymentPeriods = findEmploymentPeriods;
        this.findDepartmentManagers = findDepartmentManagers;
        this.storeEmploymentPeriod = storeEmploymentPeriod;
        this.storeDepartmentManager = storeDepartmentManager;
        this.storeEmployee = storeEmployee;
    }

    @Override
    public Employee promoteEmployee(String cui, PromoteEmployeeCommand command) {
        Employee employee = findEmployees.findEmployeeByCui(cui)
                .orElseThrow(() -> new EmployeeNotFoundException(cui));

        if (!employee.getDepartment().getCode().toString().equals(command.departmentCode())) {
            throw new EmployeeNotInDepartmentException(employee.getFullname(), command.departmentCode());
        }

        if (findDepartmentManagers.existByDepartmentCodeAndIsActive(command.departmentCode(), true)) {
            throw new DuplicateManagerException(command.departmentCode());
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
                command.date());
        storeDepartmentManager.save(newManager);

        return employee;
    }
}
