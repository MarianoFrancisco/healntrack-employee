package com.sa.healntrack.employee_service.employment.infrastructure.adapter.out.messaging.mapper;

import com.sa.healntrack.employee_service.employment.domain.Employee;
import com.sa.healntrack.employee_service.employment.infrastructure.adapter.out.messaging.message.EmployeeCreatedMessage;
import com.sa.healntrack.employee_service.employment.infrastructure.adapter.out.messaging.message.EmployeeUpdatedMessage;

public class EmployeeMessagingMapper {

    private EmployeeMessagingMapper() {

    }
    
    public static EmployeeCreatedMessage toCreatedMessage(Employee employee) {
        return new EmployeeCreatedMessage(employee.getId().value(), employee.getCui().value(), employee.getFullname());
    }

    public static EmployeeUpdatedMessage toUpdatedMessage(Employee employee) {
        return new EmployeeUpdatedMessage(employee.getId().value(), employee.getFullname());
    }

}
