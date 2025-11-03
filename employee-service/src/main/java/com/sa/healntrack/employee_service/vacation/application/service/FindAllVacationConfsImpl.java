package com.sa.healntrack.employee_service.vacation.application.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sa.healntrack.employee_service.vacation.application.port.in.FindAllVacationConfs;
import com.sa.healntrack.employee_service.vacation.application.port.out.FindVacationConfs;
import com.sa.healntrack.employee_service.vacation.domain.VacationConf;

@Service
@Transactional(readOnly = true)
public class FindAllVacationConfsImpl implements FindAllVacationConfs {
    private final FindVacationConfs findVacationConfs;

    public FindAllVacationConfsImpl(FindVacationConfs findVacationConfs) {
        this.findVacationConfs = findVacationConfs;
    }

    @Override
    public List<VacationConf> findAll() {
        return findVacationConfs.findAll();
    }
    
}
