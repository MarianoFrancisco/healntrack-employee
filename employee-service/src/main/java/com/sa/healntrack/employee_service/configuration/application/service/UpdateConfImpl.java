package com.sa.healntrack.employee_service.configuration.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sa.healntrack.employee_service.configuration.application.exception.ConfNotFoundException;
import com.sa.healntrack.employee_service.configuration.application.port.in.UpdateConf;
import com.sa.healntrack.employee_service.configuration.application.port.out.FindConfs;
import com.sa.healntrack.employee_service.configuration.application.port.out.StoreConf;
import com.sa.healntrack.employee_service.configuration.domain.Configuration;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class UpdateConfImpl implements UpdateConf{
    private final FindConfs findConfs;
    private final StoreConf storeConf;

    @Override
    public Configuration updateConf(String key, Integer value) {
        Configuration vacationConf = findConfs.findByKey(key)
            .orElseThrow(() -> new ConfNotFoundException(key));
        
        vacationConf.setValue(value);
        return storeConf.save(vacationConf);
    }
    
}
