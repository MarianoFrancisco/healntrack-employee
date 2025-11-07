package com.sa.healntrack.employee_service.employment.application.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sa.healntrack.employee_service.employment.application.port.in.find_department_managers.FindAllDepartmentManagers;
import com.sa.healntrack.employee_service.employment.application.port.in.find_department_managers.FindAllDepartmentManagersQuery;
import com.sa.healntrack.employee_service.employment.application.port.out.FindDepartmentManagers;
import com.sa.healntrack.employee_service.employment.domain.DepartmentManager;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FindAllDepartmentManagersImpl implements FindAllDepartmentManagers{
    private final FindDepartmentManagers findAllDepartmentManagers;

    @Override
    public List<DepartmentManager> findAllDepartmentManagers(FindAllDepartmentManagersQuery query) {
        return findAllDepartmentManagers.findAllDepartmentManagers(query);
    }
    
}
