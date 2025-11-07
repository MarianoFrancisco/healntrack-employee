package com.sa.healntrack.employee_service.vacation.application.mapper;

import com.sa.healntrack.employee_service.employment.domain.Employee;
import com.sa.healntrack.employee_service.vacation.application.port.in.command.RequestVacationCommand;
import com.sa.healntrack.employee_service.vacation.domain.Vacation;
import com.sa.healntrack.employee_service.vacation.domain.VacationId;

public class VacationMapper {
    private VacationMapper() {
        // Private constructor to prevent instantiation
    }

    public static Vacation toDomain(RequestVacationCommand command, Employee employee){
        return new Vacation(
            VacationId.generate().value(),
            employee,
            command.requestedAt(),
            command.startDate(),
            command.endDate()
        );
    }

}
