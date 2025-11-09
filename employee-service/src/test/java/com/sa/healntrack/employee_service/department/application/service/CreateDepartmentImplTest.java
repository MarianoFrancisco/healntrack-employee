package com.sa.healntrack.employee_service.department.application.service;

import com.sa.healntrack.employee_service.department.application.exception.DuplicateDepartmentException;
import com.sa.healntrack.employee_service.department.application.mapper.DepartmentMapper;
import com.sa.healntrack.employee_service.department.application.port.in.create_department.CreateDepartmentCommand;
import com.sa.healntrack.employee_service.department.application.port.out.persistence.FindDepartments;
import com.sa.healntrack.employee_service.department.application.port.out.persistence.StoreDepartment;
import com.sa.healntrack.employee_service.department.domain.Department;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateDepartmentImplTest {

    private static final String CODE = "MED-001";
    private static final String NAME = "Medicina General";
    private static final String DESCRIPTION = "Área de medicina general";

    @Mock
    private StoreDepartment storeDepartment;

    @Mock
    private FindDepartments findDepartments;

    @Mock
    private DepartmentMapper departmentMapper;

    @InjectMocks
    private CreateDepartmentImpl serviceToTest;

    @Test
    void testCreateDepartmentSuccessfully() {
        CreateDepartmentCommand command = new CreateDepartmentCommand(NAME, CODE, DESCRIPTION);

        when(findDepartments.existsByCodeAndIsActive(CODE, true)).thenReturn(false);

        Department result = serviceToTest.createDepartment(command);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(CODE, result.getCode().value()),
                () -> assertEquals(NAME, result.getName()),
                () -> assertEquals(DESCRIPTION, result.getDescription()),
                () -> assertTrue(result.isActive())
        );

        verify(findDepartments, times(1)).existsByCodeAndIsActive(CODE, true);
        verify(storeDepartment, times(1)).save(any(Department.class));
    }

    @Test
    void testCreateDepartmentWhenDuplicateCodeThrowsException() {
        CreateDepartmentCommand command = new CreateDepartmentCommand(NAME, CODE, DESCRIPTION);

        when(findDepartments.existsByCodeAndIsActive(CODE, true)).thenReturn(true);

        assertThrows(DuplicateDepartmentException.class, () -> serviceToTest.createDepartment(command));

        verify(findDepartments, times(1)).existsByCodeAndIsActive(CODE, true);
        verify(storeDepartment, never()).save(any(Department.class));
    }
}

