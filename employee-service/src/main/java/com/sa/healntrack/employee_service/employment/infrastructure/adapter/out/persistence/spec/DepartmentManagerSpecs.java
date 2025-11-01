package com.sa.healntrack.employee_service.employment.infrastructure.adapter.out.persistence.spec;

import java.time.LocalDate;
import org.springframework.data.jpa.domain.Specification;

import com.sa.healntrack.employee_service.employment.infrastructure.adapter.out.persistence.entity.DepartmentManagerEntity;

public class DepartmentManagerSpecs {

    public static Specification<DepartmentManagerEntity> employeeFullnameContains(String fullname) {
        return (root, query, criteriaBuilder) -> (fullname == null || fullname.isBlank())
                ? null
                : criteriaBuilder.like(
                        criteriaBuilder.lower(root.join("employee").get("fullname")),
                        "%" + fullname.toLowerCase() + "%");
    }

    public static Specification<DepartmentManagerEntity> employeeCuiEquals(String cui) {
        return (root, query, criteriaBuilder) -> (cui == null || cui.isBlank())
                ? null
                : criteriaBuilder.equal(root.join("employee").get("cui"), cui);
    }

    public static Specification<DepartmentManagerEntity> departmentNameContains(String departmentName) {
        return (root, query, criteriaBuilder) -> (departmentName == null || departmentName.isBlank())
                ? null
                : criteriaBuilder.like(
                        criteriaBuilder.lower(root.join("department").get("name")),
                        "%" + departmentName.toLowerCase() + "%");
    }

    public static Specification<DepartmentManagerEntity> departmentCodeEquals(String departmentCode) {
        return (root, query, criteriaBuilder) -> (departmentCode == null || departmentCode.isBlank())
                ? null
                : criteriaBuilder.equal(root.join("department").get("code"), departmentCode);
    }

    public static Specification<DepartmentManagerEntity> activeEquals(Boolean isActive) {
        return (root, query, criteriaBuilder) -> (isActive == null)
                ? null
                : criteriaBuilder.equal(root.get("isActive"), isActive);
    }

    public static Specification<DepartmentManagerEntity> startDateGreaterThanOrEqualTo(LocalDate startDate) {
        return (root, query, criteriaBuilder) -> (startDate == null)
                ? null
                : criteriaBuilder.greaterThanOrEqualTo(root.get("startDate"), startDate);
    }

    public static Specification<DepartmentManagerEntity> startDateLessThanOrEqualTo(LocalDate endDate) {
        return (root, query, criteriaBuilder) -> (endDate == null)
                ? null
                : criteriaBuilder.lessThanOrEqualTo(root.get("startDate"), endDate);
    }

    public static Specification<DepartmentManagerEntity> endDateGreaterThanOrEqualTo(LocalDate startDate) {
        return (root, query, criteriaBuilder) -> (startDate == null)
                ? null
                : criteriaBuilder.greaterThanOrEqualTo(root.get("endDate"), startDate);
    }

    public static Specification<DepartmentManagerEntity> endDateLessThanOrEqualTo(LocalDate endDate) {
        return (root, query, criteriaBuilder) -> (endDate == null)
                ? null
                : criteriaBuilder.lessThanOrEqualTo(root.get("endDate"), endDate);
    }
}

