-- Propósito: Crear la base de datos para RAMIREZ CARS SERVICE
-- IMPORTANTE: Revisa el contenido antes de ejecutar en producción.
-- Creación de la Base de Datos
CREATE DATABASE IF NOT EXISTS ramirez_cars_service;
USE ramirez_cars_service;

-- Tabla Cliente
CREATE TABLE Cliente (
    id_cliente INT AUTO_INCREMENT PRIMARY KEY,
    nombre_completo VARCHAR(100) NOT NULL,
    telefono VARCHAR(20),
    email VARCHAR(100),
    fecha_registro DATE DEFAULT (CURRENT_DATE)
);

-- Tabla Vehiculo
CREATE TABLE Vehiculo (
    patente VARCHAR(10) PRIMARY KEY,
    marca VARCHAR(50) NOT NULL,
    modelo VARCHAR(50) NOT NULL,
    anio INT,
    vin VARCHAR(17),
    id_cliente INT,
    FOREIGN KEY (id_cliente) REFERENCES Cliente(id_cliente)
);

-- Tabla Orden_Trabajo
CREATE TABLE Orden_Trabajo (
    nro_ot INT AUTO_INCREMENT PRIMARY KEY,
    patente VARCHAR(10),
    fecha DATE DEFAULT (CURRENT_DATE),
    kilometraje INT NOT NULL,
    diagnostico_inicial TEXT,
    estado ENUM('Abierta', 'Finalizada', 'Cancelada') DEFAULT 'Abierta',
    FOREIGN KEY (patente) REFERENCES Vehiculo(patente)
);

-- Tabla Tarea Realizada (Crucial para el CU04)
CREATE TABLE Tarea_Realizada (
    id_tarea INT AUTO_INCREMENT PRIMARY KEY,
    nro_ot INT,
    descripcion_trabajo TEXT NOT NULL,
    insumos_texto TEXT,
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (nro_ot) REFERENCES Orden_Trabajo(nro_ot)
);

-- Tabla Turno (Crucial para el CU02)
CREATE TABLE Turno (
    id_turno INT AUTO_INCREMENT PRIMARY KEY,
    patente VARCHAR(10),
    fecha DATE NOT NULL,
    hora_inicio TIME NOT NULL,
    motivo VARCHAR(255),
    estado ENUM('Pendiente', 'Realizado', 'Cancelado') DEFAULT 'Pendiente',
    FOREIGN KEY (patente) REFERENCES Vehiculo(patente)
);

-- Tabla Reporte (Para el CU07)
CREATE TABLE Reporte_Operativo (
    id_reporte INT AUTO_INCREMENT PRIMARY KEY,
    tipo_reporte VARCHAR(50),
    fecha_generacion DATETIME DEFAULT CURRENT_TIMESTAMP,
    datos_json JSON -- O TEXT si prefieres
);