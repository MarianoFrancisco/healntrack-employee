package com.sa.healntrack.employee_service.vacation.application.port.out;

import java.util.List;
import java.util.Optional;

import com.sa.healntrack.employee_service.vacation.domain.VacationConf;

public interface FindVacationConfs {
    Optional<VacationConf> findByKey(String key);
    List<VacationConf> findAll();
}
