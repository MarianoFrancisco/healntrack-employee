package com.sa.healntrack.employee_service.department.infrastructure.adapter.in.rest;

import com.sa.healntrack.employee_service.department.application.port.in.create_department.CreateDepartment;
import com.sa.healntrack.employee_service.department.application.port.in.update_department.UpdateDepartment;
import com.sa.healntrack.employee_service.department.application.port.in.deactivate_department.DeactivateDepartment;
import com.sa.healntrack.employee_service.department.application.port.in.find_all_departments.FindAllDepartments;
import com.sa.healntrack.employee_service.department.application.port.in.create_department.CreateDepartmentCommand;
import com.sa.healntrack.employee_service.department.application.port.in.update_department.UpdateDepartmentCommand;
import com.sa.healntrack.employee_service.department.application.port.in.find_all_departments.FindAllDepartmentsQuery;
import com.sa.healntrack.employee_service.department.application.port.in.find_department_by_code.FindDepartmentByCode;
import com.sa.healntrack.employee_service.department.domain.Department;
import com.sa.healntrack.employee_service.department.infrastructure.adapter.in.rest.dto.CreateDepartmentRequestDTO;
import com.sa.healntrack.employee_service.department.infrastructure.adapter.in.rest.dto.UpdateDepartmentRequestDTO;
import com.sa.healntrack.employee_service.department.infrastructure.adapter.in.rest.dto.DepartmentResponseDTO;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final CreateDepartment createDepartment;
    private final UpdateDepartment updateDepartment;
    private final DeactivateDepartment deactivateDepartment;
    private final FindAllDepartments findAllDepartments;
    private final FindDepartmentByCode findDepartmentByCode;

    @PostMapping
    public ResponseEntity<DepartmentResponseDTO> createDepartment(
            @RequestBody @Valid CreateDepartmentRequestDTO requestDTO) {

        CreateDepartmentCommand command = new CreateDepartmentCommand(
                requestDTO.name(),
                requestDTO.code(),
                requestDTO.description());

        Department created = createDepartment.createDepartment(command);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(DepartmentRestMapper.toResponseDTO(created));
    }

    @PutMapping("/{code}")
    public ResponseEntity<DepartmentResponseDTO> updateDepartment(
            @PathVariable String code,
            @RequestBody @Valid UpdateDepartmentRequestDTO requestDTO) {

        UpdateDepartmentCommand command = new UpdateDepartmentCommand(
                requestDTO.name(),
                requestDTO.description());

        Department updated = updateDepartment.updateDepartment(code, command);
        return ResponseEntity.ok(DepartmentRestMapper.toResponseDTO(updated));
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Void> deactivateDepartment(@PathVariable String code) {
        deactivateDepartment.deactivateDepartment(code);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{code}")
    public ResponseEntity<DepartmentResponseDTO> getDepartment(@PathVariable String code) {
        Department department = findDepartmentByCode.findByCode(code);
        return ResponseEntity.ok(DepartmentRestMapper.toResponseDTO(department));
    }

    @GetMapping
    public ResponseEntity<List<DepartmentResponseDTO>> getAllDepartments(
            @Valid FindAllDepartmentsQuery requestDTO) {

        List<Department> departments = findAllDepartments.findAllDepartments(requestDTO);
        List<DepartmentResponseDTO> response = departments.stream()
                .map(DepartmentRestMapper::toResponseDTO)
                .toList();

        return ResponseEntity.ok(response);
    }
}
