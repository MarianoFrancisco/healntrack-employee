package com.sa.healntrack.employee_service.vacation.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sa.healntrack.employee_service.common.application.exception.EntityNotFoundException;
import com.sa.healntrack.employee_service.vacation.application.port.in.FindVacationById;
import com.sa.healntrack.employee_service.vacation.application.port.out.FindVacations;
import com.sa.healntrack.employee_service.vacation.domain.Vacation;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FindVacationByIdImpl implements FindVacationById {
    private final FindVacations findVacations;
    @Override
    public Vacation findVacationById(UUID id) {
        return findVacations.findById(id).orElseThrow(
            () -> new EntityNotFoundException("Solicitud de vacaciones no encontrada con id: " + id)
        );
    }
    
}
