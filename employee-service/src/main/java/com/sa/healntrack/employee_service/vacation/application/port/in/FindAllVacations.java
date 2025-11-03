package com.sa.healntrack.employee_service.vacation.application.port.in;

import java.util.List;

import com.sa.healntrack.employee_service.vacation.application.port.in.command.FindAllVacationsQuery;
import com.sa.healntrack.employee_service.vacation.domain.Vacation;

public interface FindAllVacations {
    List<Vacation> findAllVacations(FindAllVacationsQuery query);
}
