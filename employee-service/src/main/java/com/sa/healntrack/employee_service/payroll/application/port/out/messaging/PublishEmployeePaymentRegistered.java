package com.sa.healntrack.employee_service.payroll.application.port.out.messaging;

import java.util.List;

import com.sa.healntrack.employee_service.payroll.domain.PayrollItem;

public interface PublishEmployeePaymentRegistered {
    
    void publishRegisteredMessage(List<PayrollItem> items);

}
