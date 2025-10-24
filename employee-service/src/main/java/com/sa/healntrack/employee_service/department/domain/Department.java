package com.sa.healntrack.employee_service.department.domain;

import java.util.Objects;

public class Department {

    private final DepartmentCode code;
    private final String name;
    private final String description;
    private boolean isActive;

    public Department(DepartmentCode code,
                      String name,
                      String description) {
        this.code = Objects.requireNonNull(code, "El código no puede ser nulo");
        this.name = validateName(name);
        this.description = normalizeDescription(description);
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
            throw new IllegalArgumentException("El nombre del departamento no puede ser vacío");
        }
        return name.trim();
    }

    private String normalizeDescription(String description) {
        if (description == null || description.isBlank()) {
            return null;
        }
        return description.trim();
    }

    public DepartmentCode getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isActive() {
        return isActive;
    }
}
