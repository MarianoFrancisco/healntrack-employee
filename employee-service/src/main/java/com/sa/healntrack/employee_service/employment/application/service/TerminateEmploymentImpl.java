package com.sa.healntrack.employee_service.employment.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sa.healntrack.employee_service.common.application.exception.EntityNotFoundException;
import com.sa.healntrack.employee_service.common.application.exception.InvalidDateRangeException;
import com.sa.healntrack.employee_service.common.application.port.out.NotificationPublisher;
import com.sa.healntrack.employee_service.employment.application.exception.EmployeeNotFoundException;
import com.sa.healntrack.employee_service.employment.application.port.in.terminate_employment.TerminateEmployment;
import com.sa.healntrack.employee_service.employment.application.port.in.terminate_employment.TerminateEmploymentCommand;
import com.sa.healntrack.employee_service.employment.application.port.out.FindDepartmentManagers;
import com.sa.healntrack.employee_service.employment.application.port.out.FindEmployees;
import com.sa.healntrack.employee_service.employment.application.port.out.FindEmployments;
import com.sa.healntrack.employee_service.employment.application.port.out.StoreDepartmentManager;
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
public class TerminateEmploymentImpl implements TerminateEmployment {

        private final FindEmployees findEmployees;
        private final FindEmployments findEmployments;
        private final FindDepartmentManagers findDepartmentManagers;
        private final StoreEmployee storeEmployee;
        private final StoreEmployment storeEmployment;
        private final StoreDepartmentManager storeDepartmentManager;
        private final NotificationPublisher notificationPublisher;

        @Override
        public Employee terminateEmployment(String cui, TerminateEmploymentCommand command) {

                Employee employee = findEmployees.findEmployeeByCui(cui)
                                .orElseThrow(() -> new EmployeeNotFoundException(cui));

                employee.deactivate();

                if (!(command.terminationType() == PeriodType.DESPIDO ||
                                command.terminationType() == PeriodType.RENUNCIA)) {
                        throw new IllegalArgumentException(
                                        "El tipo de terminación debe ser DESPIDO o RENUNCIA");
                }

                Employment lastPeriod = findEmployments.findByEmployeeAndEndDate(employee, null)
                                .orElseThrow(() -> new EntityNotFoundException("No se encontró un Employment activo"));

                if (!command.date().isAfter(lastPeriod.getStartDate())) {
                        throw new InvalidDateRangeException(
                                        lastPeriod.getStartDate(),
                                        command.date(),
                                        "fecha de inicio del periodo activo",
                                        "fecha de terminación");
                }

                lastPeriod.endPeriod(command.date());
                storeEmployment.save(lastPeriod);

                findDepartmentManagers.findDepartmentManagerByEmployeeAndIsActive(employee, true)
                                .ifPresent(departmentManager -> {
                                        departmentManager.endManagement(command.date());
                                        storeDepartmentManager.save(departmentManager);
                                });

                Employment terminationPeriod = new Employment(
                                EmploymentId.generate().value(),
                                employee,
                                command.terminationType(),
                                command.date(),
                                employee.getSalary(),
                                command.reason());
                storeEmployment.save(terminationPeriod);
                storeEmployee.save(employee);

                sendNotificationEmail(employee, command.terminationType());

                return employee;
        }

        private void sendNotificationEmail(Employee employee, PeriodType terminationType) {
                String subject = "Fin de relación laboral";
                String bodyHtml = String.format(
                                "<h1>Confirmacion de %s</h1><p>Hola %s, confirmamos que tu contrato ha finalizado. Agradecemos tu contribución y te deseamos éxito en tus próximos desafíos.</p>",
                                terminationType,
                                employee.getFullname());
                notificationPublisher.publish(
                                UUID.randomUUID().toString(),
                                employee.getEmail().value(),
                                employee.getFullname(),
                                subject,
                                bodyHtml);
        }
}
