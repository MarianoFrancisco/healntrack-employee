package com.sa.healntrack.employee_service.common.application.port.out;

public interface NotificationPublisher {
    void publish(String requestId, String to, String toName, String subject, String bodyHtml);
}
