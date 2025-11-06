package com.sa.healntrack.employee_service.vacation.application.exception;

import java.time.LocalDate;

import com.sa.healntrack.employee_service.common.application.exception.BusinessException;
import com.sa.healntrack.employee_service.vacation.domain.VacationStatus;

public class VacationAlreadyProcessedException extends BusinessException {
    public VacationAlreadyProcessedException(LocalDate startDate,
            LocalDate endDate,
            VacationStatus currentStatus) {
        super(String.format(
                "No se puede procesar la solicitud. Las vacaciones del periodo %s - %s ya tienen estado %s",
                startDate, endDate, currentStatus));
    }
}
