-- Solicitudes de vacaciones
CREATE TABLE vacation (
    id UUID PRIMARY KEY,
    employee_id UUID NOT NULL,
    requested_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    status VARCHAR(20) NOT NULL CHECK (status IN ('PENDIENTE', 'APROBADA', 'RECHAZADA', 'AUTOGENERADA', 'FIRMADO')),
    approved_by UUID, --  uuid DEL MANAGER QUE APROBO
    approved_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    signed_at TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (employee_id) REFERENCES employee(id) ON DELETE CASCADE,
    FOREIGN KEY (approved_by) REFERENCES department_manager(id) ON DELETE CASCADE
);
