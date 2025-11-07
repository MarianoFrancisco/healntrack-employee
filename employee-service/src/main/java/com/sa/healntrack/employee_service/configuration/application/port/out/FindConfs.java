package com.sa.healntrack.employee_service.configuration.application.port.out;

import java.util.List;
import java.util.Optional;

import com.sa.healntrack.employee_service.configuration.domain.Configuration;

public interface FindConfs {
    Optional<Configuration> findByKey(String key);
    List<Configuration> findAll();
}
