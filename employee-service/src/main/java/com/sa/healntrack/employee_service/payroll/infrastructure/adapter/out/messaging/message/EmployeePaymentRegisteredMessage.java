package com.sa.healntrack.employee_service.payroll.infrastructure.adapter.out.messaging.message;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record EmployeePaymentRegisteredMessage(

        UUID employeeId,
        LocalDate payDay,
        BigDecimal totalNetAmount

) { }
