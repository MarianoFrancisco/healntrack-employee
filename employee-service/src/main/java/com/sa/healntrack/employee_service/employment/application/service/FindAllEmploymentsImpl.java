package com.sa.healntrack.employee_service.employment.application.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sa.healntrack.employee_service.employment.application.port.in.find_employments.FindAllEmployments;
import com.sa.healntrack.employee_service.employment.application.port.in.find_employments.FindAllEmploymentsQuery;
import com.sa.healntrack.employee_service.employment.application.port.out.FindEmployments;
import com.sa.healntrack.employee_service.employment.domain.Employment;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FindAllEmploymentsImpl implements FindAllEmployments {
    private final FindEmployments findEmployments;

    @Override
    public List<Employment> findAllEmployments(FindAllEmploymentsQuery query) {
        return findEmployments.findAllEmployments(query);
    }
}
