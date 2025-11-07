package com.sa.healntrack.employee_service.payroll.infrastructure.adapter.out.persistence;

import com.sa.healntrack.employee_service.employment.infrastructure.adapter.out.persistence.mapper.EmployeeEntityMapper;
import com.sa.healntrack.employee_service.department.infrastructure.adapter.out.persistence.DepartmentEntityMapper;
import com.sa.healntrack.employee_service.payroll.domain.Payroll;
import com.sa.healntrack.employee_service.payroll.domain.PayrollItem;

public class PayrollItemEntityMapper {

    private PayrollItemEntityMapper() {
        // Prevent instantiation
    }

    public static PayrollItem toDomain(PayrollItemEntity entity) {
        Payroll payroll = PayrollEntityMapper.toDomain(entity.getPayroll());
        return new PayrollItem(
                entity.getId(),
                payroll,
                EmployeeEntityMapper.toDomain(entity.getEmployee()),
                DepartmentEntityMapper.toDomain(entity.getDepartment()),
                entity.getGrossSalary(),
                entity.getIgssDeduction(),
                entity.getIrtraDeduction(),
                entity.getNetSalary(),
                entity.getNotes()
        );
    }

    public static PayrollItemEntity toEntity(PayrollItem domain, PayrollEntity payrollEntity) {
        return PayrollItemEntity.builder()
                .id(domain.getId().value())
                .payroll(payrollEntity)
                .employee(EmployeeEntityMapper.toEntity(domain.getEmployee()))
                .department(DepartmentEntityMapper.toEntity(domain.getDepartment()))
                .grossSalary(domain.getGrossSalary())
                .igssDeduction(domain.getIgssDeduction())
                .irtraDeduction(domain.getIrtraDeduction())
                .netSalary(domain.getNetSalary())
                .notes(domain.getNotes())
                .build();
    }
}
