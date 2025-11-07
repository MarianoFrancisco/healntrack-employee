package com.sa.healntrack.employee_service.employment.application.port.out;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.sa.healntrack.employee_service.employment.application.port.in.find_employments.FindAllEmploymentsQuery;
import com.sa.healntrack.employee_service.employment.domain.Employee;
import com.sa.healntrack.employee_service.employment.domain.Employment;

public interface FindEmployments {
    List<Employment> findAllEmployments(FindAllEmploymentsQuery query);
    Optional<Employment> findByEmployeeAndEndDate(Employee employee, LocalDate endDate);
}
