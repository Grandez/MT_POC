CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    nif VARCHAR(15) NOT NULL UNIQUE
);

INSERT INTO usuarios (nombre, apellidos, nif)
VALUES
('Juan', 'Pérez García', '12345678A'),
('María', 'López Martínez', '87654321B');
