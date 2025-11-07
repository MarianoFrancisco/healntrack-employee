package com.sa.healntrack.employee_service.employment.application.port.out.messaging;

import com.sa.healntrack.employee_service.employment.domain.Employee;

public interface PublishEmployeeCreated {
    
    void publishCreatedMessage(Employee employee);

}
