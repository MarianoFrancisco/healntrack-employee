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

import com.sa.healntrack.employee_service.department.application.exception.DepartmentNotFoundException;
import com.sa.healntrack.employee_service.department.application.port.out.persistence.FindDepartments;
import com.sa.healntrack.employee_service.department.domain.Department;

@ExtendWith(MockitoExtension.class)
class FindDepartmentByCodeImplTest {

    private static final String CODE = "MED-001";
    private static final String NAME = "Medicina General";
    private static final String DESC = "Área de medicina general";

    @Mock
    private FindDepartments findDepartments;

    @InjectMocks
    private FindDepartmentByCodeImpl findDepartmentByCodeImpl;

    private Department department;

    @BeforeEach
    void setUp() {
        department = new Department(CODE, NAME, DESC);
    }

    @Test
    void testFindByCode_Successfully() {
        when(findDepartments.findDepartmentByCode(CODE)).thenReturn(Optional.of(department));

        Department result = findDepartmentByCodeImpl.findByCode(CODE);

        assertEquals(CODE, result.getCode().value());
        verify(findDepartments).findDepartmentByCode(CODE);
    }

    @Test
    void testFindByCode_DepartmentNotFound() {
        when(findDepartments.findDepartmentByCode(CODE)).thenReturn(Optional.empty());

        assertThrows(DepartmentNotFoundException.class, () -> findDepartmentByCodeImpl.findByCode(CODE));
        verify(findDepartments).findDepartmentByCode(CODE);
    }
}

