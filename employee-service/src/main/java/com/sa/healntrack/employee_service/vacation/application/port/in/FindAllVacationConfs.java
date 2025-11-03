package com.sa.healntrack.employee_service.vacation.application.port.in;

import java.util.List;

import com.sa.healntrack.employee_service.vacation.domain.VacationConf;

public interface FindAllVacationConfs {
    List<VacationConf> findAll();
}
