package com.sa.healntrack.employee_service.employment_period.application.port.in.terminate_employment;

import com.sa.healntrack.employee_service.employment_period.domain.Employee;

public interface TerminateEmployment {
    Employee terminateEmployment(String cui, TerminateEmploymentCommand command);
}
