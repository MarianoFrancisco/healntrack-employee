package com.sa.healntrack.employee_service.employment_period.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sa.healntrack.employee_service.common.application.exception.EntityNotFoundException;
import com.sa.healntrack.employee_service.common.application.exception.InvalidDateRangeException;
import com.sa.healntrack.employee_service.employment_period.application.exception.EmployeeNotFoundException;
import com.sa.healntrack.employee_service.employment_period.application.port.in.terminate_employment.TerminateEmployment;
import com.sa.healntrack.employee_service.employment_period.application.port.in.terminate_employment.TerminateEmploymentCommand;
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
public class TerminateEmploymentImpl implements TerminateEmployment {

    private final FindEmployees findEmployees;
    private final FindEmploymentPeriods findEmploymentPeriods;
    private final StoreEmployee storeEmployee;
    private final StoreEmploymentPeriod storeEmploymentPeriod;

    public TerminateEmploymentImpl(
            FindEmployees findEmployees,
            FindEmploymentPeriods findEmploymentPeriods,
            StoreEmployee storeEmployee,
            StoreEmploymentPeriod storeEmploymentPeriod) {
        this.findEmployees = findEmployees;
        this.findEmploymentPeriods = findEmploymentPeriods;
        this.storeEmployee = storeEmployee;
        this.storeEmploymentPeriod = storeEmploymentPeriod;
    }

    @Override
    public Employee terminateEmployment(String cui, TerminateEmploymentCommand command) {

        Employee employee = findEmployees.findEmployeeByCui(cui)
                .orElseThrow(() -> new EmployeeNotFoundException(cui));

        if (!(command.terminationType() == PeriodType.DESPIDO ||
              command.terminationType() == PeriodType.RENUNCIA)) {
            throw new IllegalArgumentException(
                    "El tipo de terminación debe ser DESPIDO o RENUNCIA");
        }

        EmploymentPeriod lastPeriod = findEmploymentPeriods.findByEmployeeAndEndDate(employee, null)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró un EmploymentPeriod activo"));

        if (!command.date().isAfter(lastPeriod.getStartDate())) {
            throw new InvalidDateRangeException(
                    lastPeriod.getStartDate(),
                    command.date(),
                    "fecha de inicio del periodo activo",
                    "fecha de terminación"
            );
        }

        lastPeriod.endPeriod(command.date());
        storeEmploymentPeriod.save(lastPeriod);

        EmploymentPeriod terminationPeriod = new EmploymentPeriod(
                EmploymentPeriodId.generate().value(),
                employee,
                command.terminationType(),
                command.date(),
                employee.getSalary(),
                command.reason()
        );
        storeEmploymentPeriod.save(terminationPeriod);

        employee.deactivate();
        storeEmployee.save(employee);

        return employee;
    }
}
