package com.sa.healntrack.employee_service.department.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DepartmentCodeTest {

    private static final String VALID_CODE = "MED-001";
    private static final String INVALID_CODE_LETTERS = "MED001";
    private static final String INVALID_CODE_FORMAT = "ME-001";
    private static final String INVALID_CODE_LOWERCASE = "med-001";
    private static final String INVALID_CODE_EXTRA = "MED-0001";

    @Test
    void testCreateDepartmentCodeSuccessfully() {
        DepartmentCode code = new DepartmentCode(VALID_CODE);

        assertAll(
                () -> assertNotNull(code),
                () -> assertEquals(VALID_CODE, code.value())
        );
    }

    @Test
    void testCreateDepartmentCodeWithNullValueShouldThrowException() {
        assertThrows(NullPointerException.class, () -> new DepartmentCode(null));
    }

    @Test
    void testCreateDepartmentCodeWithInvalidFormatShouldThrowException() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> new DepartmentCode(INVALID_CODE_LETTERS)),
                () -> assertThrows(IllegalArgumentException.class, () -> new DepartmentCode(INVALID_CODE_FORMAT)),
                () -> assertThrows(IllegalArgumentException.class, () -> new DepartmentCode(INVALID_CODE_LOWERCASE)),
                () -> assertThrows(IllegalArgumentException.class, () -> new DepartmentCode(INVALID_CODE_EXTRA))
        );
    }

    @Test
    void testEqualsAndHashCode() {
        DepartmentCode code1 = new DepartmentCode(VALID_CODE);
        DepartmentCode code2 = new DepartmentCode(VALID_CODE);
        DepartmentCode code3 = new DepartmentCode("ADM-002");

        assertAll(
                () -> assertEquals(code1, code2),
                () -> assertNotEquals(code1, code3),
                () -> assertEquals(code1.hashCode(), code2.hashCode())
        );
    }
}
