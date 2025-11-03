package com.sa.healntrack.employee_service.configuration.domain;

import java.util.Objects;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(of = "key")
public class Configuration {
    private final String key;
    private Integer value;

    public Configuration(String key, Integer value) {
        this.key = Objects.requireNonNull(key, "La key no puede ser nula");
        this.value = validateValue(value);
    }

    public void setValue(Integer value) {
        this.value = validateValue(value);
    }

    private Integer validateValue(Integer value) {
        Objects.requireNonNull(value, "El valor no puede ser nulo");
        if (value <= 0) {
            throw new IllegalArgumentException("El valor no puede ser negativo o cero");
        }
        return value;
    }
}
