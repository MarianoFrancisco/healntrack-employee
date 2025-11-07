CREATE TABLE payroll (
    id UUID PRIMARY KEY,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    pay_day DATE NOT NULL,
    type VARCHAR(20) NOT NULL CHECK (type IN ('REGULAR', 'AJUSTE')),
    total_gross_amount DECIMAL(12,2) DEFAULT 0.00 NOT NULL,
    total_igss_deduction DECIMAL(12,2) DEFAULT 0.00 NOT NULL,
    total_irtra_deduction DECIMAL(12,2) DEFAULT 0.00 NOT NULL,
    total_net_amount DECIMAL(12,2) DEFAULT 0.00 NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


-- Descripcion de los pagos
CREATE TABLE payroll_item (
    id UUID PRIMARY KEY,
    payroll_id UUID NOT NULL,
    employee_id UUID NOT NULL,
    department_code CHAR(7) NOT NULL,
    gross_salary DECIMAL(10,2) NOT NULL,
    igss_deduction DECIMAL(10,2) DEFAULT 0.00,
    irtra_deduction DECIMAL(10,2) DEFAULT 0.00,
    net_salary DECIMAL(10,2) NOT NULL,
    notes VARCHAR(255),
    FOREIGN KEY (payroll_id) REFERENCES payroll(id) ON DELETE CASCADE,
    FOREIGN KEY (employee_id) REFERENCES employee(id) ON DELETE CASCADE,
    FOREIGN KEY (department_code) REFERENCES department(code) ON DELETE SET NULL
);
