package com.sa.healntrack.employee_service.employment.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sa.healntrack.employee_service.common.application.exception.EntityNotFoundException;
import com.sa.healntrack.employee_service.common.application.exception.InvalidDateRangeException;
import com.sa.healntrack.employee_service.department.application.exception.DepartmentNotFoundException;
import com.sa.healntrack.employee_service.department.application.port.out.persistence.FindDepartments;
import com.sa.healntrack.employee_service.department.domain.Department;
import com.sa.healntrack.employee_service.employment.application.exception.DuplicateManagerException;
import com.sa.healntrack.employee_service.employment.application.exception.EmployeeNotFoundException;
import com.sa.healntrack.employee_service.employment.application.exception.EmployeeNotInDepartmentException;
import com.sa.healntrack.employee_service.employment.application.port.in.promote_employee.PromoteEmployee;
import com.sa.healntrack.employee_service.employment.application.port.in.promote_employee.PromoteEmployeeCommand;
import com.sa.healntrack.employee_service.employment.application.port.out.FindDepartmentManagers;
import com.sa.healntrack.employee_service.employment.application.port.out.FindEmployees;
import com.sa.healntrack.employee_service.employment.application.port.out.FindEmployments;
import com.sa.healntrack.employee_service.employment.application.port.out.StoreDepartmentManager;
import com.sa.healntrack.employee_service.employment.application.port.out.StoreEmployee;
import com.sa.healntrack.employee_service.employment.application.port.out.StoreEmployment;
import com.sa.healntrack.employee_service.employment.domain.DepartmentManager;
import com.sa.healntrack.employee_service.employment.domain.Employee;
import com.sa.healntrack.employee_service.employment.domain.Employment;
import com.sa.healntrack.employee_service.employment.domain.PeriodType;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class PromoteEmployeeImpl implements PromoteEmployee {

    private final FindEmployees findEmployees;
    private final FindEmployments findEmployments;
    private final FindDepartmentManagers findDepartmentManagers;
    private final FindDepartments findDepartments;
    private final StoreEmployment storeEmployment;
    private final StoreDepartmentManager storeDepartmentManager;
    private final StoreEmployee storeEmployee;

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

        Employment lastPeriod = findEmployments.findByEmployeeAndEndDate(employee, null)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró un Employment activo"));

        if (!command.date().isAfter(lastPeriod.getStartDate())) {
            throw new InvalidDateRangeException(lastPeriod.getStartDate(), command.date(),
                    "última fecha de inicio", "fecha de ascenso");
        }

        employee.increaseSalary(command.salaryIncrease());
        storeEmployee.save(employee);

        lastPeriod.endPeriod(command.date());
        storeEmployment.save(lastPeriod);

        Employment promotionPeriod = new Employment(
                UUID.randomUUID(),
                employee,
                PeriodType.ASENSO,
                command.date(),
                employee.getSalary(),
                command.notes() != null ? command.notes() : "Ascenso del empleado");
        storeEmployment.save(promotionPeriod);

        DepartmentManager newManager = new DepartmentManager(
                UUID.randomUUID(),
                employee,
                department,
                command.date());
        storeDepartmentManager.save(newManager);

        return employee;
    }
}
