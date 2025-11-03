package com.sa.healntrack.employee_service.configuration.application.port.out;

import com.sa.healntrack.employee_service.configuration.domain.Configuration;

public interface StoreConf {
    Configuration save(Configuration vacationConf);
}
