package com.sa.healntrack.employee_service.employment.application.mapper;

import java.math.BigDecimal;

import com.sa.healntrack.employee_service.department.domain.Department;
import com.sa.healntrack.employee_service.employment.application.port.in.hire_employee.HireEmployeeCommand;
import com.sa.healntrack.employee_service.employment.application.port.in.update_employee.UpdateEmployeeCommand;
import com.sa.healntrack.employee_service.employment.domain.Employee;
import com.sa.healntrack.employee_service.employment.domain.value.EmployeeId;

public class EmployeeMapper {
    private EmployeeMapper() {
        // Private constructor to prevent instantiation
    }

    public static Employee toDomain(HireEmployeeCommand command, Department department) {
        return new Employee(
                EmployeeId.generate().value(),
                command.cui(),
                command.nit(),
                command.fullname(),
                command.email(),
                command.phoneNumber(),
                command.birthDate(),
                department,
                command.salary(),
                command.igssPercent(),
                command.irtraPercent());
    }

    public static Employee updateEmployee(Employee existing, UpdateEmployeeCommand command) {
        String updatedFullname = command.fullname() != null ? command.fullname() : existing.getFullname();
        String updatedPhone = command.phoneNumber() != null ? command.phoneNumber() : existing.getPhoneNumber().value();
        BigDecimal updatedIgss = command.igssPercent() != null ? command.igssPercent() : existing.getIgssPercent();
        BigDecimal updatedIrtra = command.irtraPercent() != null ? command.irtraPercent() : existing.getIrtraPercent();

        Employee updated = new Employee(
                existing.getId().value(),
                existing.getCui().value(),
                existing.getNit().value(),
                updatedFullname,
                existing.getEmail().value(),
                updatedPhone,
                existing.getBirthDate(),
                existing.getDepartment(),
                existing.getSalary(),
                updatedIgss,
                updatedIrtra);

        if (!existing.isActive()) {
            updated.deactivate();
        }

        return updated;
    }

}
