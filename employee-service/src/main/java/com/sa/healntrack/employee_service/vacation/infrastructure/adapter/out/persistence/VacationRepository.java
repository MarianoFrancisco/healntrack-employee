package com.sa.healntrack.employee_service.vacation.infrastructure.adapter.out.persistence;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.sa.healntrack.employee_service.employment.domain.Employee;
import com.sa.healntrack.employee_service.employment.infrastructure.adapter.out.persistence.mapper.EmployeeEntityMapper;
import com.sa.healntrack.employee_service.vacation.application.port.in.command.FindAllVacationsQuery;
import com.sa.healntrack.employee_service.vacation.application.port.out.FindVacations;
import com.sa.healntrack.employee_service.vacation.application.port.out.StoreVacation;
import com.sa.healntrack.employee_service.vacation.domain.Vacation;
import com.sa.healntrack.employee_service.vacation.domain.VacationStatus;

@Repository
public class VacationRepository implements FindVacations, StoreVacation {

    private final VacationJpaRepository jpaRepository;

    public VacationRepository(VacationJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Vacation save(Vacation vacation) {
        VacationEntity entity = VacationEntityMapper.toEntity(vacation);
        VacationEntity saved = jpaRepository.save(entity);
        return VacationEntityMapper.toDomain(saved);
    }

    @Override
    public List<Vacation> findAll(FindAllVacationsQuery query) {
        Specification<VacationEntity> spec = Specification.allOf(
            Specification.anyOf(
                VacationSpecs.employeeCuiEquals(query.employee()),
                VacationSpecs.employeeFullnameContains(query.employee())
            ),
            Specification.anyOf(
                VacationSpecs.departmentCodeEquals(query.department()),
                VacationSpecs.departmentNameContains(query.department())
            ),
                VacationSpecs.startDateGreaterThanOrEqualTo(query.startDate()),
                VacationSpecs.endDateLessThanOrEqualTo(query.endDate()),
                VacationSpecs.requestedAtGreaterThanOrEqualTo(query.requestedAtFrom()),
                VacationSpecs.requestedAtLessThanOrEqualTo(query.requestedAtTo()),
                VacationSpecs.statusEquals(query.status())
        );

        return jpaRepository.findAll(spec)
                .stream()
                .map(VacationEntityMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Vacation> findById(UUID id) {
        return jpaRepository.findById(id)
                .map(VacationEntityMapper::toDomain);
    }

    @Override
    public boolean existsByEmployeeAndStatus(Employee employee, VacationStatus status) {
        return jpaRepository.existsByEmployeeAndStatus(
                EmployeeEntityMapper.toEntity(employee),
                status
        );
    }
}

