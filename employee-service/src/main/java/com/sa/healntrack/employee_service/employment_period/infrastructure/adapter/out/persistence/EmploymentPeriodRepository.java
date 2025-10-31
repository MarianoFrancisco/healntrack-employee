package com.sa.healntrack.employee_service.employment_period.infrastructure.adapter.out.persistence;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.sa.healntrack.employee_service.employment_period.application.port.in.find_employment_periods.FindAllEmploymentPeriodsQuery;
import com.sa.healntrack.employee_service.employment_period.application.port.out.FindEmploymentPeriods;
import com.sa.healntrack.employee_service.employment_period.application.port.out.StoreEmploymentPeriod;
import com.sa.healntrack.employee_service.employment_period.domain.Employee;
import com.sa.healntrack.employee_service.employment_period.domain.EmploymentPeriod;
import com.sa.healntrack.employee_service.employment_period.infrastructure.adapter.out.persistence.entity.EmploymentPeriodEntity;
import com.sa.healntrack.employee_service.employment_period.infrastructure.adapter.out.persistence.mapper.EmployeeEntityMapper;
import com.sa.healntrack.employee_service.employment_period.infrastructure.adapter.out.persistence.mapper.EmploymentPeriodEntityMapper;

public class EmploymentPeriodRepository implements FindEmploymentPeriods, StoreEmploymentPeriod {
    private final EmploymentPeriodJpaRepository jpaRepository;

    public EmploymentPeriodRepository(EmploymentPeriodJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public EmploymentPeriod save(EmploymentPeriod employmentPeriod) {
        EmploymentPeriodEntity entity = EmploymentPeriodEntityMapper.toEntity(employmentPeriod);
        EmploymentPeriodEntity saved = jpaRepository.save(entity);
        return EmploymentPeriodEntityMapper.toDomain(saved);
    }

    @Override
    public List<EmploymentPeriod> findAllEmploymentPeriods(FindAllEmploymentPeriodsQuery query) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllEmploymentPeriods'");
    }

    @Override
    public Optional<EmploymentPeriod> findByEmployeeAndEndDate(Employee employee, LocalDate endDate) {
        return jpaRepository.findByEmployeeAndEndDate(EmployeeEntityMapper.toEntity(employee), endDate)
                .map(EmploymentPeriodEntityMapper::toDomain);
    }
    
}
