package com.sa.healntrack.employee_service.department.infrastructure.adapter.out.persistence;

import org.springframework.data.jpa.domain.Specification;

public class DepartmentSpecs {

    public static Specification<DepartmentEntity> nameContains(String name) {
        return (root, query, criteriaBuilder) -> (name == null || name.isBlank())
                ? null
                : criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("name")),
                        "%" + name.toLowerCase() + "%");
    }

    public static Specification<DepartmentEntity> codeEquals(String code) {
        return (root, query, criteriaBuilder) -> (code == null || code.isBlank())
                ? null
                : criteriaBuilder.equal(root.get("code"), code);
    }

    public static Specification<DepartmentEntity> activeEquals(Boolean active) {
        return (root, query, criteriaBuilder) -> (active == null)
                ? null
                : criteriaBuilder.equal(root.get("isActive"), active);
    }
}

