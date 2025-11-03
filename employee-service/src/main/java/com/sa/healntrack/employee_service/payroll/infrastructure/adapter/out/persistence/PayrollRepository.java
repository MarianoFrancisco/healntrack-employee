package com.sa.healntrack.employee_service.payroll.infrastructure.adapter.out.persistence;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.sa.healntrack.employee_service.payroll.application.port.in.command.FindAllPayrollsQuery;
import com.sa.healntrack.employee_service.payroll.application.port.out.FindPayrolls;
import com.sa.healntrack.employee_service.payroll.application.port.out.StorePayroll;
import com.sa.healntrack.employee_service.payroll.domain.Payroll;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PayrollRepository implements FindPayrolls, StorePayroll {

    private final PayrollJpaRepository jpaRepository;

    @Override
    public Payroll save(Payroll payroll) {
        PayrollEntity entity = PayrollEntityMapper.toEntity(payroll);
        PayrollEntity saved = jpaRepository.save(entity);
        return PayrollEntityMapper.toDomain(saved);
    }

    @Override
    public List<Payroll> findPayrolls(FindAllPayrollsQuery query) {

        Specification<PayrollEntity> spec = Specification.allOf(
            PayrollSpecs.paydayGreaterThanOrEqualTo(query.paydayFrom()),
            PayrollSpecs.paydayLessThanOrEqualTo(query.paydayTo()),
            PayrollSpecs.typeEquals(query.type()),
            PayrollSpecs.periodOverlaps(query.startDate(), query.endDate())
        );

        return jpaRepository.findAll(spec)
                .stream()
                .map(PayrollEntityMapper::toDomain)
                .toList();
    }

    @Override
    public boolean existsByStartDateAndEndDate(LocalDate startDate, LocalDate endDate) {
        return jpaRepository.existsByStartDateAndEndDate(startDate, endDate);
    }
}
