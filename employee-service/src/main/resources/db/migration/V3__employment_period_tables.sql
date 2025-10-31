CREATE TABLE employee (
    id UUID PRIMARY KEY,
    cui CHAR(13) UNIQUE NOT NULL,
    nit CHAR(9)  UNIQUE NOT NULL,
    fullname VARCHAR(200) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone_number CHAR(8) NOT NULL,
    birth_date DATE,
    is_active BOOLEAN DEFAULT TRUE,
    department_code CHAR(7) NOT NULL,
    salary DECIMAL(10,2) NOT NULL DEFAULT 0.00,
    igss_percent DECIMAL(5,2) DEFAULT 0.00,
    irtra_percent DECIMAL(5,2) DEFAULT 0.00,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (department_code) REFERENCES department(code) ON DELETE SET NULL
);

CREATE TABLE department_manager (
    id UUID PRIMARY KEY,
    department_code CHAR(7) NOT NULL,
    employee_id UUID NOT NULL,
    start_date DATE NOT NULL DEFAULT CURRENT_DATE,
    end_date DATE,
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (department_code) REFERENCES department(code) ON DELETE CASCADE,
    FOREIGN KEY (employee_id) REFERENCES employee(id) ON DELETE CASCADE
);

-- Historial laboral
CREATE TABLE employment_period (
    id UUID PRIMARY KEY,
    employee_id UUID NOT NULL,
    type VARCHAR(20) NOT NULL CHECK (type IN ('CONTRATACION', 'RECONTRATACION', 'ASENSO', 'AUMENTO', 'DESPIDO', 'RENUNCIA')),
    start_date DATE NOT NULL,
    end_date DATE,
    salary DECIMAL(10,2) NOT NULL,
    notes VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (employee_id) REFERENCES employee(id) ON DELETE CASCADE
);