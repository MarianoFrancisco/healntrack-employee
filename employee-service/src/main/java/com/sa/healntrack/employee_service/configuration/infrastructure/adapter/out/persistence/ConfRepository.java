package com.sa.healntrack.employee_service.configuration.infrastructure.adapter.out.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.sa.healntrack.employee_service.configuration.application.port.out.FindConfs;
import com.sa.healntrack.employee_service.configuration.application.port.out.StoreConf;
import com.sa.healntrack.employee_service.configuration.domain.Configuration;

@Repository
public class ConfRepository implements StoreConf, FindConfs {
    private final ConfJpaRepository vacationJpaRepository;

    public ConfRepository(ConfJpaRepository vacationJpaRepository) {
        this.vacationJpaRepository = vacationJpaRepository;
    }

    @Override
    public Optional<Configuration> findByKey(String key) {
        return vacationJpaRepository.findById(key)
                .map(entity -> new Configuration(entity.getKey(), entity.getValue()));
    }

    @Override
    public List<Configuration> findAll() {
        return vacationJpaRepository.findAll().stream()
                .map(entity -> new Configuration(entity.getKey(), entity.getValue()))
                .toList();
    }

    @Override
    public Configuration save(Configuration vacationConf) {
        ConfEntity entity = ConfEntity.builder()
                .key(vacationConf.getKey())
                .value(vacationConf.getValue())
                .build();
        ConfEntity savedEntity = vacationJpaRepository.save(entity);
        return new Configuration(savedEntity.getKey(), savedEntity.getValue());
    }
    
}
