package com.sa.healntrack.employee_service.payroll.application.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sa.healntrack.employee_service.payroll.application.port.in.FindAllPayrollsItems;
import com.sa.healntrack.employee_service.payroll.application.port.in.command.FindAllPayrollsQuery;
import com.sa.healntrack.employee_service.payroll.application.port.out.FindPayrollItems;
import com.sa.healntrack.employee_service.payroll.domain.PayrollItem;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FindAllPayrollsItemsImpl implements FindAllPayrollsItems {
    private final FindPayrollItems findPayrollItems;

    @Override
    public List<PayrollItem> findAllPayrollsItems(FindAllPayrollsQuery query) {
        return findPayrollItems.findAll(query);
    }

}
