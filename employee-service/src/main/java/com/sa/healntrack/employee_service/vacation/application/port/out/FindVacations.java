package com.sa.healntrack.employee_service.vacation.application.port.out;

import java.util.*;

import com.sa.healntrack.employee_service.employment.domain.Employee;
import com.sa.healntrack.employee_service.vacation.application.port.in.command.FindAllVacationsQuery;
import com.sa.healntrack.employee_service.vacation.domain.Vacation;
import com.sa.healntrack.employee_service.vacation.domain.VacationStatus;

public interface FindVacations {
    List<Vacation> findAll(FindAllVacationsQuery query);
    Optional<Vacation> findById(UUID id);
    boolean existsByEmployeeAndStatus(Employee employee, VacationStatus status);
}
