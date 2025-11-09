package com.sa.healntrack.employee_service.department.infrastructure.adapter.out.persistence;

import com.sa.healntrack.employee_service.CommonJpaTest;
import com.sa.healntrack.employee_service.department.application.port.in.find_all_departments.FindAllDepartmentsQuery;
import com.sa.healntrack.employee_service.department.domain.Department;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class DepartmentRepositoryTest extends CommonJpaTest {

    @Autowired
    private DepartmentJpaRepository jpaRepository;

    private final DepartmentRepository departmentRepository;
    
    DepartmentRepositoryTest(@Autowired DepartmentJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
        this.departmentRepository = new DepartmentRepository(jpaRepository);
    }
    
    private static final LocalDateTime TODAY = LocalDateTime.now();
    private static final String CODE_1 = "MED-001";
    private static final String NAME_1 = "Medicina";
    private static final String DESCRIPTION_1 = "Área médica";

    private static final String CODE_2 = "ODO-002";
    private static final String NAME_2 = "Odontología";
    private static final String DESCRIPTION_2 = "Área dental";

    @Test
    void shouldFindAllDepartmentsWithoutFilters() {
        jpaRepository.save(new DepartmentEntity(CODE_1, NAME_1, DESCRIPTION_1, true, TODAY, TODAY));
        jpaRepository.save(new DepartmentEntity(CODE_2, NAME_2, DESCRIPTION_2, true, TODAY, TODAY));

        List<Department> result = departmentRepository.findAllDepartments(new FindAllDepartmentsQuery(null, null));

        assertThat(result).hasSize(2);
        assertThat(result.stream().map(d -> d.getCode().value()))
                .containsExactlyInAnyOrder(CODE_1, CODE_2);
    }

    @Test
    void shouldFilterBySearchTerm() {
        jpaRepository.save(new DepartmentEntity(CODE_1, NAME_1, DESCRIPTION_1, true, TODAY, TODAY));
        jpaRepository.save(new DepartmentEntity(CODE_2, NAME_2, DESCRIPTION_2, true, TODAY, TODAY));

        List<Department> result = departmentRepository.findAllDepartments(new FindAllDepartmentsQuery("Medicina", null));

        assertThat(result).hasSize(1);
        assertThat(result.getFirst().getCode().value()).isEqualTo(CODE_1);
    }

    @Test
    void shouldFilterByActiveStatus() {
        jpaRepository.save(new DepartmentEntity(CODE_1, NAME_1, DESCRIPTION_1, true, TODAY, TODAY));
        jpaRepository.save(new DepartmentEntity(CODE_2, NAME_2, DESCRIPTION_2, false, TODAY, TODAY));

        List<Department> result = departmentRepository.findAllDepartments(new FindAllDepartmentsQuery(null, true));

        assertThat(result).hasSize(1);
        assertThat(result.getFirst().getCode().value()).isEqualTo(CODE_1);
    }

    @Test
    void shouldFindDepartmentByCode() {
        jpaRepository.save(new DepartmentEntity(CODE_1, NAME_1, DESCRIPTION_1, true, TODAY, TODAY));

        Optional<Department> result = departmentRepository.findDepartmentByCode(CODE_1);

        assertThat(result).isPresent();
        assertThat(result.get().getCode().value()).isEqualTo(CODE_1);
        assertThat(result.get().getName()).isEqualTo(NAME_1);
    }

    @Test
    void shouldReturnEmptyWhenCodeDoesNotExist() {
        Optional<Department> result = departmentRepository.findDepartmentByCode("NON-999");

        assertThat(result).isEmpty();
    }

    @Test
    void shouldReturnTrueWhenCodeAndActiveMatch() {
        jpaRepository.save(new DepartmentEntity(CODE_1, NAME_1, DESCRIPTION_1, true, TODAY, TODAY));

        boolean exists = departmentRepository.existsByCodeAndIsActive(CODE_1, true);

        assertThat(exists).isTrue();
    }

    @Test
    void shouldReturnFalseWhenActiveDoesNotMatch() {
        jpaRepository.save(new DepartmentEntity(CODE_1, NAME_1, DESCRIPTION_1, false, TODAY, TODAY));

        boolean exists = departmentRepository.existsByCodeAndIsActive(CODE_1, true);

        assertThat(exists).isFalse();
    }

    @Test
    void shouldReturnFalseWhenCodeDoesNotExist() {
        boolean exists = departmentRepository.existsByCodeAndIsActive("NON-999", true);

        assertThat(exists).isFalse();
    }

    @Test
    void shouldSaveDepartmentSuccessfully() {
        Department department = new Department(CODE_1, NAME_1, DESCRIPTION_1);

        Department saved = departmentRepository.save(department);

        assertThat(saved).isNotNull();
        assertThat(saved.getCode().value()).isEqualTo(CODE_1);
        assertThat(saved.getName()).isEqualTo(NAME_1);
        assertThat(saved.getDescription()).isEqualTo(DESCRIPTION_1);

        
        Optional<DepartmentEntity> entity = jpaRepository.findById(CODE_1);
        assertThat(entity).isPresent();
        assertThat(entity.get().getName()).isEqualTo(NAME_1);
    }
}
