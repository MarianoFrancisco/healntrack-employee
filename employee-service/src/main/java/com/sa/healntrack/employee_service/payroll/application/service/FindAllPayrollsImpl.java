package com.sa.healntrack.employee_service.payroll.application.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sa.healntrack.employee_service.payroll.application.port.in.FindAllPayrolls;
import com.sa.healntrack.employee_service.payroll.application.port.in.command.FindAllPayrollsQuery;
import com.sa.healntrack.employee_service.payroll.application.port.out.FindPayrolls;
import com.sa.healntrack.employee_service.payroll.domain.Payroll;

@Service
@Transactional(readOnly = true)
public class FindAllPayrollsImpl implements FindAllPayrolls {
    private final FindPayrolls findPayrolls;

    public FindAllPayrollsImpl(FindPayrolls findPayrolls) {
        this.findPayrolls = findPayrolls;
    }

    @Override
    public List<Payroll> findAllPayrolls(FindAllPayrollsQuery query) {
        return findPayrolls.findPayrolls(query);
    }
    
}
