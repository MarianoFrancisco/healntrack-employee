package com.sa.healntrack.employee_service.vacation.application.port.in;

import com.sa.healntrack.employee_service.vacation.application.port.in.command.RequestVacationCommand;
import com.sa.healntrack.employee_service.vacation.domain.Vacation;

public interface RequestVacation {
    Vacation requestVacation(RequestVacationCommand command);
}
