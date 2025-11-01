package com.sa.healntrack.employee_service.employment.application.port.in.terminate_employment;

import com.sa.healntrack.employee_service.employment.domain.Employee;

public interface TerminateEmployment {
    Employee terminateEmployment(String cui, TerminateEmploymentCommand command);
}
