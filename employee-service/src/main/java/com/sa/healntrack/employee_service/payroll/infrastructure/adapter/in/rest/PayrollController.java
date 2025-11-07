package com.sa.healntrack.employee_service.payroll.infrastructure.adapter.in.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sa.healntrack.employee_service.payroll.application.port.in.FindAllPayrollsItems;
import com.sa.healntrack.employee_service.payroll.application.port.in.PayPayroll;
import com.sa.healntrack.employee_service.payroll.application.port.in.command.FindAllPayrollsQuery;
import com.sa.healntrack.employee_service.payroll.application.port.in.command.PayPayrollCommand;
import com.sa.healntrack.employee_service.payroll.domain.Payroll;
import com.sa.healntrack.employee_service.payroll.domain.PayrollItem;
import com.sa.healntrack.employee_service.payroll.infrastructure.adapter.in.rest.mapper.PayrollItemRestMapper;
import com.sa.healntrack.employee_service.payroll.infrastructure.adapter.in.rest.mapper.PayrollRestMapper;
import com.sa.healntrack.employee_service.payroll.infrastructure.adapter.in.rest.dto.*;

import lombok.RequiredArgsConstructor;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/employees/payrolls")
@RequiredArgsConstructor
public class PayrollController {

        private final PayPayroll payPayroll;
        private final FindAllPayrollsItems findAllPayrollsItems;

        @PostMapping
        public ResponseEntity<PayrollResponseDTO> payPayroll(@RequestBody @Valid PayPayrollRequestDTO requestDTO) {

                PayPayrollCommand command = new PayPayrollCommand(
                                requestDTO.startDate(),
                                requestDTO.endDate(),
                                requestDTO.payDay(),
                                requestDTO.type());

                Payroll payroll = payPayroll.payPayroll(command);
                return ResponseEntity.status(HttpStatus.CREATED)
                                .body(PayrollRestMapper.toResponseDTO(payroll));
        }

        @GetMapping
        public ResponseEntity<List<PayrollItemResponseDTO>> getAllPayrolls(
                        @Valid FindAllPayrollsRequestDTO requestDTO) {

                FindAllPayrollsQuery query = new FindAllPayrollsQuery(
                                requestDTO.employee(),
                                requestDTO.department(),
                                requestDTO.paydayFrom(),
                                requestDTO.paydayTo(),
                                requestDTO.startDate(),
                                requestDTO.endDate(),
                                requestDTO.type());

                List<PayrollItem> payrollItems = findAllPayrollsItems.findAllPayrollsItems(query);
                List<PayrollItemResponseDTO> response = payrollItems.stream()
                                .map(PayrollItemRestMapper::toResponseDTO)
                                .toList();

                return ResponseEntity.ok(response);
        }

}
