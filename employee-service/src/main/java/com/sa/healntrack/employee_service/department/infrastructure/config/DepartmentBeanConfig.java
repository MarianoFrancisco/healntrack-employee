package com.sa.healntrack.employee_service.department.infrastructure.config;

import com.sa.healntrack.employee_service.department.application.port.in.create_department.CreateDepartment;
import com.sa.healntrack.employee_service.department.application.port.in.deactivate_department.DeactivateDepartment;
import com.sa.healntrack.employee_service.department.application.port.in.find_all_departments.FindAllDepartments;
import com.sa.healntrack.employee_service.department.application.port.in.find_department_by_code.FindDepartmentByCode;
import com.sa.healntrack.employee_service.department.application.port.in.update_department.UpdateDepartment;
import com.sa.healntrack.employee_service.department.application.port.out.persistence.FindDepartments;
import com.sa.healntrack.employee_service.department.application.port.out.persistence.StoreDepartment;
import com.sa.healntrack.employee_service.department.infrastructure.adapter.out.persistence.DepartmentPersistenceAdapter;
import com.sa.healntrack.employee_service.department.infrastructure.adapter.out.persistence.DepartmentJpaRepository;
import com.sa.healntrack.employee_service.department.application.service.CreateDepartmentImpl;
import com.sa.healntrack.employee_service.department.application.service.DeactivateDepartmentImpl;
import com.sa.healntrack.employee_service.department.application.service.FindAllDepartmentsImpl;
import com.sa.healntrack.employee_service.department.application.service.FindDepartmentByCodeImpl;
import com.sa.healntrack.employee_service.department.application.service.UpdateDepartmentImpl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DepartmentBeanConfig {

    // Adapter de persistencia
    @Bean
    public DepartmentPersistenceAdapter departmentPersistenceAdapter(DepartmentJpaRepository repository) {
        return new DepartmentPersistenceAdapter(repository);
    }

    // Beans de servicios de aplicación (Input Ports)
    @Bean
    public CreateDepartment creatingDepartment(StoreDepartment storeDepartment,
                                               FindDepartments findDepartments) {
        return new CreateDepartmentImpl(storeDepartment, findDepartments);
    }

    @Bean
    public UpdateDepartment updatingDepartment(StoreDepartment storeDepartment,
                                               FindDepartments findDepartments) {
        return new UpdateDepartmentImpl(storeDepartment, findDepartments);
    }

    @Bean
    public DeactivateDepartment deactivatingDepartment(FindDepartments findDepartments,
                                                       StoreDepartment storeDepartment) {
        return new DeactivateDepartmentImpl(findDepartments, storeDepartment);
    }

    @Bean
    public FindAllDepartments findingAllDepartments(FindDepartments findDepartments) {
        return new FindAllDepartmentsImpl(findDepartments);
    }

    @Bean
    public FindDepartmentByCode findingDepartmentByCode(FindDepartments findDepartments) {
        return new FindDepartmentByCodeImpl(findDepartments);
    }
}
