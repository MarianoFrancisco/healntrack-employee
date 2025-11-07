package com.sa.healntrack.employee_service.employment.infrastructure.adapter.out.messaging;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sa.healntrack.employee_service.common.infrastructure.exception.MessageSerializationException;
import com.sa.healntrack.employee_service.employment.application.port.out.messaging.PublishEmployeeCreated;
import com.sa.healntrack.employee_service.employment.application.port.out.messaging.PublishEmployeeUpdated;
import com.sa.healntrack.employee_service.employment.domain.Employee;
import com.sa.healntrack.employee_service.employment.infrastructure.adapter.out.messaging.mapper.EmployeeMessagingMapper;
import com.sa.healntrack.employee_service.employment.infrastructure.adapter.out.messaging.message.EmployeeCreatedMessage;
import com.sa.healntrack.employee_service.employment.infrastructure.adapter.out.messaging.message.EmployeeUpdatedMessage;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class EmployeePublisher implements PublishEmployeeCreated, PublishEmployeeUpdated {
    
    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, byte[]> template;

    @Override
    public void publishCreatedMessage(Employee employee) {
        EmployeeCreatedMessage message = EmployeeMessagingMapper.toCreatedMessage(employee);
        try {
            template.send("employee.created", objectMapper.writeValueAsBytes(message));
        } catch (JsonProcessingException e) {
            throw new MessageSerializationException("No se pudo serializar el mensaje");
        }
    }

    @Override
    public void publishUpdatedMessage(Employee employee) {
        EmployeeUpdatedMessage message = EmployeeMessagingMapper.toUpdatedMessage(employee);
        try {
            template.send("employee.updated", objectMapper.writeValueAsBytes(message));
        } catch (JsonProcessingException e) {
            throw new MessageSerializationException("No se pudo serializar el mensaje");
        }
    }

}
