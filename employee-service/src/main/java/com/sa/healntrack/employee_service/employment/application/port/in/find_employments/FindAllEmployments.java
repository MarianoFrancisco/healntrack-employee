package com.sa.healntrack.employee_service.employment.application.port.in.find_employments;

import java.util.List;

import com.sa.healntrack.employee_service.employment.domain.Employment;

public interface FindAllEmployments {
    List<Employment> findAllEmployments(FindAllEmploymentsQuery query);
}
