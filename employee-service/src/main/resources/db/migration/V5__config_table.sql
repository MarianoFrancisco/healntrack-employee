-- Configuraciones generales del microservicio
CREATE TABLE configuration (
    key VARCHAR(50) PRIMARY KEY,
    value INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO configuration (key, value) VALUES ('vacation_days', 10);
INSERT INTO configuration (key, value) VALUES ('vacation_change_advance_days', 7);
