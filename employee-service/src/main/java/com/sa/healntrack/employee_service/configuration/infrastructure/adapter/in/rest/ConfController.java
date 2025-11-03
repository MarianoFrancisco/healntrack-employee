package com.sa.healntrack.employee_service.configuration.infrastructure.adapter.in.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sa.healntrack.employee_service.configuration.application.port.in.FindAllConfs;
import com.sa.healntrack.employee_service.configuration.application.port.in.UpdateConf;
import com.sa.healntrack.employee_service.configuration.domain.Configuration;
import com.sa.healntrack.employee_service.configuration.infrastructure.adapter.in.rest.dto.UpdateConfDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/employees/config")
@RequiredArgsConstructor
public class ConfController {
    private final UpdateConf updateConf;
    private final FindAllConfs findAllConfs;    

    @PutMapping("/{key}")
    public ResponseEntity<Configuration> updateConf(@PathVariable String key, @RequestBody @Valid UpdateConfDTO request) {
        Configuration updatedConf = updateConf.updateConf(key, request.value());
        return ResponseEntity.ok(updatedConf);
    }

    @GetMapping
    public ResponseEntity<List<Configuration>> getAllConfs() {
        List<Configuration> confs = findAllConfs.findAll();
        return ResponseEntity.ok(confs);
    }
}
