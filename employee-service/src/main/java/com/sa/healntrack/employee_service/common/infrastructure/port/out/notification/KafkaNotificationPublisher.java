package com.sa.healntrack.employee_service.common.infrastructure.port.out.notification;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sa.healntrack.employee_service.common.application.port.out.NotificationPublisher;
import com.sa.healntrack.employee_service.common.infrastructure.config.NotificationTopicProperties;

import lombok.RequiredArgsConstructor;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaNotificationPublisher implements NotificationPublisher {

    private final KafkaTemplate<String, byte[]> kafkaTemplate;
    private final NotificationTopicProperties notificationTopics;
    private final ObjectMapper objectMapper;

    @Override
    public void publish(String requestId, String to, String toName, String subject, String bodyHtml) {
        try {
            NotificationRequested event = new NotificationRequested(
                    requestId, to, toName, subject, bodyHtml
            );

            byte[] eventBytes = objectMapper.writeValueAsBytes(event);
            ProducerRecord<String, byte[]> record =
                    new ProducerRecord<>(notificationTopics.getRequested(), requestId, eventBytes);
            kafkaTemplate.send(record);
        } catch (Exception ex) {
            throw new IllegalArgumentException("KAFKA", ex);
        }
    }
}
