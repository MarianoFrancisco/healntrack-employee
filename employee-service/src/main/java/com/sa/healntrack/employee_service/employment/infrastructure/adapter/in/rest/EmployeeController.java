package com.sa.healntrack.employee_service.employment.infrastructure.adapter.in.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sa.healntrack.employee_service.employment.application.port.in.find_department_managers.FindAllDepartmentManagers;
import com.sa.healntrack.employee_service.employment.application.port.in.find_department_managers.FindAllDepartmentManagersQuery;
import com.sa.healntrack.employee_service.employment.application.port.in.find_employees.*;
import com.sa.healntrack.employee_service.employment.application.port.in.find_employments.FindAllEmployments;
import com.sa.healntrack.employee_service.employment.application.port.in.find_employments.FindAllEmploymentsQuery;
import com.sa.healntrack.employee_service.employment.application.port.in.hire_employee.*;
import com.sa.healntrack.employee_service.employment.application.port.in.promote_employee.*;
import com.sa.healntrack.employee_service.employment.application.port.in.salary_increase.*;
import com.sa.healntrack.employee_service.employment.application.port.in.terminate_employment.*;
import com.sa.healntrack.employee_service.employment.application.port.in.update_employee.UpdateEmployee;
import com.sa.healntrack.employee_service.employment.application.port.in.update_employee.UpdateEmployeeCommand;
import com.sa.healntrack.employee_service.employment.domain.DepartmentManager;
import com.sa.healntrack.employee_service.employment.domain.Employee;
import com.sa.healntrack.employee_service.employment.domain.Employment;
import com.sa.healntrack.employee_service.employment.infrastructure.adapter.in.rest.dto.*;
import com.sa.healntrack.employee_service.employment.infrastructure.adapter.in.rest.mapper.DepartmentManagerRestMapper;
import com.sa.healntrack.employee_service.employment.infrastructure.adapter.in.rest.mapper.EmployeeRestMapper;
import com.sa.healntrack.employee_service.employment.infrastructure.adapter.in.rest.mapper.EmploymentRestMapper;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final HireEmployee hireEmployee;
    private final FindAllEmployees findAllEmployees;
    private final FindEmployeeByCui findEmployeeByCui;
    private final RehireEmployee rehireEmployee;
    private final PromoteEmployee promoteEmployee;
    private final SalaryIncrease salaryIncrease;
    private final TerminateEmployment terminateEmployment;
    private final FindAllEmployments findAllEmployments;
    private final FindAllDepartmentManagers findAllDepartmentManagers;
    private final UpdateEmployee updateEmployee;

    @PostMapping
    public ResponseEntity<EmployeeResponseDTO> hireEmployee(
            @RequestBody @Valid HireEmployeeRequestDTO requestDTO) {

        HireEmployeeCommand command = new HireEmployeeCommand(
                requestDTO.cui(),
                requestDTO.nit(),
                requestDTO.fullname(),
                requestDTO.email(),
                requestDTO.phoneNumber(),
                requestDTO.birthDate(),
                requestDTO.departmentCode(),
                requestDTO.salary(),
                requestDTO.igssPercent(),
                requestDTO.irtraPercent(),
                requestDTO.startDate(),
                requestDTO.notes()
        );

        Employee employee = hireEmployee.hireEmployee(command);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(EmployeeRestMapper.toResponseDTO(employee));
    }

    @PutMapping("/{cui}")
    public ResponseEntity<EmployeeResponseDTO> updateEmployee(
                    @PathVariable String cui,
                    @RequestBody @Valid UpdateEmployeeRequestDTO requestDTO) {

            UpdateEmployeeCommand command = new UpdateEmployeeCommand(
                            requestDTO.fullname(),
                            requestDTO.phoneNumber(),
                            requestDTO.igssPercent(),
                            requestDTO.irtraPercent());

            Employee employee = updateEmployee.updateEmployee(cui, command);
            return ResponseEntity.ok(EmployeeRestMapper.toResponseDTO(employee));
    }

    @GetMapping("/{cui}")
    public ResponseEntity<EmployeeResponseDTO> getEmployeeByCui(@PathVariable String cui) {
        Employee employee = findEmployeeByCui.findByCui(cui);
        return ResponseEntity.ok(EmployeeRestMapper.toResponseDTO(employee));
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponseDTO>> getAllEmployees(@Valid FindAllEmployeesRequestDTO requestDTO) {
        FindAllEmployeesQuery query = new FindAllEmployeesQuery(
                requestDTO.q(),
                requestDTO.department(),
                requestDTO.isActive()
        );
        
        List<Employee> employees = findAllEmployees.findAllEmployees(query);
        List<EmployeeResponseDTO> response = employees.stream()
                .map(EmployeeRestMapper::toResponseDTO)
                .toList();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{cui}/rehire")
    public ResponseEntity<EmployeeResponseDTO> rehireEmployee(
            @PathVariable String cui,
            @RequestBody @Valid RehireEmployeeRequestDTO requestDTO) {

        RehireEmployeeCommand command = new RehireEmployeeCommand(
                requestDTO.phoneNumber(),
                requestDTO.departmentCode(),
                requestDTO.newSalary(),
                requestDTO.igssPercent(),
                requestDTO.irtraPercent(),
                requestDTO.startDate(),
                requestDTO.notes()
        );

        Employee employee = rehireEmployee.rehireEmployee(cui, command);
        return ResponseEntity.ok(EmployeeRestMapper.toResponseDTO(employee));
    }

    @PostMapping("/{cui}/promote")
    public ResponseEntity<EmployeeResponseDTO> promoteEmployee(
            @PathVariable String cui,
            @RequestBody @Valid PromoteEmployeeRequestDTO requestDTO) {

        PromoteEmployeeCommand command = new PromoteEmployeeCommand(
                requestDTO.salaryIncrease(),
                requestDTO.departmentCode(),
                requestDTO.date(),
                requestDTO.notes()
        );

        Employee employee = promoteEmployee.promoteEmployee(cui, command);
        return ResponseEntity.ok(EmployeeRestMapper.toResponseDTO(employee));
    }

    @PostMapping("/{cui}/salary-increase")
    public ResponseEntity<EmployeeResponseDTO> increaseSalary(
            @PathVariable String cui,
            @RequestBody @Valid SalaryIncreaseRequestDTO requestDTO) {

        SalaryIncreaseCommand command = new SalaryIncreaseCommand(
                requestDTO.salaryIncrease(),
                requestDTO.date(),
                requestDTO.notes()
        );

        Employee employee = salaryIncrease.applySalaryIncrease(cui, command);
        return ResponseEntity.ok(EmployeeRestMapper.toResponseDTO(employee));
    }

    @DeleteMapping("/{cui}")
    public ResponseEntity<EmployeeResponseDTO> terminateEmployment(
            @PathVariable String cui,
            @RequestBody @Valid TerminateEmploymentRequestDTO requestDTO) {

        TerminateEmploymentCommand command = new TerminateEmploymentCommand(
                requestDTO.date(),
                requestDTO.terminationType(),
                requestDTO.reason()
        );

        Employee employee = terminateEmployment.terminateEmployment(cui, command);
        return ResponseEntity.ok(EmployeeRestMapper.toResponseDTO(employee));
    }

    @GetMapping("/history")
    public ResponseEntity<List<EmploymentResponseDTO>> getAllEmployments(
                    @Valid FindAllEmploymentsRequestDTO requestDTO) {

            FindAllEmploymentsQuery query = new FindAllEmploymentsQuery(
                            requestDTO.employee(),
                            requestDTO.department(),
                            requestDTO.type(),
                            requestDTO.startDateFrom(),
                            requestDTO.startDateTo(),
                            requestDTO.endDateFrom(),
                            requestDTO.endDateTo());

            List<Employment> employmentPeriods = findAllEmployments.findAllEmployments(query);
            List<EmploymentResponseDTO> response = employmentPeriods.stream()
                            .map(EmploymentRestMapper::toResponseDTO)
                            .toList();
            return ResponseEntity.ok(response);
    }

    @GetMapping("/managers")
    public ResponseEntity<List<DepartmentManagerResponseDTO>> getAllDepartmentManagers(
            @Valid FindAllDepartmentManagersRequestDTO requestDTO) {
        FindAllDepartmentManagersQuery query = new FindAllDepartmentManagersQuery(
                requestDTO.employee(),
                requestDTO.department(),
                requestDTO.startDateFrom(),
                requestDTO.startDateTo(),
                requestDTO.endDateFrom(),
                requestDTO.endDateTo(),
                requestDTO.isActive()
        );

        List<DepartmentManager> departmentManagers = findAllDepartmentManagers.findAllDepartmentManagers(query);
        List<DepartmentManagerResponseDTO> response = departmentManagers.stream()
                .map(DepartmentManagerRestMapper::toResponseDTO)
                .toList();
        return ResponseEntity.ok(response);
    }
    
}

