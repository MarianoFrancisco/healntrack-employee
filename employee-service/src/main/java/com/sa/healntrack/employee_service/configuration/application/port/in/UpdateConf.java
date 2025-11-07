package com.sa.healntrack.employee_service.configuration.application.port.in;

import com.sa.healntrack.employee_service.configuration.domain.Configuration;

public interface UpdateConf {
    Configuration updateConf(String key, Integer value);
}
