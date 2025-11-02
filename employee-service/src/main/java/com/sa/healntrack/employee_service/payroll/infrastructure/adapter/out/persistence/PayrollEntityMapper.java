package com.sa.healntrack.employee_service.payroll.infrastructure.adapter.out.persistence;

import java.util.Set;
import java.util.stream.Collectors;

import com.sa.healntrack.employee_service.payroll.domain.Payroll;
import com.sa.healntrack.employee_service.payroll.domain.PayrollItem;

public class PayrollEntityMapper {

    private PayrollEntityMapper() {
        // Prevent instantiation
    }

    public static Payroll toDomain(PayrollEntity entity) {
        if (entity == null) return null;

        Set<PayrollItem> items = entity.getItems() != null
                ? entity.getItems().stream()
                        .map(PayrollItemEntityMapper::toDomain)
                        .collect(Collectors.toSet())
                : Set.of();

        return new Payroll(
                entity.getId(),
                items,
                entity.getStartDate(),
                entity.getEndDate(),
                entity.getPayDay(),
                entity.getType(),
                entity.getTotalGrossAmount(),
                entity.getTotalIgssDeduction(),
                entity.getTotalIrtraDeduction(),
                entity.getTotalNetAmount()
        );
    }

    public static PayrollEntity toEntity(Payroll domain) {
        if (domain == null) return null;

        PayrollEntity entity = PayrollEntity.builder()
                .id(domain.getId().value())
                .startDate(domain.getPeriod().startDate())
                .endDate(domain.getPeriod().endDate())
                .payDay(domain.getPayDay())
                .type(domain.getType())
                .totalGrossAmount(domain.getTotalGrossAmount())
                .totalIgssDeduction(domain.getTotalIgssDeduction())
                .totalIrtraDeduction(domain.getTotalIrtraDeduction())
                .totalNetAmount(domain.getTotalNetAmount())
                .build();

        if (domain.getItems() != null && !domain.getItems().isEmpty()) {
            entity.setItems(domain.getItems().stream()
                    .map(item -> PayrollItemEntityMapper.toEntity(item, entity))
                    .collect(Collectors.toSet()));
        }

        return entity;
    }
}

