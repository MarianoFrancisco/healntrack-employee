package com.sa.healntrack.employee_service.vacation.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sa.healntrack.employee_service.common.application.exception.ConfNotFoundException;
import com.sa.healntrack.employee_service.vacation.application.port.in.UpdateVacationConf;
import com.sa.healntrack.employee_service.vacation.application.port.out.FindVacationConfs;
import com.sa.healntrack.employee_service.vacation.application.port.out.StoreVacationConf;
import com.sa.healntrack.employee_service.vacation.domain.VacationConf;

@Service
@Transactional(rollbackFor = Exception.class)
public class UpdateVacationConfImpl implements UpdateVacationConf{
    private final FindVacationConfs findVacationConfs;
    private final StoreVacationConf storeVacationConf;

    public UpdateVacationConfImpl(FindVacationConfs findVacationConfs, StoreVacationConf storeVacationConf) {
        this.findVacationConfs = findVacationConfs;
        this.storeVacationConf = storeVacationConf;
    }

    @Override
    public VacationConf updateVacationConf(String key, Integer value) {
        VacationConf vacationConf = findVacationConfs.findByKey(key)
            .orElseThrow(() -> new ConfNotFoundException(key));
        
        vacationConf.setValue(value);
        return storeVacationConf.save(vacationConf);
    }
    
}
