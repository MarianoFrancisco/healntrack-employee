package com.sa.healntrack.employee_service.employment_period.application.port.out;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.sa.healntrack.employee_service.employment_period.application.port.in.find_employees.FindAllEmployeesQuery;
import com.sa.healntrack.employee_service.employment_period.domain.Employee;

public interface FindEmployees {
    List<Employee> findAllEmployees(FindAllEmployeesQuery query);
    Optional<Employee> findEmployeeById(UUID id);
    Optional<Employee> findEmployeeByCui(String cui);
    boolean existByCui(String cui);
    boolean existByNit(String nit);
    boolean existByEmail(String email);
}
