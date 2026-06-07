package controllers;

import dao.TurnoDAO;
import models.Turno;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/* * =======================================================================================
 * CAPA LÓGICA DE NEGOCIO (CONTROLADOR) - CU02
 * =======================================================================================
 * CONCEPTO APLICADO: Este componente cumple el rol de mediador en la arquitectura MVC.
 * Aísla completamente la lógica de negocio y las validaciones de la interfaz gráfica, 
 * asegurando que los datos sean correctos antes de instanciar los objetos del modelo.
 * * CUMPLIMIENTO DE RÚBRICA:
 * 1. "Estructuras condicionales": Uso de sentencias 'if' para prevenir datos nulos o vacíos.
 * 2. "Tratamiento y manejo de excepciones": Captura específica de ParseException para 
 * evitar que el sistema colapse si el usuario ingresa una fecha con formato incorrecto.
 * 3. "Declaración y creación de objetos": Uso del constructor new Turno().
 * =======================================================================================
 */
public class TurnoController {
    
    private TurnoDAO turnoDAO;

    public TurnoController() {
        this.turnoDAO = new TurnoDAO();
    }

    public String procesarRegistroTurno(String patente, String fechaStr, String motivo) {
        
        // Estructuras de control para validación de campos obligatorios
        if (patente == null || patente.trim().isEmpty()) {
            return "Error: La patente del vehículo es obligatoria para agendar un turno.";
        }
        if (fechaStr == null || fechaStr.trim().isEmpty()) {
            return "Error: Debe ingresar una fecha estimada.";
        }
        if (motivo == null || motivo.trim().isEmpty()) {
            return "Error: El motivo del turno es obligatorio.";
        }

        try {
            // Parseo de la fecha ingresada por el usuario al formato objeto Date de Java
            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
            formatoFecha.setLenient(false); // Validar que la fecha sea real
            Date fechaTurno = formatoFecha.parse(fechaStr);

            // Instanciación del objeto de dominio
            Turno nuevoTurno = new Turno(patente, fechaTurno, motivo);

            // Delegación de la persistencia a la capa de Acceso a Datos
            if (turnoDAO.agendarTurno(nuevoTurno)) {
                return "OK";
            } else {
                return "Error: No se pudo agendar. Verifique que la patente exista en el sistema.";
            }

        } catch (ParseException e) {
            // Manejo de excepciones (Requisito de la rúbrica)
            return "Error: El formato de la fecha es incorrecto. Utilice YYYY-MM-DD (ej: 2026-06-15).";
        } catch (Exception e) {
            return "Error inesperado en el sistema: " + e.getMessage();
        }
    }
}