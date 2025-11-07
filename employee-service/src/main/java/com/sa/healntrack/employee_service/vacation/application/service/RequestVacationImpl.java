package com.sa.healntrack.employee_service.vacation.application.service;

import java.time.temporal.ChronoUnit;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sa.healntrack.employee_service.common.application.exception.EntityNotFoundException;
import com.sa.healntrack.employee_service.common.application.port.out.NotificationPublisher;
import com.sa.healntrack.employee_service.configuration.application.port.out.FindConfs;
import com.sa.healntrack.employee_service.configuration.domain.Configuration;
import com.sa.healntrack.employee_service.employment.application.exception.EmployeeNotFoundException;
import com.sa.healntrack.employee_service.employment.application.port.out.FindDepartmentManagers;
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
    private final FindDepartmentManagers findDepartmentManagers;
    private final NotificationPublisher notificationPublisher;

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

        Vacation savedVacation = storeVacation.save(vacation);

        sendNotificationEmail(savedVacation, employee, false);
        findDepartmentManagers.findManagerByDepartmentAndIsActive(employee.getDepartment().getCode().value(), true)
                .ifPresent(manager -> sendNotificationEmail(savedVacation, employee, true));

        return savedVacation;
    }

    private int getConfValue(String key) {
        return findConfs.findByKey(key)
                .map(Configuration::getValue)
                .orElseThrow(() -> new EntityNotFoundException("Configuración no encontrada para la clave: " + key));
    }

    private void sendNotificationEmail(Vacation vacation, Employee employee, boolean isManager) {
        String subject = "Solicitud de Vacaciones";
        String bodyHtml;

        if (isManager) {
            bodyHtml = String.format(
                    "<h1>Nueva Solicitud de Vacaciones</h1>" +
                            "<p>El empleado <strong>%s</strong> ha solicitado vacaciones.</p>" +
                            "<p><strong>Periodo:</strong> %s a %s</p>" +
                            "<p><strong>Fecha de solicitud:</strong> %s</p>" +
                            "<p>Por favor, revise la solicitud en el sistema.</p>",
                    employee.getFullname(),
                    vacation.getPeriod().startDate(),
                    vacation.getPeriod().endDate(),
                    vacation.getRequestedAt());
        } else {
            bodyHtml = String.format(
                    "<h1>¡Hola %s!</h1>" +
                            "<p>Tu solicitud de vacaciones ha sido registrada exitosamente.</p>" +
                            "<p><strong>Periodo:</strong> %s a %s</p>" +
                            "<p><strong>Fecha de solicitud:</strong> %s</p>" +
                            "<p>Recibirás una notificación cuando sea revisada.</p>",
                    employee.getFullname().split(" ")[0],
                    vacation.getPeriod().startDate(),
                    vacation.getPeriod().endDate(),
                    vacation.getRequestedAt());
        }

        notificationPublisher.publish(
                UUID.randomUUID().toString(),
                employee.getEmail().value(),
                employee.getFullname(),
                subject,
                bodyHtml);
    }

}
