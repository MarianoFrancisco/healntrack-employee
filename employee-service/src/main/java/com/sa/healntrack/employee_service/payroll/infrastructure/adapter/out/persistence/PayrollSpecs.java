package com.sa.healntrack.employee_service.payroll.infrastructure.adapter.out.persistence;

import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;

import com.sa.healntrack.employee_service.payroll.domain.PayrollType;

import jakarta.persistence.criteria.Join;

public class PayrollSpecs {

    public static Specification<PayrollEntity> employeeFullnameContains(String fullname) {
        return (root, query, cb) -> {
            if (fullname == null || fullname.isBlank()) {
                return null;
            }
            // Join con payroll.items -> employee
            Join<PayrollEntity, PayrollItemEntity> items = root.join("items");
            return cb.like(
                    cb.lower(items.join("employee").get("fullname")),
                    "%" + fullname.toLowerCase() + "%");
        };
    }

    public static Specification<PayrollEntity> employeeCuiEquals(String cui) {
        return (root, query, cb) -> {
            if (cui == null || cui.isBlank()) {
                return null;
            }
            Join<Object, Object> items = root.join("items");
            return cb.equal(items.join("employee").get("cui"), cui);
        };
    }

    public static Specification<PayrollEntity> departmentNameContains(String departmentName) {
        return (root, query, cb) -> {
            if (departmentName == null || departmentName.isBlank()) {
                return null;
            }
            Join<Object, Object> items = root.join("items");
            return cb.like(
                    cb.lower(items.join("department").get("name")),
                    "%" + departmentName.toLowerCase() + "%");
        };
    }

    public static Specification<PayrollEntity> departmentCodeEquals(String departmentCode) {
        return (root, query, cb) -> {
            if (departmentCode == null || departmentCode.isBlank()) {
                return null;
            }
            Join<Object, Object> items = root.join("items");
            return cb.equal(items.join("department").get("code"), departmentCode);
        };
    }

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