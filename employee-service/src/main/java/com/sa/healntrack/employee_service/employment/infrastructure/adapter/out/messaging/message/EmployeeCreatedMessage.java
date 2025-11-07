package com.sa.healntrack.employee_service.employment.infrastructure.adapter.out.messaging.message;

import java.util.UUID;

public record EmployeeCreatedMessage(

    UUID id,
    String cui,
    String fullName


) { }
