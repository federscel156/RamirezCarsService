-- Propósito: Poblar las tablas definidas en crearDb.sql (Datos de prueba)
-- IMPORTANTE: Revisa el contenido antes de ejecutar en producción.
USE ramirez_cars_service;

-- 1. Poblado de la tabla Cliente (5 Casos)
INSERT INTO Cliente (nombre_completo, telefono, email, fecha_registro) VALUES
('Juan Carlos Perez', '3516123456', 'juan.perez@email.com', '2026-01-10'),
('Marta Gonzalez', '3543456789', 'marta.gonzalez@gmail.com', '2026-02-15'),
('Roberto Gomez', '3517890123', 'roberto.gomez@outlook.com', '2026-03-05'),
('Lucia Diaz', '3543112233', 'lucia.diaz@yahoo.com', '2026-04-12'),
('Carlos Paz', '3519988776', 'cpaz_valla@email.com', '2026-05-01');

-- 2. Poblado de la tabla Vehiculo (Asociados a los clientes anteriores)
INSERT INTO Vehiculo (patente, marca, modelo, anio, vin, id_cliente) VALUES
('AF123JK', 'Toyota', 'Corolla', 2022, '9BH12345678901234', 1),
('AE456LL', 'Ford', 'Focus', 2020, '8AF98765432109876', 2),
('AD789MM', 'Volkswagen', 'Gol Trend', 2018, '9BW11223344556677', 3),
('AG321PO', 'Fiat', 'Cronos', 2023, '8AD55667788990011', 4),
('AB654RT', 'Chevrolet', 'Onix', 2016, '9BG33445566778899', 5);

-- 3. Poblado de Orden_Trabajo (Simulando historial y casos abiertos)
INSERT INTO Orden_Trabajo (patente, fecha, kilometraje, diagnostico_inicial, estado) VALUES
('AF123JK', '2026-05-10', 45000, 'Service de los 45.000km y revisión de frenos.', 'Finalizada'),
('AE456LL', '2026-05-12', 82300, 'Ruido metálico en tren delantero al doblar.', 'Finalizada'),
('AD789MM', '2026-05-15', 110000, 'Cambio de correa de distribución y bomba de agua.', 'Abierta'),
('AG321PO', '2026-05-16', 15200, 'Control de niveles y chequeo de luz de motor encendida.', 'Abierta'),
('AB654RT', '2026-04-20', 135000, 'Cambio de aceite y filtros.', 'Finalizada');

-- 4. Poblado de Tarea_Realizada (Detalles técnicos para las OTs anteriores)
-- Nota: nro_ot 1, 2 y 5 están finalizadas
INSERT INTO Tarea_Realizada (nro_ot, descripcion_trabajo, insumos_texto) VALUES
(1, 'Cambio de aceite sintético 5W30 y filtro original.', 'Aceite Castrol, Filtro Toyota OEM'),
(1, 'Reemplazo de pastillas de freno delanteras.', 'Pastillas de freno marca Fras-le'),
(2, 'Reemplazo de bujes de parrilla y bieletas.', 'Bujes de goma reforzados, Bieletas Thompson'),
(5, 'Cambio de aceite mineral 10W40 y filtro de aire.', 'Aceite Shell Helix, Filtro Fram'),
(3, 'Desarmado de tapa de distribución para acceso a correa.', 'N/A - En proceso');

-- 5. Poblado de la tabla Turno (Agenda futura)
INSERT INTO Turno (patente, fecha, hora_inicio, motivo, estado) VALUES
('AF123JK', '2026-05-20', '08:30:00', 'Alineación y balanceo post-reparación.', 'Pendiente'),
('AE456LL', '2026-05-21', '09:00:00', 'Revisión de amortiguadores traseros.', 'Pendiente'),
('AG321PO', '2026-05-18', '14:30:00', 'Continuación diagnóstico escáner.', 'Pendiente');

-- 6. Poblado de Reporte_Operativo (informes de servicio)
INSERT INTO Reporte_Operativo (tipo_reporte, datos_json) VALUES
('Mantenimiento', JSON_OBJECT('vehiculo', 'AF123JK', 'mecanico', 'Pedro Martinez', 'detalle', 'Revisión completa antes de viaje.', 'hora', '10:30')),
('Eficiencia', JSON_OBJECT('periodo', 'Mayo 2026', 'total_ot', 5, 'promedio_km', 82000, 'observacion', 'Buen desempeño general')),
('Incidente', JSON_OBJECT('vehiculo', 'AD789MM', 'problema', 'Correa de distribución', 'urgente', true, 'accion', 'Reemplazo inmediato'));

-- 7. Confirmación de los cambios
COMMIT;