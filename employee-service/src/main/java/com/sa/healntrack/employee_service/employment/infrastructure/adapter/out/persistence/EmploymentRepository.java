package com.sa.healntrack.employee_service.employment.infrastructure.adapter.out.persistence;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.sa.healntrack.employee_service.employment.application.port.in.find_employments.FindAllEmploymentsQuery;
import com.sa.healntrack.employee_service.employment.application.port.out.FindEmployments;
import com.sa.healntrack.employee_service.employment.application.port.out.StoreEmployment;
import com.sa.healntrack.employee_service.employment.domain.Employee;
import com.sa.healntrack.employee_service.employment.domain.Employment;
import com.sa.healntrack.employee_service.employment.infrastructure.adapter.out.persistence.entity.EmploymentEntity;
import com.sa.healntrack.employee_service.employment.infrastructure.adapter.out.persistence.mapper.EmployeeEntityMapper;
import com.sa.healntrack.employee_service.employment.infrastructure.adapter.out.persistence.mapper.EmploymentEntityMapper;
import com.sa.healntrack.employee_service.employment.infrastructure.adapter.out.persistence.spec.EmploymentSpec;

@Repository
public class EmploymentRepository implements FindEmployments, StoreEmployment {

    private final EmploymentJpaRepository jpaRepository;

    public EmploymentRepository(EmploymentJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Employment save(Employment employmentPeriod) {
        EmploymentEntity entity = EmploymentEntityMapper.toEntity(employmentPeriod);
        EmploymentEntity saved = jpaRepository.save(entity);
        return EmploymentEntityMapper.toDomain(saved);
    }

    @Override
    public List<Employment> findAllEmployments(FindAllEmploymentsQuery query) {
        Specification<EmploymentEntity> spec = Specification.allOf(
            Specification.anyOf(
                EmploymentSpec.employeeFullnameContains(query.employee()),
                EmploymentSpec.employeeCuiEquals(query.employee()),
                EmploymentSpec.employeeNitEquals(query.employee())
            ),
            Specification.anyOf(
                EmploymentSpec.departmentNameContains(query.department()),
                EmploymentSpec.departmentCodeEquals(query.department())
            ),
            EmploymentSpec.startDateGreaterThanOrEqualTo(query.startDateFrom()),
            EmploymentSpec.startDateLessThanOrEqualTo(query.startDateTo()),

            EmploymentSpec.endDateGreaterThanOrEqualTo(query.endDateFrom()),
            EmploymentSpec.endDateLessThanOrEqualTo(query.endDateTo()),

            EmploymentSpec.periodTypeEquals(query.type())
        );
        return jpaRepository.findAll(spec)
                .stream()
                .map(EmploymentEntityMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Employment> findByEmployeeAndEndDate(Employee employee, LocalDate endDate) {
        return jpaRepository.findByEmployeeAndEndDate(EmployeeEntityMapper.toEntity(employee), endDate)
                .map(EmploymentEntityMapper::toDomain);
    }

}
