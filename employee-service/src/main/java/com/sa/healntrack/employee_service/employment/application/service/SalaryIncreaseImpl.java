package com.sa.healntrack.employee_service.employment.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sa.healntrack.employee_service.common.application.exception.EntityNotFoundException;
import com.sa.healntrack.employee_service.common.application.exception.InvalidDateRangeException;
import com.sa.healntrack.employee_service.common.application.port.out.NotificationPublisher;
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
    private final NotificationPublisher notificationPublisher;

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
        sendNotificationEmail(employee);

        return employee;
    }

    private void sendNotificationEmail(Employee employee) {
        String subject = "Aumento de salario";
        String bodyHtml = String.format(
                        "<h1>Felicidades, %s!</h1><p>Nos complace informarte que has recibido un aumento de salario. Asi que ahora tu salario actual es de Q.%s. Gracias por tu dedicación y esfuerzo.</p>",
                        employee.getFullname(), employee.getSalary());
        notificationPublisher.publish(
                        UUID.randomUUID().toString(),
                        employee.getEmail().value(),
                        employee.getFullname(),
                        subject,
                        bodyHtml);
    }
}

