package com.sa.healntrack.employee_service.employment.application.port.out.messaging;

import com.sa.healntrack.employee_service.employment.domain.Employee;

public interface PublishEmployeeUpdated {
    
    void publishUpdatedMessage(Employee employee);

}
