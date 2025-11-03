package com.sa.healntrack.employee_service.employment.infrastructure.adapter.out.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.sa.healntrack.employee_service.employment.application.port.in.find_employees.FindAllEmployeesQuery;
import com.sa.healntrack.employee_service.employment.application.port.out.FindEmployees;
import com.sa.healntrack.employee_service.employment.application.port.out.StoreEmployee;
import com.sa.healntrack.employee_service.employment.domain.Employee;
import com.sa.healntrack.employee_service.employment.infrastructure.adapter.out.persistence.entity.EmployeeEntity;
import com.sa.healntrack.employee_service.employment.infrastructure.adapter.out.persistence.mapper.EmployeeEntityMapper;
import com.sa.healntrack.employee_service.employment.infrastructure.adapter.out.persistence.spec.EmployeeSpecs;

@Repository
public class EmployeeRepository implements FindEmployees, StoreEmployee {

    private final EmployeeJpaRepository jpaRepository;

    public EmployeeRepository(EmployeeJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Employee save(Employee employee) {
        EmployeeEntity entity = EmployeeEntityMapper.toEntity(employee);
        EmployeeEntity saved = jpaRepository.save(entity);
        return EmployeeEntityMapper.toDomain(saved);
    }

    @Override
    public List<Employee> findAllEmployees(FindAllEmployeesQuery query) {
        Specification<EmployeeEntity> spec = Specification.allOf(
            Specification.anyOf(
                EmployeeSpecs.fullnameContains(query.searchTerm()),
                EmployeeSpecs.cuiEquals(query.searchTerm()),
                EmployeeSpecs.emailEquals(query.searchTerm()),
                EmployeeSpecs.phoneNumberEquals(query.searchTerm()),
                EmployeeSpecs.nitEquals(query.searchTerm())
            ),
            Specification.anyOf(
                EmployeeSpecs.departmentNameContains(query.department()),
                EmployeeSpecs.departmentCodeEquals(query.department())
            ),
            EmployeeSpecs.activeEquals(query.isActive())
        );

        return jpaRepository.findAll(spec)
                .stream()
                .map(EmployeeEntityMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Employee> findEmployeeByCui(String cui) {
        return jpaRepository.findByCui(cui)
                .map(EmployeeEntityMapper::toDomain);
    }

    @Override
    public boolean existByCui(String cui) {
        return jpaRepository.existsByCui(cui);
    }

    @Override
    public boolean existByNit(String nit) {
        return jpaRepository.existsByNit(nit);
    }

    @Override
    public boolean existByEmail(String email) {
        return jpaRepository.existsByEmail(email);
    }

    @Override
    public Optional<Employee> findEmployeeByEmail(String email) {
        return jpaRepository.findByEmail(email)
                .map(EmployeeEntityMapper::toDomain);
    }
    
}
