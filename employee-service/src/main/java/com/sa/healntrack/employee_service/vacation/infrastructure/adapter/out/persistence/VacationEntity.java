package com.sa.healntrack.employee_service.vacation.infrastructure.adapter.out.persistence;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.sa.healntrack.employee_service.employment.infrastructure.adapter.out.persistence.entity.DepartmentManagerEntity;
import com.sa.healntrack.employee_service.employment.infrastructure.adapter.out.persistence.entity.EmployeeEntity;
import com.sa.healntrack.employee_service.vacation.domain.VacationStatus;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "vacation")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class VacationEntity {

    @Id
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_id", nullable = false)
    private EmployeeEntity employee;

    private LocalDateTime requestedAt;
    private LocalDate startDate;
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private VacationStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "approved_by")
    private DepartmentManagerEntity approvedBy;

    private LocalDate approvedAt;
    private LocalDateTime signedAt;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}

