package com.sa.healntrack.employee_service.department.infrastructure.adapter.out.persistence;

import com.sa.healntrack.employee_service.department.application.port.out.persistence.FindDepartments;
import com.sa.healntrack.employee_service.department.application.port.out.persistence.StoreDepartment;
import com.sa.healntrack.employee_service.department.application.port.in.find_all_departments.FindAllDepartmentsQuery;
import com.sa.healntrack.employee_service.department.domain.Department;

import lombok.RequiredArgsConstructor;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class DepartmentRepository implements FindDepartments, StoreDepartment {
    private final DepartmentJpaRepository repository;

    @Override
    public Department save(Department department) {
        DepartmentEntity entity = DepartmentEntityMapper.toEntity(department);
        DepartmentEntity saved = repository.save(entity);
        return DepartmentEntityMapper.toDomain(saved);
    }

    @Override
    public List<Department> findAllDepartments(FindAllDepartmentsQuery query) {
        Specification<DepartmentEntity> spec = Specification
                .anyOf(
                        DepartmentSpecs.nameContains(query.searchTerm()),
                        DepartmentSpecs.codeEquals(query.searchTerm()))
                .and(
                        DepartmentSpecs.activeEquals(query.isActive()) 
                );

        return repository.findAll(spec)
                .stream()
                .map(DepartmentEntityMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Department> findDepartmentByCode(String code) {
        return repository.findById(code)
                .map(DepartmentEntityMapper::toDomain);
    }

    @Override
    public boolean existsByCodeAndIsActive(String code, boolean isActive) {
        return repository.existsByCodeAndIsActive(code, isActive);
    }
}
