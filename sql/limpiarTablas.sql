-- Propósito: Vaciar las tablas definidas en crearDb.sql (sin eliminar la estructura)
-- IMPORTANTE: Revisa el contenido antes de ejecutar en producción.

USE ramirez_cars_service;
-- Chequeo Tablas con datos
SELECT * FROM Tarea_Realizada;
SELECT * FROM Orden_Trabajo;
SELECT * FROM Turno;
SELECT * FROM Vehiculo;
SELECT * FROM Cliente;
SELECT * FROM Reporte_Operativo;

-- Desactivar comprobación de claves foráneas temporalmente
SET FOREIGN_KEY_CHECKS = 0;

-- Orden de vaciado: primero tablas que dependen de otras
-- Primero las tablas que referencian a otras tablas
TRUNCATE TABLE Tarea_Realizada;
TRUNCATE TABLE Orden_Trabajo;
TRUNCATE TABLE Turno;
TRUNCATE TABLE Vehiculo;
TRUNCATE TABLE Cliente;
TRUNCATE TABLE Reporte_Operativo;

-- Volver a activar las comprobaciones de FK
SET FOREIGN_KEY_CHECKS = 1;

-- Chequeo Tablas sin datos
SELECT * FROM Tarea_Realizada;
SELECT * FROM Orden_Trabajo;
SELECT * FROM Turno;
SELECT * FROM Vehiculo;
SELECT * FROM Cliente;
SELECT * FROM Reporte_Operativo;

-- Alternativa (si TRUNCATE no es deseado):
-- START TRANSACTION;
-- DELETE FROM Orden_Trabajo;
-- DELETE FROM Vehiculo;
-- DELETE FROM Cliente;
-- COMMIT;

-- Fin de limpiarTablas.sql
