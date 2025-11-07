package com.sa.healntrack.employee_service.employment.infrastructure.adapter.out.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.sa.healntrack.employee_service.department.domain.Department;
import com.sa.healntrack.employee_service.department.infrastructure.adapter.out.persistence.DepartmentEntityMapper;
import com.sa.healntrack.employee_service.employment.application.port.in.find_department_managers.FindAllDepartmentManagersQuery;
import com.sa.healntrack.employee_service.employment.application.port.out.FindDepartmentManagers;
import com.sa.healntrack.employee_service.employment.application.port.out.StoreDepartmentManager;
import com.sa.healntrack.employee_service.employment.domain.DepartmentManager;
import com.sa.healntrack.employee_service.employment.domain.Employee;
import com.sa.healntrack.employee_service.employment.infrastructure.adapter.out.persistence.entity.DepartmentManagerEntity;
import com.sa.healntrack.employee_service.employment.infrastructure.adapter.out.persistence.mapper.DepartmentManagerEntityMapper;
import com.sa.healntrack.employee_service.employment.infrastructure.adapter.out.persistence.mapper.EmployeeEntityMapper;
import com.sa.healntrack.employee_service.employment.infrastructure.adapter.out.persistence.spec.DepartmentManagerSpecs;

@Repository
public class DepartmentManagerRepository implements FindDepartmentManagers, StoreDepartmentManager {
    private final DepartmentManagerJpaRepository jpaRepository;

    public DepartmentManagerRepository(DepartmentManagerJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public DepartmentManager save(DepartmentManager departmentManager) {
        DepartmentManagerEntity entity = DepartmentManagerEntityMapper.toEntity(departmentManager);
        DepartmentManagerEntity saved = jpaRepository.save(entity);
        return DepartmentManagerEntityMapper.toDomain(saved);
    }

    @Override
    public List<DepartmentManager> findAllDepartmentManagers(FindAllDepartmentManagersQuery query) {
        Specification<DepartmentManagerEntity> spec = Specification.allOf(
            Specification.anyOf(
                    DepartmentManagerSpecs.employeeFullnameContains(query.employee()),
                    DepartmentManagerSpecs.employeeCuiEquals(query.employee())
            ),
            Specification.anyOf(
                    DepartmentManagerSpecs.departmentNameContains(query.department()),
                    DepartmentManagerSpecs.departmentCodeEquals(query.department())
            ),
            DepartmentManagerSpecs.startDateGreaterThanOrEqualTo(query.startDateFrom()),
            DepartmentManagerSpecs.startDateLessThanOrEqualTo(query.startDateTo()),
            DepartmentManagerSpecs.endDateGreaterThanOrEqualTo(query.endDateFrom()),
            DepartmentManagerSpecs.endDateLessThanOrEqualTo(query.endDateTo()),
            DepartmentManagerSpecs.activeEquals(query.isActive())
        );

        return jpaRepository.findAll(spec)
                .stream()
                .map(DepartmentManagerEntityMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<DepartmentManager> findDepartmentManagerByEmployeeAndIsActive(Employee employee, boolean isActive) {
        return jpaRepository.findByEmployeeAndIsActive(EmployeeEntityMapper.toEntity(employee), isActive)
                .map(DepartmentManagerEntityMapper::toDomain);
    }

    @Override
    public boolean existsByDepartmentAndIsActive(Department department, boolean isActive) {
        return jpaRepository.existsByDepartmentAndIsActive(
                DepartmentEntityMapper.toEntity(department),
                isActive);
    }

    @Override
    public Optional<DepartmentManager> findByCuiAndIsActive(String cui, boolean isActive) {
        Specification<DepartmentManagerEntity> spec = Specification.allOf(
                DepartmentManagerSpecs.employeeCuiEquals(cui),
                DepartmentManagerSpecs.activeEquals(isActive)
        );
        return jpaRepository.findOne(spec)
                .map(DepartmentManagerEntityMapper::toDomain);
    }

    @Override
    public Optional<DepartmentManager> findManagerByDepartmentAndIsActive(String departmentCode, boolean isActive) {
        Specification<DepartmentManagerEntity> spec = Specification.allOf(
                DepartmentManagerSpecs.departmentCodeEquals(departmentCode),
                DepartmentManagerSpecs.activeEquals(isActive)
        );
        return jpaRepository.findOne(spec)
                .map(DepartmentManagerEntityMapper::toDomain);
    }


}
