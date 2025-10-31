package com.sa.healntrack.employee_service.employment_period.domain;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

import com.sa.healntrack.employee_service.department.domain.Department;
import com.sa.healntrack.employee_service.employment_period.domain.value.DepartmentManagerId;

import lombok.Getter;

@Getter
public class DepartmentManager {
    private final DepartmentManagerId id;
    private final Employee employee;
    private final Department department;
    private final LocalDate startDate;
    private LocalDate endDate;
    private boolean isActive;

    public DepartmentManager(
            UUID id,
            Employee employee,
            Department department,
            LocalDate startDate) {
        this.id = new DepartmentManagerId(id);
        this.employee = Objects.requireNonNull(employee, "El empleado no puede ser nulo");
        this.department = validateDepartment(department);
        this.startDate = validateStartDate(startDate);
        this.endDate = validateEndDate(null);
        this.isActive = true;
    }

    public void endManagement(LocalDate endDate) {
        if( !this.isActive) {
            throw new IllegalStateException("La gestión ya ha sido finalizada");
        }
        this.endDate = validateEndDate(endDate);
        this.isActive = false;
    }

    public Department validateDepartment(Department department) {
        if (department == null) {
            throw new IllegalArgumentException("El departamento no puede ser nulo");
        }
        if (department.getCode().equals(this.employee.getDepartment().getCode())) {
            throw new IllegalArgumentException("El departamento debe coincidir con el departamento del empleado");
            
        }
        return department;
    }

    private LocalDate validateStartDate(LocalDate startDate) {
        if (startDate == null) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser nula");
        }
        if (startDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser futura");
        }
        return startDate;
    }

    private LocalDate validateEndDate(LocalDate endDate) {
        if (endDate != null && endDate.isBefore(this.startDate)) {
            throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la fecha de inicio");
        }
        return endDate;
    }
}
