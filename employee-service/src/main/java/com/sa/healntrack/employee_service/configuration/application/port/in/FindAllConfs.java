package com.sa.healntrack.employee_service.configuration.application.port.in;

import java.util.List;

import com.sa.healntrack.employee_service.configuration.domain.Configuration;

public interface FindAllConfs {
    List<Configuration> findAll();
}
