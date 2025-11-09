package com.sa.healntrack.employee_service.department.application.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sa.healntrack.employee_service.department.application.exception.DepartmentNotFoundException;
import com.sa.healntrack.employee_service.department.application.mapper.DepartmentMapper;
import com.sa.healntrack.employee_service.department.application.port.in.update_department.UpdateDepartmentCommand;
import com.sa.healntrack.employee_service.department.application.port.out.persistence.FindDepartments;
import com.sa.healntrack.employee_service.department.application.port.out.persistence.StoreDepartment;
import com.sa.healntrack.employee_service.department.domain.Department;

@ExtendWith(MockitoExtension.class)
class UpdateDepartmentImplTest {

    private static final String CODE = "MED-001";
    private static final String OLD_NAME = "Medicina General";
    private static final String OLD_DESC = "Área de medicina general";
    private static final String NEW_NAME = "Medicina Interna";
    private static final String NEW_DESC = "Área especializada en medicina interna";

    @Mock
    private StoreDepartment storeDepartment;

    @Mock
    private FindDepartments findDepartments;

    @InjectMocks
    private UpdateDepartmentImpl updateDepartmentImpl;

    private Department existingDepartment;
    private UpdateDepartmentCommand command;

    @BeforeEach
    void setUp() {
        existingDepartment = new Department(CODE, OLD_NAME, OLD_DESC);
        command = new UpdateDepartmentCommand(NEW_NAME, NEW_DESC);
    }

    @Test
    void testUpdateDepartment_Successfully() {
        Department updatedDepartment = DepartmentMapper.updateDepartment(existingDepartment, command);

        when(findDepartments.findDepartmentByCode(CODE)).thenReturn(Optional.of(existingDepartment));
        when(storeDepartment.save(any(Department.class))).thenReturn(updatedDepartment);

        Department result = updateDepartmentImpl.updateDepartment(CODE, command);

        assertEquals(NEW_NAME, result.getName());
        assertEquals(NEW_DESC, result.getDescription());
        verify(findDepartments).findDepartmentByCode(CODE);
        verify(storeDepartment).save(any(Department.class));
    }

    @Test
    void testUpdateDepartment_DepartmentNotFound() {
        when(findDepartments.findDepartmentByCode(CODE)).thenReturn(Optional.empty());

        assertThrows(DepartmentNotFoundException.class, () -> updateDepartmentImpl.updateDepartment(CODE, command));
        verify(findDepartments).findDepartmentByCode(CODE);
        verify(storeDepartment, never()).save(any());
    }
}
