package com.sa.healntrack.employee_service.department.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(of = "code")
public class Department {

    private final DepartmentCode code;
    private final String name;
    private final String description;
    private boolean isActive;

    public Department(String code,
                      String name,
                      String description) {
        this.code = new DepartmentCode(code);
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
}
