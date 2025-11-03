package com.sa.healntrack.employee_service.configuration.infrastructure.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfJpaRepository extends JpaRepository<ConfEntity, String>{
    
}
