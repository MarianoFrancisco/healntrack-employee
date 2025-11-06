package com.sa.healntrack.employee_service.vacation.application.port.in;

import java.util.UUID;

import com.sa.healntrack.employee_service.vacation.domain.Vacation;

public interface FindVacationById {
    Vacation findVacationById(UUID id);
}
