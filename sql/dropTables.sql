-- Propósito: Eliminar las tablas definidas en crearDb.sql (BORRA LA ESTRUCTURA)
-- ADVERTENCIA: Este script borra las tablas. Haz backup si hace falta.

USE ramirez_cars_service;

SET FOREIGN_KEY_CHECKS = 0;

-- Eliminar primero las tablas dependientes
DROP TABLE IF EXISTS Tarea_Realizada;
DROP TABLE IF EXISTS Orden_Trabajo;
DROP TABLE IF EXISTS Turno;
DROP TABLE IF EXISTS Vehiculo;
DROP TABLE IF EXISTS Cliente;
DROP TABLE IF EXISTS Reporte_Operativo;

SET FOREIGN_KEY_CHECKS = 1;

-- Fin de dropTables.sql
