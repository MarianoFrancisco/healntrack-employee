package com.sa.healntrack.employee_service.vacation.application.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sa.healntrack.employee_service.vacation.application.port.in.FindAllVacations;
import com.sa.healntrack.employee_service.vacation.application.port.in.command.FindAllVacationsQuery;
import com.sa.healntrack.employee_service.vacation.application.port.out.FindVacations;
import com.sa.healntrack.employee_service.vacation.domain.Vacation;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FindAllVacationsImpl implements FindAllVacations {
    private final FindVacations findVacations;

    @Override
    public List<Vacation> findAllVacations(FindAllVacationsQuery query) {
        return findVacations.findAll(query);
    }
    
}
