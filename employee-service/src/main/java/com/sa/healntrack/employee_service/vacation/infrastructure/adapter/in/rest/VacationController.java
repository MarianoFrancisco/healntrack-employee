package com.sa.healntrack.employee_service.vacation.infrastructure.adapter.in.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sa.healntrack.employee_service.vacation.application.port.in.FindAllVacationConfs;
import com.sa.healntrack.employee_service.vacation.application.port.in.UpdateVacationConf;
import com.sa.healntrack.employee_service.vacation.domain.VacationConf;
import com.sa.healntrack.employee_service.vacation.infrastructure.adapter.in.rest.dto.UpdateVacationConfDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/employees/vacations")
@RequiredArgsConstructor
public class VacationController {
    private final UpdateVacationConf updateVacationConf;
    private final FindAllVacationConfs findAllVacationConfs;    

    @PutMapping("/config/{key}")
    public ResponseEntity<VacationConf> updateVacationConf(@PathVariable String key, @RequestBody @Valid UpdateVacationConfDTO request) {
        VacationConf updatedConf = updateVacationConf.updateVacationConf(key, request.value());
        return ResponseEntity.ok(updatedConf);
    }

    @GetMapping("/config")
    public ResponseEntity<List<VacationConf>> getAllVacationConfs() {
        List<VacationConf> confs = findAllVacationConfs.findAll();
        return ResponseEntity.ok(confs);
    }
}
