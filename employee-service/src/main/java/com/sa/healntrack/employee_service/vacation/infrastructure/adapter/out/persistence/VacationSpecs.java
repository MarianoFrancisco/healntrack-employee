package com.sa.healntrack.employee_service.vacation.infrastructure.adapter.out.persistence;

import java.time.LocalDate;
import org.springframework.data.jpa.domain.Specification;

import com.sa.healntrack.employee_service.vacation.domain.VacationStatus;

public class VacationSpecs {

    // employee
    public static Specification<VacationEntity> employeeCuiEquals(String cui) {
        return (root, query, cb) -> (cui == null || cui.isBlank())
                ? null
                : cb.equal(root.join("employee").get("cui"), cui);
    }

    public static Specification<VacationEntity> employeeFullnameContains(String fullname) {
        return (root, query, cb) -> (fullname == null || fullname.isBlank())
                ? null
                : cb.like(
                        cb.lower(root.join("employee").get("fullname")),
                        "%" + fullname.toLowerCase() + "%"
                );
    }

    // department
    public static Specification<VacationEntity> departmentCodeEquals(String code) {
        return (root, query, cb) -> (code == null || code.isBlank())
                ? null
                : cb.equal(root.join("employee").join("department").get("code"), code);
    }

    public static Specification<VacationEntity> departmentNameContains(String name) {
        return (root, query, cb) -> (name == null || name.isBlank())
                ? null
                : cb.like(
                        cb.lower(root.join("employee").join("department").get("name")),
                        "%" + name.toLowerCase() + "%"
                );
    }

    // vacation periods

    public static Specification<VacationEntity> startDateGreaterThanOrEqualTo(LocalDate startDate) {
        return (root, query, cb) -> (startDate == null)
                ? null
                : cb.greaterThanOrEqualTo(root.get("startDate"), startDate);
    }

    public static Specification<VacationEntity> endDateLessThanOrEqualTo(LocalDate endDate) {
        return (root, query, cb) -> (endDate == null)
                ? null
                : cb.lessThanOrEqualTo(root.get("endDate"), endDate);
    }

    // request date filters

    public static Specification<VacationEntity> requestedAtGreaterThanOrEqualTo(LocalDate fromDate) {
        return (root, query, cb) -> (fromDate == null)
                ? null
                : cb.greaterThanOrEqualTo(root.get("requestedAt"), fromDate.atStartOfDay());
    }

    public static Specification<VacationEntity> requestedAtLessThanOrEqualTo(LocalDate toDate) {
        return (root, query, cb) -> (toDate == null)
                ? null
                : cb.lessThanOrEqualTo(root.get("requestedAt"), toDate.atTime(23, 59, 59));
    }

    // status

    public static Specification<VacationEntity> statusEquals(VacationStatus status) {
        return (root, query, cb) -> (status == null)
                ? null
                : cb.equal(root.get("status"), status);
    }
}

