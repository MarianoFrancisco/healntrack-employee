package com.sa.healntrack.employee_service.payroll.infrastructure.adapter.out.messaging.mapper;

import com.sa.healntrack.employee_service.payroll.domain.PayrollItem;
import com.sa.healntrack.employee_service.payroll.infrastructure.adapter.out.messaging.message.EmployeePaymentRegisteredMessage;

public class PayrollMessagingMapper {
    
    private  PayrollMessagingMapper() {

    }

    public static EmployeePaymentRegisteredMessage toRegisteredMessage(PayrollItem item) {
        return new EmployeePaymentRegisteredMessage(item.getEmployee().getId().value(), item.getPayroll().getPayDay(), item.getPayroll().getTotalNetAmount());
    } 

}
