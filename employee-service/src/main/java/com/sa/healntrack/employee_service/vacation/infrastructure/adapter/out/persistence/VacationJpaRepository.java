package com.sa.healntrack.employee_service.vacation.infrastructure.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VacationJpaRepository extends JpaRepository<VacationConfEntity, String>{
    
}
