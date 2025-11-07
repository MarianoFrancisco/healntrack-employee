package com.sa.healntrack.employee_service.configuration.application.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sa.healntrack.employee_service.configuration.application.port.in.FindAllConfs;
import com.sa.healntrack.employee_service.configuration.application.port.out.FindConfs;
import com.sa.healntrack.employee_service.configuration.domain.Configuration;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FindAllConfsImpl implements FindAllConfs {
    private final FindConfs findConfs;

    @Override
    public List<Configuration> findAll() {
        return findConfs.findAll();
    }
    
}
