package com.sa.healntrack.employee_service.payroll.infrastructure.adapter.out.persistence;

import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;

public class PayrollItemSpecs {

    public static Specification<PayrollItemEntity> employeeFullnameContains(String fullname) {
        return (root, query, cb) -> {
            if (fullname == null || fullname.isEmpty()) return null;
            Join<PayrollItemEntity, ?> employee = root.join("employee", JoinType.INNER);
            return cb.like(cb.lower(employee.get("fullname")), "%" + fullname.toLowerCase() + "%");
        };
    }

    public static Specification<PayrollItemEntity> employeeCuiEquals(String cui) {
        return (root, query, cb) -> {
            if (cui == null || cui.isEmpty()) return null;
            Join<PayrollItemEntity, ?> employee = root.join("employee", JoinType.INNER);
            return cb.equal(employee.get("cui"), cui);
        };
    }

    public static Specification<PayrollItemEntity> departmentNameContains(String name) {
        return (root, query, cb) -> {
            if (name == null || name.isEmpty()) return null;
            Join<PayrollItemEntity, ?> department = root.join("department", JoinType.INNER);
            return cb.like(cb.lower(department.get("name")), "%" + name.toLowerCase() + "%");
        };
    }

    public static Specification<PayrollItemEntity> departmentCodeEquals(String code) {
        return (root, query, cb) -> {
            if (code == null || code.isEmpty()) return null;
            Join<PayrollItemEntity, ?> department = root.join("department", JoinType.INNER);
            return cb.equal(department.get("code"), code);
        };
    }

    public static Specification<PayrollItemEntity> paydayFrom(LocalDate paydayFrom) {
        return (root, query, cb) -> {
            if (paydayFrom == null) return null;
            Join<PayrollItemEntity, ?> payroll = root.join("payroll", JoinType.INNER);
            return cb.greaterThanOrEqualTo(payroll.get("payDay"), paydayFrom);
        };
    }

    public static Specification<PayrollItemEntity> paydayTo(LocalDate paydayTo) {
        return (root, query, cb) -> {
            if (paydayTo == null) return null;
            Join<PayrollItemEntity, ?> payroll = root.join("payroll", JoinType.INNER);
            return cb.lessThanOrEqualTo(payroll.get("payDay"), paydayTo);
        };
    }

    public static Specification<PayrollItemEntity> periodOverlaps(LocalDate startDate, LocalDate endDate) {
        return (root, query, cb) -> {
            if (startDate == null || endDate == null) return null;
            Join<PayrollItemEntity, ?> payroll = root.join("payroll", JoinType.INNER);
            return cb.and(
                    cb.lessThanOrEqualTo(payroll.get("startDate"), endDate),
                    cb.greaterThanOrEqualTo(payroll.get("endDate"), startDate)
            );
        };
    }
}
