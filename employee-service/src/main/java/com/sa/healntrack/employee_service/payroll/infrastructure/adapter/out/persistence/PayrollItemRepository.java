package com.sa.healntrack.employee_service.payroll.infrastructure.adapter.out.persistence;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.sa.healntrack.employee_service.payroll.application.port.in.command.FindAllPayrollsQuery;
import com.sa.healntrack.employee_service.payroll.application.port.out.FindPayrollItems;
import com.sa.healntrack.employee_service.payroll.domain.PayrollItem;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PayrollItemRepository implements FindPayrollItems {

    private final PayrollItemJpaRepository payrollItemJpaRepository;

    @Override
    public List<PayrollItem> findAll(FindAllPayrollsQuery query) {

        Specification<PayrollItemEntity> spec = Specification.allOf(
            Specification.anyOf(
                PayrollItemSpecs.employeeFullnameContains(query.employee()),
                PayrollItemSpecs.employeeCuiEquals(query.employee())
            ),
            Specification.anyOf(
                PayrollItemSpecs.departmentNameContains(query.department()),
                PayrollItemSpecs.departmentCodeEquals(query.department())
            ),
            PayrollItemSpecs.paydayFrom(query.paydayFrom()),
            PayrollItemSpecs.paydayTo(query.paydayTo()),
            PayrollItemSpecs.periodOverlaps(query.startDate(), query.endDate())
        );

        return payrollItemJpaRepository.findAll(spec)
                .stream()
                .map(PayrollItemEntityMapper::toDomain)
                .toList();
    }
}
