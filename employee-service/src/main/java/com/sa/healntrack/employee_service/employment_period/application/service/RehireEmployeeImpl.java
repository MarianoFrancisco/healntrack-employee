package com.sa.healntrack.employee_service.employment_period.application.service;

import com.sa.healntrack.employee_service.employment_period.application.port.in.hire_employee.RehireEmployee;
import com.sa.healntrack.employee_service.employment_period.application.port.in.hire_employee.RehireEmployeeCommand;
import com.sa.healntrack.employee_service.employment_period.domain.Employee;

import java.time.LocalDate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sa.healntrack.employee_service.department.application.exception.DepartmentNotFoundException;
import com.sa.healntrack.employee_service.department.application.port.out.persistence.FindDepartments;
import com.sa.healntrack.employee_service.department.domain.Department;
import com.sa.healntrack.employee_service.employment_period.application.exception.EmployeeNotFoundException;
import com.sa.healntrack.employee_service.employment_period.application.port.out.FindEmployees;
import com.sa.healntrack.employee_service.employment_period.application.port.out.FindEmploymentPeriods;
import com.sa.healntrack.employee_service.employment_period.application.port.out.StoreEmployee;
import com.sa.healntrack.employee_service.employment_period.application.port.out.StoreEmploymentPeriod;
import com.sa.healntrack.employee_service.employment_period.domain.EmploymentPeriod;
import com.sa.healntrack.employee_service.employment_period.domain.PeriodType;
import com.sa.healntrack.employee_service.employment_period.domain.value.EmploymentPeriodId;

@Service
@Transactional(rollbackFor = Exception.class)
public class RehireEmployeeImpl implements RehireEmployee {

    private final FindEmployees findEmployees;
    private final FindDepartments findDepartments;
    private final StoreEmployee storeEmployee;
    private final StoreEmploymentPeriod storeEmploymentPeriod;
    private final FindEmploymentPeriods findEmploymentPeriods;

    public RehireEmployeeImpl(
            FindEmployees findEmployees,
            FindDepartments findDepartments,
            StoreEmployee storeEmployee,
            StoreEmploymentPeriod storeEmploymentPeriod,
            FindEmploymentPeriods findEmploymentPeriods) {
        this.findEmployees = findEmployees;
        this.findDepartments = findDepartments;
        this.storeEmployee = storeEmployee;
        this.storeEmploymentPeriod = storeEmploymentPeriod;
        this.findEmploymentPeriods = findEmploymentPeriods;
    }

    @Override
    public Employee rehireEmployee(String cui, RehireEmployeeCommand command) {
        Employee existingEmployee = findEmployees.findEmployeeByCui(cui)
                .orElseThrow(() -> new EmployeeNotFoundException("CUI: " + cui));

        Department department = findDepartments.findDepartmentByCode(command.departmentCode())
                    .orElseThrow(() -> new DepartmentNotFoundException(command.departmentCode()));

        existingEmployee.rehire(
                command.phoneNumber(),
                department,
                command.newSalary(),
                command.igssPercent(),
                command.irtraPercent());

        storeEmployee.save(existingEmployee);

        findEmploymentPeriods.findByEmployeeAndEndDate(existingEmployee, null)
                .ifPresent(activePeriod -> {
                    activePeriod.endPeriod(command.startDate() != null ? command.startDate() : LocalDate.now());
                    storeEmploymentPeriod.save(activePeriod);
                });

        EmploymentPeriod newEmploymentPeriod = new EmploymentPeriod(
                EmploymentPeriodId.generate().value(),
                existingEmployee,
                PeriodType.RECONTRATACION,
                command.startDate() != null ? command.startDate() : LocalDate.now(),
                existingEmployee.getSalary(),
                command.notes() != null ? command.notes() : "Recontratación del empleado");

        storeEmploymentPeriod.save(newEmploymentPeriod);

        return existingEmployee;
    }}
