package com.sa.healntrack.employee_service.payroll.infrastructure.adapter.out.persistence;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import com.sa.healntrack.employee_service.payroll.domain.PayrollType;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "payroll")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class PayrollEntity {

    @Id
    private UUID id;

    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate payDay;

    @Enumerated(EnumType.STRING)
    private PayrollType type;

    private BigDecimal totalGrossAmount;
    private BigDecimal totalIgssDeduction;
    private BigDecimal totalIrtraDeduction;
    private BigDecimal totalNetAmount;

    @OneToMany(mappedBy = "payroll", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<PayrollItemEntity> items;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
