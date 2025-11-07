package com.sa.healntrack.employee_service.employment.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sa.healntrack.employee_service.common.application.port.out.NotificationPublisher;
import com.sa.healntrack.employee_service.employment.application.exception.EmployeeNotFoundException;
import com.sa.healntrack.employee_service.employment.application.mapper.EmployeeMapper;
import com.sa.healntrack.employee_service.employment.application.port.in.update_employee.UpdateEmployee;
import com.sa.healntrack.employee_service.employment.application.port.in.update_employee.UpdateEmployeeCommand;
import com.sa.healntrack.employee_service.employment.application.port.out.FindEmployees;
import com.sa.healntrack.employee_service.employment.application.port.out.StoreEmployee;
import com.sa.healntrack.employee_service.employment.application.port.out.messaging.PublishEmployeeUpdated;
import com.sa.healntrack.employee_service.employment.domain.Employee;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class UpdateEmployeeImpl implements UpdateEmployee {
    private final StoreEmployee storeEmployee;
    private final FindEmployees findEmployees;
    private final NotificationPublisher notificationPublisher;
    private final PublishEmployeeUpdated publishEmployeeUpdated;

    @Override
    public Employee updateEmployee(String cui, UpdateEmployeeCommand command) {
        Employee existing = findEmployees.findEmployeeByCui(cui)
                .orElseThrow(() -> new EmployeeNotFoundException(cui));

        Employee updated = EmployeeMapper.updateEmployee(existing, command);
        Employee saved = storeEmployee.save(updated);
        sendNotificationEmail(saved, command);
        publishEmployeeUpdated.publishUpdatedMessage(saved);
        return saved;
    }

    private void sendNotificationEmail(Employee employee, UpdateEmployeeCommand command) {
        String subject = "Actualización de información laboral";

        String changesSummary = buildChangesSummary(command).toString();

        String bodyHtml = String.format(
                "<h1>Hola, %s!</h1>" +
                        "<p>Tu información laboral ha sido actualizada exitosamente.</p>" +
                        "%s" +
                        "<p>Si no solicitaste estos cambios, por favor contacta al gerente o jefe inmediato.</p>",
                employee.getFullname(),
                changesSummary);

        notificationPublisher.publish(
                UUID.randomUUID().toString(),
                employee.getEmail().value(),
                employee.getFullname(),
                subject,
                bodyHtml);
    }

    private StringBuilder buildChangesSummary(UpdateEmployeeCommand command) {
        StringBuilder summary = new StringBuilder(
                "<h1>Actualización de Información</h1><p>Se han realizado los siguientes cambios en tu información laboral:</p><ul>");

        if (command.fullname() != null) {
            summary.append(String.format("<li>Nombre completo actualizado a: %s</li>", command.fullname()));
        }
        if (command.phoneNumber() != null) {
            summary.append(String.format("<li>Número de teléfono actualizado a: %s</li>", command.phoneNumber()));
        }
        if (command.igssPercent() != null) {
            summary.append(String.format("<li>Porcentaje de IGSS actualizado a: %s</li>", command.igssPercent()));
        }
        if (command.irtraPercent() != null) {
            summary.append(String.format("<li>Porcentaje de IRTRA actualizado a: %s</li>", command.irtraPercent()));
        }

        summary.append("</ul>");
        return summary;
    }

}
