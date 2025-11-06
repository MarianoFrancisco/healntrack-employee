package com.sa.healntrack.employee_service.vacation.application.service;

import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sa.healntrack.employee_service.common.application.exception.EntityNotFoundException;
import com.sa.healntrack.employee_service.configuration.application.port.out.FindConfs;
import com.sa.healntrack.employee_service.configuration.domain.Configuration;
import com.sa.healntrack.employee_service.employment.application.exception.EmployeeNotFoundException;
import com.sa.healntrack.employee_service.employment.application.port.out.FindEmployees;
import com.sa.healntrack.employee_service.employment.domain.Employee;
import com.sa.healntrack.employee_service.vacation.application.exception.DuplicateVacationRequestException;
import com.sa.healntrack.employee_service.vacation.application.exception.EmployeeHasApprovedVacationException;
import com.sa.healntrack.employee_service.vacation.application.exception.InsufficientAdvanceException;
import com.sa.healntrack.employee_service.vacation.application.exception.InvalidVacationDurationException;
import com.sa.healntrack.employee_service.vacation.application.mapper.VacationMapper;
import com.sa.healntrack.employee_service.vacation.application.port.in.RequestVacation;
import com.sa.healntrack.employee_service.vacation.application.port.in.command.RequestVacationCommand;
import com.sa.healntrack.employee_service.vacation.application.port.out.FindVacations;
import com.sa.healntrack.employee_service.vacation.application.port.out.StoreVacation;
import com.sa.healntrack.employee_service.vacation.domain.Vacation;
import com.sa.healntrack.employee_service.vacation.domain.VacationStatus;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class RequestVacationImpl implements RequestVacation {
    private static final String VACATION_DAYS_KEY = "vacation_days";
    private static final String VACATION_CHANGE_ADVANCE_DAYS_KEY = "vacation_change_advance_days";

    private final FindVacations findVacations;
    private final FindEmployees findEmployees;
    private final FindConfs findConfs;
    private final StoreVacation storeVacation;

    @Override
    public Vacation requestVacation(RequestVacationCommand command) {
        Employee employee = findEmployees.findEmployeeByCui(command.employeeCui())
                .orElseThrow(
                        () -> new EmployeeNotFoundException(command.employeeCui()));

        if (!employee.isActive()) {
            throw new EmployeeNotFoundException(command.employeeCui());
        }

        int maxVacationDays = getConfValue(VACATION_DAYS_KEY);
        int minAdvanceDays = getConfValue(VACATION_CHANGE_ADVANCE_DAYS_KEY);
        
        int year = command.startDate().getYear();
        if (findVacations.existsApprovedOrSignedVacationInYear(employee.getCui().value(), year)) {
            throw new EmployeeHasApprovedVacationException(employee.getCui().value(), year);
        }

        if (findVacations.existsByEmployeeAndStatus(employee, VacationStatus.PENDIENTE)) {
            throw new DuplicateVacationRequestException(employee.getCui().value());
        }

        long advanceDays = ChronoUnit.DAYS.between(command.requestedAt(), command.startDate());
        if (advanceDays < minAdvanceDays) {
            throw new InsufficientAdvanceException(minAdvanceDays);
        }

        long requestedDays = ChronoUnit.DAYS.between(command.startDate(), command.endDate()) + 1;
        if (requestedDays != maxVacationDays) {
            throw new InvalidVacationDurationException(maxVacationDays);
        }

        Vacation vacation = VacationMapper.toDomain(command, employee);

        return storeVacation.save(vacation);
    }

    private int getConfValue(String key) {
        return findConfs.findByKey(key)
                .map(Configuration::getValue)
                .orElseThrow(() -> new EntityNotFoundException("Configuración no encontrada para la clave: " + key));
    }

}
