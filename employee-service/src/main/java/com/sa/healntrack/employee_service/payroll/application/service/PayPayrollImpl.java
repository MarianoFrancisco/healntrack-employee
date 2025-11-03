package com.sa.healntrack.employee_service.payroll.application.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sa.healntrack.employee_service.employment.application.port.in.find_employees.FindAllEmployeesQuery;
import com.sa.healntrack.employee_service.employment.application.port.out.FindEmployees;
import com.sa.healntrack.employee_service.employment.domain.Employee;
import com.sa.healntrack.employee_service.payroll.application.exception.DuplicatePayrollException;
import com.sa.healntrack.employee_service.payroll.application.mapper.PayrollMapper;
import com.sa.healntrack.employee_service.payroll.application.port.in.PayPayroll;
import com.sa.healntrack.employee_service.payroll.application.port.in.command.PayPayrollCommand;
import com.sa.healntrack.employee_service.payroll.application.port.out.FindPayrolls;
import com.sa.healntrack.employee_service.payroll.application.port.out.StorePayroll;
import com.sa.healntrack.employee_service.payroll.domain.Payroll;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class PayPayrollImpl implements PayPayroll {

    private final FindEmployees findEmployees;
    private final FindPayrolls findPayrolls;
    private final StorePayroll storePayroll;

    @Override
    public Payroll payPayroll(PayPayrollCommand command) {
        if (findPayrolls.existsByStartDateAndEndDate(command.startDate(), command.endDate())) {
            throw new DuplicatePayrollException(command.startDate(), command.endDate());
        }

        Payroll payroll = PayrollMapper.toDomain(command);
        List<Employee> employees = findEmployees.findAllEmployees(FindAllEmployeesQuery.activeOnly(true));

        employees.forEach(employee ->
            payroll.addItem(employee, "Pago regular del periodo " +
                command.startDate() + " - " + command.endDate())
        );

        storePayroll.save(payroll);

        return payroll;
    }
}
