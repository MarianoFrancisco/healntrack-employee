package com.sa.healntrack.employee_service.employment_period.application.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sa.healntrack.employee_service.employment_period.application.port.in.find_employment_periods.FindAllEmploymentPeriods;
import com.sa.healntrack.employee_service.employment_period.application.port.in.find_employment_periods.FindAllEmploymentPeriodsQuery;
import com.sa.healntrack.employee_service.employment_period.application.port.out.FindEmploymentPeriods;
import com.sa.healntrack.employee_service.employment_period.domain.EmploymentPeriod;

@Service
@Transactional(readOnly = true)
public class FindAllEmploymentPeriodsImpl implements FindAllEmploymentPeriods {
    private final FindEmploymentPeriods findEmploymentPeriods;

    public FindAllEmploymentPeriodsImpl(FindEmploymentPeriods findEmploymentPeriods) {
        this.findEmploymentPeriods = findEmploymentPeriods;
    }

    @Override
    public List<EmploymentPeriod> findAllEmploymentPeriods(FindAllEmploymentPeriodsQuery query) {
        return findEmploymentPeriods.findAllEmploymentPeriods(query);
    }

}
