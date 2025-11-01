package com.sa.healntrack.employee_service.employment.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

import com.sa.healntrack.employee_service.department.domain.Department;
import com.sa.healntrack.employee_service.employment.domain.value.CUI;
import com.sa.healntrack.employee_service.employment.domain.value.Email;
import com.sa.healntrack.employee_service.employment.domain.value.EmployeeId;
import com.sa.healntrack.employee_service.employment.domain.value.NIT;
import com.sa.healntrack.employee_service.employment.domain.value.PhoneNumber;

import lombok.Getter;

@Getter
public class Employee {
    private static final int LEGAL_AGE = 18;

    private final EmployeeId id;
    private final CUI cui;
    private final NIT nit;
    private final Email email;
    private final LocalDate birthDate;
    private String fullname;
    private PhoneNumber phoneNumber;
    private Department department;
    private BigDecimal salary;
    private BigDecimal igssPercent;
    private BigDecimal irtraPercent;
    private boolean isActive;

    public Employee(
            UUID id,
            String cui,
            String nit,
            String fullname,
            String email,
            String phoneNumber,
            LocalDate birthDate,
            Department department,
            BigDecimal salary,
            BigDecimal igssPercent,
            BigDecimal irtraPercent) {
        this.id = new EmployeeId(id);
        this.cui = new CUI(cui);
        this.nit = new NIT(nit);
        this.fullname = validateName(fullname);
        this.email = new Email(email);
        this.phoneNumber = new PhoneNumber(phoneNumber);
        this.birthDate = validateBirthDate(birthDate);
        this.department = Objects.requireNonNull(department, "El empleado debe tener un area asignada");
        this.salary = validateSalary(salary);
        this.igssPercent = validatePercentage(igssPercent);
        this.irtraPercent = validatePercentage(irtraPercent);
        this.isActive = true;
    }

    public void increaseSalary(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto a aumentar debe ser mayor que cero");
        }
        this.salary = this.salary.add(amount);
    }

    public void updateEmploymentInfo(
            String fullname,
            String phoneNumber,
            BigDecimal igssPercent,
            BigDecimal irtraPercent) {
        this.fullname = validateName(fullname);
        this.phoneNumber = new PhoneNumber(phoneNumber);
        this.igssPercent = validatePercentage(igssPercent);
        this.irtraPercent = validatePercentage(irtraPercent);
    }

    public void deactivate() {
        if (!isActive) {
            throw new IllegalStateException("El empleado ya está inactivo");
        }
        this.isActive = false;
    }

    public void rehire(
            String phoneNumber,
            Department newDepartment,
            BigDecimal newSalary,
            BigDecimal igssPercent,
            BigDecimal irtraPercent) {
        if (this.isActive) {
            throw new IllegalStateException("No se puede recontratar un empleado que ya está activo");
        }

        this.phoneNumber = new PhoneNumber(phoneNumber);
        this.department = Objects.requireNonNull(newDepartment, "El empleado debe tener un area asignada");
        this.salary = validateSalary(newSalary);
        this.igssPercent = validatePercentage(igssPercent);
        this.irtraPercent = validatePercentage(irtraPercent);
        this.isActive = true;
    }

    private String validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("El nombre completo no puede ser vacío");
        }
        return name.trim();
    }

    private LocalDate validateBirthDate(LocalDate birthDate) {
        if (birthDate == null) {
            throw new IllegalArgumentException("La fecha de nacimiento no puede ser nula");
        }

        LocalDate today = LocalDate.now();
        int age = java.time.Period.between(birthDate, today).getYears();

        if (age < LEGAL_AGE) {
            throw new IllegalArgumentException("El empleado debe ser mayor de edad");
        }

        return birthDate;
    }

    private BigDecimal validatePercentage(BigDecimal percentage) {
        if (percentage == null) {
            return BigDecimal.ZERO;
        }

        if (percentage.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(
                    String.format("El porcentaje debe ser mayor o igual a 0"));
        }

        if (percentage.compareTo(BigDecimal.ONE) >= 0) {
            throw new IllegalArgumentException(
                    String.format("El porcentaje debe ser menor a 1"));
        }

        return percentage;
    }

    private BigDecimal validateSalary(BigDecimal salary) {
        if (salary == null || salary.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El salario no puede ser nulo o negativo");
        }
        return salary;
    }
}
