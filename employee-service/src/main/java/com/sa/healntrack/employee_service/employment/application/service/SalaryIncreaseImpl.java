package com.sa.healntrack.employee_service.employment.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sa.healntrack.employee_service.common.application.exception.EntityNotFoundException;
import com.sa.healntrack.employee_service.common.application.exception.InvalidDateRangeException;
import com.sa.healntrack.employee_service.employment.application.exception.EmployeeNotFoundException;
import com.sa.healntrack.employee_service.employment.application.port.in.salary_increase.SalaryIncrease;
import com.sa.healntrack.employee_service.employment.application.port.in.salary_increase.SalaryIncreaseCommand;
import com.sa.healntrack.employee_service.employment.application.port.out.FindEmployees;
import com.sa.healntrack.employee_service.employment.application.port.out.FindEmployments;
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
public class SalaryIncreaseImpl implements SalaryIncrease {

    private final FindEmployees findEmployees;
    private final FindEmployments findEmployments;
    private final StoreEmployment storeEmployment;
    private final StoreEmployee storeEmployee;

    @Override
    public Employee applySalaryIncrease(String cui, SalaryIncreaseCommand command) {

        Employee employee = findEmployees.findEmployeeByCui(cui)
                .orElseThrow(() -> new EmployeeNotFoundException(cui));

        Employment lastPeriod = findEmployments.findByEmployeeAndEndDate(employee, null)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró un Employment activo"));

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
        storeEmployment.save(lastPeriod);

        Employment newPeriod = new Employment(
                EmploymentId.generate().value(),
                employee,
                PeriodType.AUMENTO,
                command.date(),
                employee.getSalary(),
                command.notes() != null ? command.notes() : "Aumento de salario"
        );
        storeEmployment.save(newPeriod);

        return employee;
    }
}

