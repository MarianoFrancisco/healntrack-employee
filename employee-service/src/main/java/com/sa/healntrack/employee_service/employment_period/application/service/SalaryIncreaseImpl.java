package com.sa.healntrack.employee_service.employment_period.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sa.healntrack.employee_service.common.application.exception.EntityNotFoundException;
import com.sa.healntrack.employee_service.common.application.exception.InvalidDateRangeException;
import com.sa.healntrack.employee_service.employment_period.application.exception.EmployeeNotFoundException;
import com.sa.healntrack.employee_service.employment_period.application.port.in.salary_increase.SalaryIncrease;
import com.sa.healntrack.employee_service.employment_period.application.port.in.salary_increase.SalaryIncreaseCommand;
import com.sa.healntrack.employee_service.employment_period.application.port.out.FindEmployees;
import com.sa.healntrack.employee_service.employment_period.application.port.out.FindEmploymentPeriods;
import com.sa.healntrack.employee_service.employment_period.application.port.out.StoreEmployee;
import com.sa.healntrack.employee_service.employment_period.application.port.out.StoreEmploymentPeriod;
import com.sa.healntrack.employee_service.employment_period.domain.Employee;
import com.sa.healntrack.employee_service.employment_period.domain.EmploymentPeriod;
import com.sa.healntrack.employee_service.employment_period.domain.PeriodType;
import com.sa.healntrack.employee_service.employment_period.domain.value.EmploymentPeriodId;

@Service
@Transactional(rollbackFor = Exception.class)
public class SalaryIncreaseImpl implements SalaryIncrease {

    private final FindEmployees findEmployees;
    private final FindEmploymentPeriods findEmploymentPeriods;
    private final StoreEmploymentPeriod storeEmploymentPeriod;
    private final StoreEmployee storeEmployee;

    public SalaryIncreaseImpl(
            FindEmployees findEmployees,
            FindEmploymentPeriods findEmploymentPeriods,
            StoreEmploymentPeriod storeEmploymentPeriod,
            StoreEmployee storeEmployee) {
        this.findEmployees = findEmployees;
        this.findEmploymentPeriods = findEmploymentPeriods;
        this.storeEmploymentPeriod = storeEmploymentPeriod;
        this.storeEmployee = storeEmployee;
    }

    @Override
    public Employee applySalaryIncrease(String cui, SalaryIncreaseCommand command) {

        Employee employee = findEmployees.findEmployeeByCui(cui)
                .orElseThrow(() -> new EmployeeNotFoundException(cui));

        EmploymentPeriod lastPeriod = findEmploymentPeriods.findByEmployeeAndEndDate(employee, null)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró un EmploymentPeriod activo"));

        if (!command.date().isAfter(lastPeriod.getStartDate())) {
            throw new InvalidDateRangeException(
                    lastPeriod.getStartDate(),
                    command.date(),
                    "última fecha de inicio",
                    "fecha de aumento");
        }

        employee.increaseSalary(command.salaryIncrease());
        storeEmployee.save(employee);

        lastPeriod.endPeriod(command.date());
        storeEmploymentPeriod.save(lastPeriod);

        EmploymentPeriod newPeriod = new EmploymentPeriod(
                EmploymentPeriodId.generate().value(),
                employee,
                PeriodType.AUMENTO,
                command.date(),
                employee.getSalary(),
                command.notes() != null ? command.notes() : "Aumento de salario"
        );
        storeEmploymentPeriod.save(newPeriod);

        return employee;
    }
}

