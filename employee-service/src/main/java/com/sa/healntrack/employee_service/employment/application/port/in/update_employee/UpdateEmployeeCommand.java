package com.sa.healntrack.employee_service.employment.application.port.in.update_employee;

import java.math.BigDecimal;

public record UpdateEmployeeCommand(
    String fullname,
    String phoneNumber,
    BigDecimal igssPercent,
    BigDecimal irtraPercent
) {}
