package com.sa.healntrack.employee_service.department.infrastructure.adapter.in.rest;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Collections;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.sa.healntrack.employee_service.CommonMvcTest;
import com.sa.healntrack.employee_service.department.application.port.in.create_department.*;
import com.sa.healntrack.employee_service.department.application.port.in.update_department.*;
import com.sa.healntrack.employee_service.department.application.port.in.deactivate_department.*;
import com.sa.healntrack.employee_service.department.application.port.in.find_all_departments.*;
import com.sa.healntrack.employee_service.department.application.port.in.find_department_by_code.*;
import com.sa.healntrack.employee_service.department.domain.Department;
import com.sa.healntrack.employee_service.department.infrastructure.adapter.in.rest.dto.*;

@WebMvcTest(controllers = DepartmentController.class)
class DepartmentControllerTest extends CommonMvcTest {

    @MockitoBean
    private CreateDepartment createDepartment;
    @MockitoBean
    private UpdateDepartment updateDepartment;
    @MockitoBean
    private DeactivateDepartment deactivateDepartment;
    @MockitoBean
    private FindAllDepartments findAllDepartments;
    @MockitoBean
    private FindDepartmentByCode findDepartmentByCode;

    private static final String BASE_URL = "/api/v1/departments";
    private static final String CODE = "MED-001";
    private static final String NAME = "Medicina General";
    private static final String DESCRIPTION = "Área médica";
    private static final String UPDATED_NAME = "Medicina Actualizada";
    private static final String UPDATED_DESCRIPTION = "Área actualizada";

    private static final Department DEPARTMENT = new Department(CODE, NAME, DESCRIPTION);
    private static final DepartmentResponseDTO DEPARTMENT_RESPONSE_DTO = new DepartmentResponseDTO(CODE, NAME,
            DESCRIPTION, true);

    @Test
    @DisplayName("POST /api/v1/departments")
    void shouldCreateDepartment() throws Exception {
        CreateDepartmentRequestDTO request = new CreateDepartmentRequestDTO(NAME, CODE, DESCRIPTION);
        DepartmentResponseDTO responseDTO = new DepartmentResponseDTO(CODE, NAME, DESCRIPTION, true);
        when(createDepartment.createDepartment(any(CreateDepartmentCommand.class))).thenReturn(DEPARTMENT);

        mockMvc.perform(post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(result -> {
                    String body = result.getResponse().getContentAsString();
                    Assertions.assertThat(body).isEqualTo(objectMapper.writeValueAsString(responseDTO));
                });
    }

    @Test
    @DisplayName("PATCH /api/v1/departments/{code}")
    void shouldUpdateDepartment() throws Exception {
        UpdateDepartmentRequestDTO request = new UpdateDepartmentRequestDTO(UPDATED_NAME, UPDATED_DESCRIPTION);
        Department updated = new Department(CODE, UPDATED_NAME, UPDATED_DESCRIPTION);
        DepartmentResponseDTO responseDTO = new DepartmentResponseDTO(CODE, UPDATED_NAME, UPDATED_DESCRIPTION, true);
        when(updateDepartment.updateDepartment(eq(CODE), any(UpdateDepartmentCommand.class))).thenReturn(updated);

        mockMvc.perform(patch(BASE_URL + "/" + CODE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    String body = result.getResponse().getContentAsString();
                    Assertions.assertThat(body).isEqualTo(objectMapper.writeValueAsString(responseDTO));
                });
    }

    @Test
    @DisplayName("DELETE /api/v1/departments/{code}")
    void shouldDeactivateDepartment() throws Exception {
        doNothing().when(deactivateDepartment).deactivateDepartment(CODE);

        mockMvc.perform(delete(BASE_URL + "/" + CODE))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("GET /api/v1/departments/{code}")
    void shouldGetDepartmentByCode() throws Exception {
        when(findDepartmentByCode.findByCode(CODE)).thenReturn(DEPARTMENT);

        mockMvc.perform(get(BASE_URL + "/" + CODE))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    String body = result.getResponse().getContentAsString();
                    Assertions.assertThat(body).isEqualTo(objectMapper.writeValueAsString(DEPARTMENT_RESPONSE_DTO));
                });
    }

    @Test
    @DisplayName("GET /api/v1/departments sin filtros")
    void shouldGetAllDepartmentsWithoutFilters() throws Exception {
        List<Department> departments = List.of(
                new Department(CODE, NAME, DESCRIPTION),
                new Department("ODO-002", "Odontología", "Área dental"));
        List<DepartmentResponseDTO> departmentResponseDTOs = List.of(
                new DepartmentResponseDTO(CODE, NAME, DESCRIPTION, true),
                new DepartmentResponseDTO("ODO-002", "Odontología", "Área dental", true));
        when(findAllDepartments.findAllDepartments(any(FindAllDepartmentsQuery.class))).thenReturn(departments);

        mockMvc.perform(get(BASE_URL))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    String body = result.getResponse().getContentAsString();
                    Assertions.assertThat(body).isEqualTo(objectMapper.writeValueAsString(departmentResponseDTOs));
                });
    }

    @Test
    @DisplayName("GET /api/v1/departments con filtros")
    void shouldGetDepartmentsWithFilters() throws Exception {
        List<Department> departments = Collections.singletonList(DEPARTMENT);
        List<DepartmentResponseDTO> departmentResponseDTOs = Collections.singletonList(DEPARTMENT_RESPONSE_DTO);
        when(findAllDepartments.findAllDepartments(any(FindAllDepartmentsQuery.class))).thenReturn(departments);

        mockMvc.perform(get(BASE_URL)
                .param("q", "MED")
                .param("isActive", "true"))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    String body = result.getResponse().getContentAsString();
                    Assertions.assertThat(body).isEqualTo(objectMapper.writeValueAsString(departmentResponseDTOs));
                });
    }
}
