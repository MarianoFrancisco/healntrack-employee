package com.sa.healntrack.employee_service.employment_period.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import com.sa.healntrack.employee_service.department.domain.Department;
import com.sa.healntrack.employee_service.employment_period.domain.value.CUI;
import com.sa.healntrack.employee_service.employment_period.domain.value.Email;
import com.sa.healntrack.employee_service.employment_period.domain.value.EmployeeId;
import com.sa.healntrack.employee_service.employment_period.domain.value.NIT;
import com.sa.healntrack.employee_service.employment_period.domain.value.PhoneNumber;

import lombok.Getter;

@Getter
public class Employee {
    private static final int LEGAL_AGE = 18;

    private final EmployeeId id;
    private final CUI cui;
    private final NIT nit;
    private final String fullname;
    private final Email email;
    private final PhoneNumber phoneNumber;
    private final LocalDate birthDate;
    private final Department department;
    private final BigDecimal salary;
    private final BigDecimal igssPercent;
    private final BigDecimal irtraPercent;
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
        this.department = department;
        this.salary = salary;
        this.igssPercent = igssPercent;
        this.irtraPercent = irtraPercent;
        this.isActive = true;
    }

    public void deactivate() {
        this.isActive = false;
    }

    public void activate() {
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
}
