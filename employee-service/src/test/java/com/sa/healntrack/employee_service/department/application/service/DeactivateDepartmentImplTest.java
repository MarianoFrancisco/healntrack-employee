package com.sa.healntrack.employee_service.department.application.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import com.sa.healntrack.employee_service.department.application.exception.DepartmentHasActiveEmployeesException;
import com.sa.healntrack.employee_service.department.application.exception.DepartmentNotFoundException;
import com.sa.healntrack.employee_service.department.application.port.out.persistence.FindDepartments;
import com.sa.healntrack.employee_service.department.application.port.out.persistence.StoreDepartment;
import com.sa.healntrack.employee_service.department.domain.Department;
import com.sa.healntrack.employee_service.employment.application.port.out.FindEmployees;

@ExtendWith(MockitoExtension.class)
class DeactivateDepartmentImplTest {

    private static final String CODE = "MED-001";
    private static final String NAME = "Medicina General";
    private static final String DESCRIPTION = "Área médica del hospital";

    @Mock
    private StoreDepartment storeDepartment;

    @Mock
    private FindDepartments findDepartments;

    @Mock
    private FindEmployees findEmployees;

    @InjectMocks
    private DeactivateDepartmentImpl deactivateDepartmentImpl;

    private Department department;

    @BeforeEach
    void setUp() {
        department = new Department(CODE, NAME, DESCRIPTION);
    }

    @Test
    void testDeactivateDepartmentSuccessfully() {
        when(findDepartments.findDepartmentByCode(CODE)).thenReturn(Optional.of(department));
        when(findEmployees.existByDepartmentAndIsActive(CODE, true)).thenReturn(false);

        deactivateDepartmentImpl.deactivateDepartment(CODE);

        assertFalse(department.isActive());
        verify(storeDepartment).save(department);
    }

    @Test
    void testDeactivateDepartmentThrowsNotFoundException() {
        when(findDepartments.findDepartmentByCode(CODE)).thenReturn(Optional.empty());

        assertThrows(DepartmentNotFoundException.class, () -> deactivateDepartmentImpl.deactivateDepartment(CODE));

        verify(storeDepartment, never()).save(any());
    }

    @Test
    void testDeactivateDepartmentThrowsHasActiveEmployeesException() {
        when(findDepartments.findDepartmentByCode(CODE)).thenReturn(Optional.of(department));
        when(findEmployees.existByDepartmentAndIsActive(CODE, true)).thenReturn(true);

        assertThrows(DepartmentHasActiveEmployeesException.class, () -> deactivateDepartmentImpl.deactivateDepartment(CODE));

        verify(storeDepartment, never()).save(any());
    }
}

