package com.sa.healntrack.employee_service.employment.application.port.out;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.sa.healntrack.employee_service.employment.application.port.in.find_employees.FindAllEmployeesQuery;
import com.sa.healntrack.employee_service.employment.domain.Employee;

public interface FindEmployees {
    List<Employee> findAllEmployees(FindAllEmployeesQuery query);
    Optional<Employee> findEmployeeByCui(String cui);
    Optional<Employee> findEmployeeById(UUID id);
    Optional<Employee> findEmployeeByEmail(String email);
    boolean existByCui(String cui);
    boolean existByNit(String nit);
    boolean existByEmail(String email);
    boolean existByDepartmentAndIsActive(String departmentCode, boolean isActive);
}
