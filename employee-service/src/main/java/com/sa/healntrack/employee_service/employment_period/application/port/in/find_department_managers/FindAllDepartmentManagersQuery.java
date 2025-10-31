package com.sa.healntrack.employee_service.employment_period.application.port.in.find_department_managers;

import java.time.LocalDate;

public record FindAllDepartmentManagersQuery(
    String employee,
    String department,
    LocalDate fromDate,
    LocalDate toDate,
    Boolean isActive

) {}
