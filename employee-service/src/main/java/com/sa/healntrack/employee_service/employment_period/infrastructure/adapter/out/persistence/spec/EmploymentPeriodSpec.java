package com.sa.healntrack.employee_service.employment_period.infrastructure.adapter.out.persistence.spec;


import java.time.LocalDate;
import org.springframework.data.jpa.domain.Specification;

import com.sa.healntrack.employee_service.employment_period.domain.PeriodType;
import com.sa.healntrack.employee_service.employment_period.infrastructure.adapter.out.persistence.entity.EmploymentPeriodEntity;

public class EmploymentPeriodSpec {

    // Filtros por Employee (OR entre campos)
    public static Specification<EmploymentPeriodEntity> employeeFullnameContains(String fullname) {
        return (root, query, criteriaBuilder) -> (fullname == null || fullname.isBlank())
                ? null
                : criteriaBuilder.like(
                        criteriaBuilder.lower(root.join("employee").get("fullname")),
                        "%" + fullname.toLowerCase() + "%");
    }

    public static Specification<EmploymentPeriodEntity> employeeCuiEquals(String cui) {
        return (root, query, criteriaBuilder) -> (cui == null || cui.isBlank())
                ? null
                : criteriaBuilder.equal(root.join("employee").get("cui"), cui);
    }

    public static Specification<EmploymentPeriodEntity> employeeNitEquals(String nit) {
        return (root, query, criteriaBuilder) -> (nit == null || nit.isBlank())
                ? null
                : criteriaBuilder.equal(root.join("employee").get("nit"), nit);
    }

    // Filtros por Department (OR entre campos)
    public static Specification<EmploymentPeriodEntity> departmentNameContains(String departmentName) {
        return (root, query, criteriaBuilder) -> (departmentName == null || departmentName.isBlank())
                ? null
                : criteriaBuilder.like(
                        criteriaBuilder.lower(root.join("department").get("name")),
                        "%" + departmentName.toLowerCase() + "%");
    }

    public static Specification<EmploymentPeriodEntity> departmentCodeEquals(String departmentCode) {
        return (root, query, criteriaBuilder) -> (departmentCode == null || departmentCode.isBlank())
                ? null
                : criteriaBuilder.equal(root.join("department").get("code"), departmentCode);
    }

    // Filtros por fechas
    public static Specification<EmploymentPeriodEntity> startDateGreaterThanOrEqualTo(LocalDate startDate) {
        return (root, query, criteriaBuilder) -> (startDate == null)
                ? null
                : criteriaBuilder.greaterThanOrEqualTo(root.get("startDate"), startDate);
    }

    public static Specification<EmploymentPeriodEntity> startDateLessThanOrEqualTo(LocalDate endDate) {
        return (root, query, criteriaBuilder) -> (endDate == null)
                ? null
                : criteriaBuilder.lessThanOrEqualTo(root.get("startDate"), endDate);
    }

    public static Specification<EmploymentPeriodEntity> endDateGreaterThanOrEqualTo(LocalDate startDate) {
        return (root, query, criteriaBuilder) -> (startDate == null)
                ? null
                : criteriaBuilder.greaterThanOrEqualTo(root.get("endDate"), startDate);
    }

    public static Specification<EmploymentPeriodEntity> endDateLessThanOrEqualTo(LocalDate endDate) {
        return (root, query, criteriaBuilder) -> (endDate == null)
                ? null
                : criteriaBuilder.lessThanOrEqualTo(root.get("endDate"), endDate);
    }

    // Filtro por tipo de período
    public static Specification<EmploymentPeriodEntity> periodTypeEquals(PeriodType periodType) {
        return (root, query, criteriaBuilder) -> (periodType == null)
                ? null
                : criteriaBuilder.equal(root.get("type"), periodType);
    }
}
