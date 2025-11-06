package com.sa.healntrack.employee_service.vacation.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sa.healntrack.employee_service.employment.application.exception.DepartmentManagerNotFoundException;
import com.sa.healntrack.employee_service.employment.application.port.out.FindDepartmentManagers;
import com.sa.healntrack.employee_service.employment.domain.DepartmentManager;
import com.sa.healntrack.employee_service.vacation.application.exception.VacationAlreadyProcessedException;
import com.sa.healntrack.employee_service.vacation.application.exception.VacationNotFoundException;
import com.sa.healntrack.employee_service.vacation.application.port.in.RejectVacation;
import com.sa.healntrack.employee_service.vacation.application.port.in.command.ReviewVacationCommand;
import com.sa.healntrack.employee_service.vacation.application.port.out.FindVacations;
import com.sa.healntrack.employee_service.vacation.application.port.out.StoreVacation;
import com.sa.healntrack.employee_service.vacation.domain.Vacation;
import com.sa.healntrack.employee_service.vacation.domain.VacationStatus;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class RejectVacationImpl implements RejectVacation {
    private final FindVacations findVacations;
    private final FindDepartmentManagers findDepartmentManagers;
    private final StoreVacation storeVacation;

    @Override
    public void rejectVacation(UUID id, ReviewVacationCommand command) {
        Vacation vacation = findVacations.findById(id)
                .orElseThrow(() -> new VacationNotFoundException(id));

        if (vacation.getStatus() == VacationStatus.APROBADA || 
            vacation.getStatus() == VacationStatus.FIRMADO) {
            throw new VacationAlreadyProcessedException(
                vacation.getPeriod().startDate(),
                vacation.getPeriod().endDate(),
                vacation.getStatus()
            );
        }

        DepartmentManager rejecter = findDepartmentManagers.findByCuiAndIsActive(command.reviewer(), true)
                .orElseThrow(() -> new DepartmentManagerNotFoundException(command.reviewer()));

        vacation.reject(rejecter, command.reviewedAt());

        storeVacation.save(vacation);
    }
}
