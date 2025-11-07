package com.sa.healntrack.employee_service.vacation.application.port.in;

import java.util.UUID;

import com.sa.healntrack.employee_service.vacation.application.port.in.command.ReviewVacationCommand;

public interface ApproveVacation {
    void approveVacation(UUID id, ReviewVacationCommand command);
}
