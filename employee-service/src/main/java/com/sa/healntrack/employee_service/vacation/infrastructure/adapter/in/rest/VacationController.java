package com.sa.healntrack.employee_service.vacation.infrastructure.adapter.in.rest;

import java.util.List;
import java.util.UUID;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sa.healntrack.employee_service.vacation.application.port.in.ApproveVacation;
import com.sa.healntrack.employee_service.vacation.application.port.in.FindAllVacations;
import com.sa.healntrack.employee_service.vacation.application.port.in.FindVacationById;
import com.sa.healntrack.employee_service.vacation.application.port.in.RejectVacation;
import com.sa.healntrack.employee_service.vacation.application.port.in.RequestVacation;
import com.sa.healntrack.employee_service.vacation.application.port.in.command.FindAllVacationsQuery;
import com.sa.healntrack.employee_service.vacation.application.port.in.command.RequestVacationCommand;
import com.sa.healntrack.employee_service.vacation.application.port.in.command.ReviewVacationCommand;
import com.sa.healntrack.employee_service.vacation.domain.Vacation;
import com.sa.healntrack.employee_service.vacation.infrastructure.adapter.in.rest.dto.FindAllVacationsRequestDTO;
import com.sa.healntrack.employee_service.vacation.infrastructure.adapter.in.rest.dto.RequestVacationRequestDTO;
import com.sa.healntrack.employee_service.vacation.infrastructure.adapter.in.rest.dto.ReviewVacationRequestDTO;
import com.sa.healntrack.employee_service.vacation.infrastructure.adapter.in.rest.dto.VacationResponseDTO;
import com.sa.healntrack.employee_service.vacation.infrastructure.adapter.in.rest.mapper.VacationRestMapper;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/employees/vacations")
@RequiredArgsConstructor
public class VacationController {

    private final RequestVacation requestVacation;
    private final ApproveVacation approveVacation;
    private final RejectVacation rejectVacation;
    private final FindAllVacations findAllVacations;
    private final FindVacationById findVacationById;

    @PostMapping
    public ResponseEntity<VacationResponseDTO> requestVacation(
            @RequestBody @Valid RequestVacationRequestDTO requestDTO) {

        RequestVacationCommand command = new RequestVacationCommand(
                requestDTO.employeeCui(),
                requestDTO.requestedAt(),
                requestDTO.startDate(),
                requestDTO.endDate()
        );

        Vacation vacation = requestVacation.requestVacation(command);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(VacationRestMapper.toResponseDTO(vacation));
    }
        @GetMapping("/{id}")
    public ResponseEntity<VacationResponseDTO> getVacationById(@PathVariable UUID id) {
        Vacation vacation = findVacationById.findVacationById(id);
        return ResponseEntity.ok(VacationRestMapper.toResponseDTO(vacation));
    }

    @PostMapping("/{id}/approve")
    public ResponseEntity<Void> approveVacation(
            @PathVariable UUID id,
            @RequestBody @Valid ReviewVacationRequestDTO requestDTO) {

        ReviewVacationCommand command = new ReviewVacationCommand(
                requestDTO.reviewerCui(),
                requestDTO.reviewedAt()
        );

        approveVacation.approveVacation(id, command);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/reject")
    public ResponseEntity<Void> rejectVacation(
            @PathVariable UUID id,
            @RequestBody @Valid ReviewVacationRequestDTO requestDTO) {

        ReviewVacationCommand command = new ReviewVacationCommand(
                requestDTO.reviewerCui(),
                requestDTO.reviewedAt()
        );

        rejectVacation.rejectVacation(id, command);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<VacationResponseDTO>> getAllVacations(
            @Valid FindAllVacationsRequestDTO requestDTO) {

        FindAllVacationsQuery query = new FindAllVacationsQuery(
                requestDTO.employee(),
                requestDTO.department(),
                requestDTO.startDate(),
                requestDTO.endDate(),
                requestDTO.requestedAtFrom(),
                requestDTO.requestedAtTo(),
                requestDTO.status()
        );

        List<Vacation> vacations = findAllVacations.findAllVacations(query);
        List<VacationResponseDTO> response = vacations.stream()
                .map(VacationRestMapper::toResponseDTO)
                .toList();

        return ResponseEntity.ok(response);
    }
}
