package com.sa.healntrack.employee_service.payroll.infrastructure.adapter.out.persistence;

import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;

import com.sa.healntrack.employee_service.payroll.domain.PayrollType;

public class PayrollSpecs {

    public static Specification<PayrollEntity> paydayGreaterThanOrEqualTo(LocalDate paydayFrom) {
        return (root, query, cb) -> (paydayFrom == null)
                ? null
                : cb.greaterThanOrEqualTo(root.get("payDay"), paydayFrom);
    }

    public static Specification<PayrollEntity> paydayLessThanOrEqualTo(LocalDate paydayTo) {
        return (root, query, cb) -> (paydayTo == null)
                ? null
                : cb.lessThanOrEqualTo(root.get("payDay"), paydayTo);
    }

    public static Specification<PayrollEntity> typeEquals(PayrollType type) {
        return (root, query, cb) -> (type == null)
                ? null
                : cb.equal(root.get("type"), type);
    }

    public static Specification<PayrollEntity> periodOverlaps(LocalDate startDate, LocalDate endDate) {
        return (root, query, cb) -> {
            if (startDate == null || endDate == null) {
                return null;
            }
            // Overlap: (payroll.startDate <= endDate) AND (payroll.endDate >= startDate)
            return cb.and(
                    cb.lessThanOrEqualTo(root.get("startDate"), endDate),
                    cb.greaterThanOrEqualTo(root.get("endDate"), startDate));
        };
}

}