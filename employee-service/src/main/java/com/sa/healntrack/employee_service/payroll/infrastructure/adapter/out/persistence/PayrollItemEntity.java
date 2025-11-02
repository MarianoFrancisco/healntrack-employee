package com.sa.healntrack.employee_service.payroll.infrastructure.adapter.out.persistence;

import java.math.BigDecimal;
import java.util.UUID;

import com.sa.healntrack.employee_service.department.infrastructure.adapter.out.persistence.DepartmentEntity;
import com.sa.healntrack.employee_service.employment.infrastructure.adapter.out.persistence.entity.EmployeeEntity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "payroll_item")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = {"payroll", "employee"})
public class PayrollItemEntity {

    @Id
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payroll_id", nullable = false)
    private PayrollEntity payroll;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private EmployeeEntity employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_code", nullable = false)
    private DepartmentEntity department;

    private BigDecimal grossSalary;
    private BigDecimal igssDeduction;
    private BigDecimal irtraDeduction;
    private BigDecimal netSalary;
    private String notes;
}
