package com.sa.healntrack.employee_service.employment_period.application.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sa.healntrack.employee_service.employment_period.application.port.in.find_department_managers.FindAllDepartmentManagers;
import com.sa.healntrack.employee_service.employment_period.application.port.in.find_department_managers.FindAllDepartmentManagersQuery;
import com.sa.healntrack.employee_service.employment_period.application.port.out.FindDepartmentManagers;
import com.sa.healntrack.employee_service.employment_period.domain.DepartmentManager;

@Service
@Transactional(readOnly = true)
public class FindAllDepartmentManagersImpl implements FindAllDepartmentManagers{
    private final FindDepartmentManagers findAllDepartmentManagers;

    public FindAllDepartmentManagersImpl(FindDepartmentManagers findAllDepartmentManagers) {
        this.findAllDepartmentManagers = findAllDepartmentManagers;
    }

    @Override
    public List<DepartmentManager> findAllDepartmentManagers(FindAllDepartmentManagersQuery query) {
        return findAllDepartmentManagers.findAllDepartmentManagers(query);
    }
    
}
