package com.sa.healntrack.employee_service.department.infrastructure.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "department")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "code")
public class DepartmentEntity {

    @Id
    private String code;
    private String name;
    private String description;
    private boolean isActive;
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
