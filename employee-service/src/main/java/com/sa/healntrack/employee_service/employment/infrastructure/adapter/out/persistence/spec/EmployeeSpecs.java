package com.sa.healntrack.employee_service.employment.infrastructure.adapter.out.persistence.spec;

import java.math.BigDecimal;
import org.springframework.data.jpa.domain.Specification;

import com.sa.healntrack.employee_service.employment.infrastructure.adapter.out.persistence.entity.EmployeeEntity;

public class EmployeeSpecs {

    public static Specification<EmployeeEntity> fullnameContains(String fullname) {
        return (root, query, criteriaBuilder) -> (fullname == null || fullname.isBlank())
                ? null
                : criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("fullname")),
                        "%" + fullname.toLowerCase() + "%");
    }

    public static Specification<EmployeeEntity> cuiEquals(String cui) {
        return (root, query, criteriaBuilder) -> (cui == null || cui.isBlank())
                ? null
                : criteriaBuilder.equal(root.get("cui"), cui);
    }

    public static Specification<EmployeeEntity> nitEquals(String nit) {
        return (root, query, criteriaBuilder) -> (nit == null || nit.isBlank())
                ? null
                : criteriaBuilder.equal(root.get("nit"), nit);
    }

    public static Specification<EmployeeEntity> phoneNumberEquals(String phoneNumber) {
        return (root, query, criteriaBuilder) -> (phoneNumber == null || phoneNumber.isBlank())
                ? null
                : criteriaBuilder.equal(root.get("phoneNumber"), phoneNumber);
    }

    public static Specification<EmployeeEntity> emailEquals(String email) {
        return (root, query, criteriaBuilder) -> (email == null || email.isBlank())
                ? null
                : criteriaBuilder.equal(root.get("email"), email);
    }

    public static Specification<EmployeeEntity> departmentCodeEquals(String departmentCode) {
        return (root, query, criteriaBuilder) -> (departmentCode == null || departmentCode.isBlank())
                ? null
                : criteriaBuilder.equal(root.join("department").get("code"), departmentCode);
    }

    public static Specification<EmployeeEntity> departmentNameContains(String departmentName) {
        return (root, query, criteriaBuilder) -> (departmentName == null || departmentName.isBlank())
                ? null
                : criteriaBuilder.like(
                        criteriaBuilder.lower(root.join("department").get("name")),
                        "%" + departmentName.toLowerCase() + "%");
    }

    public static Specification<EmployeeEntity> activeEquals(Boolean isActive) {
        return (root, query, criteriaBuilder) -> (isActive == null)
                ? null
                : criteriaBuilder.equal(root.get("isActive"), isActive);
    }

    public static Specification<EmployeeEntity> salaryGreaterThanOrEqualTo(BigDecimal minSalary) {
        return (root, query, criteriaBuilder) -> (minSalary == null)
                ? null
                : criteriaBuilder.greaterThanOrEqualTo(root.get("salary"), minSalary);
    }

    public static Specification<EmployeeEntity> salaryLessThanOrEqualTo(BigDecimal maxSalary) {
        return (root, query, criteriaBuilder) -> (maxSalary == null)
                ? null
                : criteriaBuilder.lessThanOrEqualTo(root.get("salary"), maxSalary);
    }
}

