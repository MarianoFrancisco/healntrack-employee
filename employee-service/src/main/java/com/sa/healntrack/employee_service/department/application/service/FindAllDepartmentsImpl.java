package com.sa.healntrack.employee_service.department.application.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sa.healntrack.employee_service.department.application.port.in.find_all_departments.FindAllDepartments;
import com.sa.healntrack.employee_service.department.application.port.in.find_all_departments.FindAllDepartmentsQuery;
import com.sa.healntrack.employee_service.department.application.port.out.persistence.FindDepartments;
import com.sa.healntrack.employee_service.department.domain.Department;

@Service
@Transactional(readOnly = true)
public class FindAllDepartmentsImpl implements FindAllDepartments{
    private final FindDepartments findDepartments;

    public FindAllDepartmentsImpl(FindDepartments findDepartments) {
        this.findDepartments = findDepartments;
    }

    @Override
    public List<Department> findAllDepartments(FindAllDepartmentsQuery query) {
        return findDepartments.findAllDepartments(query);
    }
    
}
