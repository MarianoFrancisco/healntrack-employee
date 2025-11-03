package com.sa.healntrack.employee_service.vacation.application.port.out;

import com.sa.healntrack.employee_service.vacation.domain.VacationConf;

public interface StoreVacationConf {
    VacationConf save(VacationConf vacationConf);
}
