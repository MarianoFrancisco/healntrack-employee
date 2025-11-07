package com.sa.healntrack.employee_service.vacation.application.port.in;

import java.util.UUID;

import com.sa.healntrack.employee_service.vacation.application.port.in.command.ReviewVacationCommand;

public interface RejectVacation {
    void rejectVacation(UUID id, ReviewVacationCommand command);
}
