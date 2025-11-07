package com.sa.healntrack.employee_service.payroll.infrastructure.adapter.out.messaging;

import java.util.List;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sa.healntrack.employee_service.common.infrastructure.exception.MessageSerializationException;
import com.sa.healntrack.employee_service.payroll.application.port.out.messaging.PublishEmployeePaymentRegistered;
import com.sa.healntrack.employee_service.payroll.domain.PayrollItem;
import com.sa.healntrack.employee_service.payroll.infrastructure.adapter.out.messaging.mapper.PayrollMessagingMapper;
import com.sa.healntrack.employee_service.payroll.infrastructure.adapter.out.messaging.message.EmployeePaymentRegisteredMessage;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class PayrollPublisher implements PublishEmployeePaymentRegistered {
    
    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, byte[]> template;

    @Override
    public void publishRegisteredMessage(List<PayrollItem> items) {
        for (PayrollItem item : items) {
            EmployeePaymentRegisteredMessage message = PayrollMessagingMapper.toRegisteredMessage(item);
            try {
                template.send("employee.payment.registered", objectMapper.writeValueAsBytes(message));
            } catch (JsonProcessingException e) {
                throw new MessageSerializationException("No se pudo serializar el mensaje");
            }
            
        }
    }


}
