package com.sa.healntrack.employee_service.vacation.domain;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

import com.sa.healntrack.employee_service.employment.domain.DepartmentManager;
import com.sa.healntrack.employee_service.employment.domain.Employee;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(of = "id")
public class Vacation {
    private final VacationId id;
    private final Employee employee;
    private final VacationPeriod period;
    private final LocalDate requestedAt;
    private VacationStatus status;
    private DepartmentManager approvedBy;
    private LocalDate approvedAt;
    private LocalDate signedAt;

    public Vacation(UUID id, Employee employee, LocalDate requestedAt, LocalDate startDate, LocalDate endDate,
            VacationStatus status,
            DepartmentManager approvedBy, LocalDate approvedAt, LocalDate signedAt) {
        this.id = new VacationId(id);
        this.employee = Objects.requireNonNull(employee, "El empleado no puede ser nulo");
        this.period = new VacationPeriod(startDate, endDate);
        this.requestedAt = validateRequestedAt(requestedAt, startDate);
        this.status = Objects.requireNonNull(status, "El estado de la solicitud no puede ser nulo");
        this.approvedBy = approvedBy;
        this.approvedAt = approvedAt;
        this.signedAt = signedAt;
    }

    public Vacation(UUID id, Employee employee, LocalDate requestedAt, LocalDate startDate, LocalDate endDate) {
        this(id, employee, requestedAt, startDate, endDate, VacationStatus.PENDIENTE, null, null, null);
    }

    private LocalDate validateRequestedAt(LocalDate requestedAt, LocalDate startDate) {
        Objects.requireNonNull(requestedAt, "La fecha de solicitud no puede ser nula");
        if (requestedAt.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de solicitud no puede ser futura");
        }
        if (requestedAt.isAfter(startDate)) {
            throw new IllegalArgumentException(
                    "La fecha de solicitud no puede ser posterior a la fecha de inicio de las vacaciones");
        }
        return requestedAt;
    }

    public void approve(DepartmentManager approver, LocalDate date) {
        if (date.isAfter(this.period.startDate())) {
            throw new IllegalArgumentException("La fecha de aprobación no puede ser posterior a la fecha de inicio de las vacaciones");
            
        }

        changeApprovalStatus(approver, VacationStatus.APROBADA, date);
    }

    public void reject(DepartmentManager approver, LocalDate date) {
        changeApprovalStatus(approver, VacationStatus.RECHAZADA, date);
    }

    public void sign() {
        if (!status.equals(VacationStatus.APROBADA)) {
            throw new IllegalStateException("Solo se pueden firmar solicitudes aprobadas.");
        }
        this.signedAt = LocalDate.now();
    }

    private void changeApprovalStatus(DepartmentManager approver, VacationStatus newStatus, LocalDate date) {
        Objects.requireNonNull(approver, "El aprobador no puede ser nulo");
        Objects.requireNonNull(date, "La fecha de aprobación no puede ser nula");
        
        if (!this.employee.isActive()) {
            throw new IllegalArgumentException("No se puede cambiar el estado porque no existe contrato para el empleado con cui: " + this.employee.getCui().value());
        }

        if(date.isBefore(this.requestedAt)) {
            throw new IllegalArgumentException("La fecha de aprobación no puede ser anterior a la fecha de solicitud");
        }

        if (!approver.getDepartment().equals(this.employee.getDepartment())) {
            throw new IllegalStateException("Solo un gerente del mismo departamento puede aprobar o rechazar la solicitud.");
        }
        if (!status.equals(VacationStatus.PENDIENTE)) {
            throw new IllegalStateException("Solo se pueden modificar solicitudes pendientes.");
        }
        this.status = newStatus;
        this.approvedBy = approver;
        this.approvedAt = date;
    }

}
