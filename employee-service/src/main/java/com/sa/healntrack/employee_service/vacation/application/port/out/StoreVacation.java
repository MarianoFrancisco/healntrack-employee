package com.sa.healntrack.employee_service.vacation.application.port.out;

import com.sa.healntrack.employee_service.vacation.domain.Vacation;

public interface StoreVacation {
    Vacation save(Vacation vacation);
}
