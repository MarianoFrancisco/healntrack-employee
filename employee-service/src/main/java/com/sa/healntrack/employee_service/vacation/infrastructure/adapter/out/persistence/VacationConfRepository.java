package com.sa.healntrack.employee_service.vacation.infrastructure.adapter.out.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.sa.healntrack.employee_service.vacation.application.port.out.FindVacationConfs;
import com.sa.healntrack.employee_service.vacation.application.port.out.StoreVacationConf;
import com.sa.healntrack.employee_service.vacation.domain.VacationConf;

@Repository
public class VacationConfRepository implements StoreVacationConf, FindVacationConfs {
    private final VacationJpaRepository vacationJpaRepository;

    public VacationConfRepository(VacationJpaRepository vacationJpaRepository) {
        this.vacationJpaRepository = vacationJpaRepository;
    }

    @Override
    public Optional<VacationConf> findByKey(String key) {
        return vacationJpaRepository.findById(key)
                .map(entity -> new VacationConf(entity.getKey(), entity.getValue()));
    }

    @Override
    public List<VacationConf> findAll() {
        return vacationJpaRepository.findAll().stream()
                .map(entity -> new VacationConf(entity.getKey(), entity.getValue()))
                .toList();
    }

    @Override
    public VacationConf save(VacationConf vacationConf) {
        VacationConfEntity entity = VacationConfEntity.builder()
                .key(vacationConf.getKey())
                .value(vacationConf.getValue())
                .build();
        VacationConfEntity savedEntity = vacationJpaRepository.save(entity);
        return new VacationConf(savedEntity.getKey(), savedEntity.getValue());
    }
    
}
