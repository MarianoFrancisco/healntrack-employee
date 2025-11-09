package com.sa.healntrack.employee_service.department.application.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import com.sa.healntrack.employee_service.department.application.port.in.find_all_departments.FindAllDepartmentsQuery;
import com.sa.healntrack.employee_service.department.application.port.out.persistence.FindDepartments;
import com.sa.healntrack.employee_service.department.domain.Department;

@ExtendWith(MockitoExtension.class)
class FindAllDepartmentsImplTest {

    private static final String CODE_1 = "MED-001";
    private static final String NAME_1 = "Medicina General";
    private static final String DESC_1 = "Área médica del hospital";

    private static final String CODE_2 = "ADM-002";
    private static final String NAME_2 = "Administración";
    private static final String DESC_2 = "Departamento administrativo";

    private static final String FILTER_DEPARTMENT_NAME = "med";
    private static final String NO_FILTER_DEPARTMENT_NAME = null;
    private static final Boolean NO_FILTER_DEPARTMENT_STATUS = null;

    @Mock
    private FindDepartments findDepartments;

    @InjectMocks
    private FindAllDepartmentsImpl findAllDepartmentsImpl;

    private Department department1;
    private Department department2;

    @BeforeEach
    void setUp() {
        department1 = new Department(CODE_1, NAME_1, DESC_1);
        department2 = new Department(CODE_2, NAME_2, DESC_2);
    }

    @Test
    void testFindAllDepartments_WithFilters() {
        FindAllDepartmentsQuery query = new FindAllDepartmentsQuery(FILTER_DEPARTMENT_NAME, true);
        when(findDepartments.findAllDepartments(query)).thenReturn(List.of(department1));

        List<Department> result = findAllDepartmentsImpl.findAllDepartments(query);

        assertAll(
            () -> assertEquals(1, result.size()),
            () -> assertEquals(CODE_1, result.get(0).getCode().value())
        );

        verify(findDepartments).findAllDepartments(query);
    }

    @Test
    void testFindAllDepartments_WithoutFilters() {
        FindAllDepartmentsQuery query = new FindAllDepartmentsQuery(NO_FILTER_DEPARTMENT_NAME, NO_FILTER_DEPARTMENT_STATUS);
        when(findDepartments.findAllDepartments(query)).thenReturn(List.of(department1, department2));

        List<Department> result = findAllDepartmentsImpl.findAllDepartments(query);

        assertAll(
            () -> assertEquals(2, result.size()),
            () -> assertEquals(CODE_1, result.get(0).getCode().value()),
            () -> assertEquals(CODE_2, result.get(1).getCode().value())
        );
        verify(findDepartments).findAllDepartments(query);
    }
}
