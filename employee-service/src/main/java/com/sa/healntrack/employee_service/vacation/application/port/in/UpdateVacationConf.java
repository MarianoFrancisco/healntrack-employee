package com.sa.healntrack.employee_service.vacation.application.port.in;

import com.sa.healntrack.employee_service.vacation.domain.VacationConf;

public interface UpdateVacationConf {
    VacationConf updateVacationConf(String key, Integer value);
}
