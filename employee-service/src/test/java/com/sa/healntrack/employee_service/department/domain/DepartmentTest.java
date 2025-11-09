package com.sa.healntrack.employee_service.department.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DepartmentTest {

    private static final String CODE = "MED-001";
    private static final String NAME = "Medicina General";
    private static final String DESCRIPTION = "Área de medicina general del hospital";
    private static final String NAME_WITH_SPACES = "   Medicina Interna   ";
    private static final String NAME_WITHOUT_SPACES = "Medicina Interna";
    private static final String DESCRIPTION_WITH_SPACES = "   Área de atención general   ";
    private static final String DESCRIPTION_WITHOUT_SPACES = "Área de atención general";
    private static final String EMPTY_STRING = "   ";
    private static final String OTHER_CODE = "ADM-002";
    private static final String OTHER_NAME = "Otro Nombre";
    private static final String OTHER_DESCRIPTION = "Otra descripción";

    @Test
    void testCreateDepartmentSuccessfully() {
        Department department = new Department(CODE, NAME, DESCRIPTION);

        assertAll(
                () -> assertEquals(new DepartmentCode(CODE), department.getCode()),
                () -> assertEquals(NAME, department.getName()),
                () -> assertEquals(DESCRIPTION, department.getDescription()),
                () -> assertTrue(department.isActive())
        );
    }

    @Test
    void testCreateDepartmentTrimsNameAndDescription() {
        Department department = new Department(CODE, NAME_WITH_SPACES, DESCRIPTION_WITH_SPACES);

        assertAll(
                () -> assertEquals(NAME_WITHOUT_SPACES, department.getName()),
                () -> assertEquals(DESCRIPTION_WITHOUT_SPACES, department.getDescription())
        );
    }

    @Test
    void testCreateDepartmentWithNullOrEmptyDescriptionShouldReturnNull() {
        Department departmentWithNull = new Department(CODE, NAME, null);
        Department departmentWithEmpty = new Department(CODE, NAME, EMPTY_STRING);

        assertAll(
                () -> assertNull(departmentWithNull.getDescription()),
                () -> assertNull(departmentWithEmpty.getDescription())
        );
    }

    @Test
    void testCreateDepartmentWithEmptyNameShouldThrowException() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> new Department(CODE, EMPTY_STRING, DESCRIPTION)),
                () -> assertThrows(IllegalArgumentException.class, () -> new Department(CODE, null, DESCRIPTION))
        );
    }

    @Test
    void testDeactivateDepartment() {
        Department department = new Department(CODE, NAME, DESCRIPTION);
        department.deactivate();

        assertFalse(department.isActive());
    }

    @Test
    void testActivateDepartment() {
        Department department = new Department(CODE, NAME, DESCRIPTION);
        department.deactivate();
        department.activate();

        assertTrue(department.isActive());
    }

    @Test
    void testEqualsAndHashCodeBasedOnCode() {
        Department department1 = new Department(CODE, NAME, DESCRIPTION);
        Department department2 = new Department(CODE, OTHER_NAME, OTHER_DESCRIPTION);
        Department department3 = new Department(OTHER_CODE, NAME, DESCRIPTION);

        assertAll(
                () -> assertEquals(department1, department2),
                () -> assertNotEquals(department1, department3),
                () -> assertEquals(department1.hashCode(), department2.hashCode())
        );
    }
}
