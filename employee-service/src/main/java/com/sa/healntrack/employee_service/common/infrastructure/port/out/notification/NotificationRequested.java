package com.sa.healntrack.employee_service.common.infrastructure.port.out.notification;

public record NotificationRequested (

    String requestId,
    String to,
    String toName,
    String subject,
    String bodyHtml
){}