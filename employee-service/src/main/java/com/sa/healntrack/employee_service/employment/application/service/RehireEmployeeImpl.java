package com.sa.healntrack.employee_service.employment.application.service;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sa.healntrack.employee_service.common.application.port.out.NotificationPublisher;
import com.sa.healntrack.employee_service.department.application.exception.DepartmentNotFoundException;
import com.sa.healntrack.employee_service.department.application.port.out.persistence.FindDepartments;
import com.sa.healntrack.employee_service.department.domain.Department;
import com.sa.healntrack.employee_service.employment.application.exception.EmployeeNotFoundException;
import com.sa.healntrack.employee_service.employment.application.port.in.hire_employee.RehireEmployee;
import com.sa.healntrack.employee_service.employment.application.port.in.hire_employee.RehireEmployeeCommand;
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
public class RehireEmployeeImpl implements RehireEmployee {

    private final FindEmployees findEmployees;
    private final FindDepartments findDepartments;
    private final StoreEmployee storeEmployee;
    private final StoreEmployment storeEmployment;
    private final FindEmployments findEmployments;
    private final NotificationPublisher notificationPublisher;

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

        findEmployments.findByEmployeeAndEndDate(existingEmployee, null)
                .ifPresent(activePeriod -> {
                    activePeriod.endPeriod(command.startDate() != null ? command.startDate() : LocalDate.now());
                    storeEmployment.save(activePeriod);
                });

        Employment newEmployment = new Employment(
                EmploymentId.generate().value(),
                existingEmployee,
                PeriodType.RECONTRATACION,
                command.startDate() != null ? command.startDate() : LocalDate.now(),
                existingEmployee.getSalary(),
                command.notes() != null ? command.notes() : "Recontratación del empleado");

        storeEmployment.save(newEmployment);
        sendNotificationEmail(existingEmployee);

        return existingEmployee;
    }

    private void sendNotificationEmail(Employee employee) {
        String subject = "Recontratación de empleado";
        String bodyHtml = String.format("<h1>Bienvenido de nuevo, %s!</h1><p>Estamos felices de tenerte de vuelta en el equipo.</p>", employee.getFullname());

        notificationPublisher.publish(
            UUID.randomUUID().toString(),
            employee.getEmail().value(),
            employee.getFullname(),
            subject,
            bodyHtml
        );
    }
}
