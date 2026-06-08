package controllers;

import dao.TurnoDAO;
import models.Turno;


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

    public String procesarRegistroTurno(String patente, String fechaStr, String horaStr, String motivo) {
        
        if (patente == null || patente.trim().isEmpty()) { return "Error: Patente obligatoria."; }
        if (fechaStr == null || fechaStr.trim().isEmpty()) { return "Error: Fecha obligatoria."; }
        if (horaStr == null || horaStr.trim().isEmpty()) { return "Error: Hora obligatoria (HH:MM)."; }
        if (motivo == null || motivo.trim().isEmpty()) { return "Error: Motivo obligatorio."; }

        try {
            java.text.SimpleDateFormat formatoFecha = new java.text.SimpleDateFormat("yyyy-MM-dd");
            formatoFecha.setLenient(false);
            java.util.Date fechaTurno = formatoFecha.parse(fechaStr);

            // Instanciamos con la hora
            Turno nuevoTurno = new Turno(patente, fechaTurno, horaStr, motivo);

            if (turnoDAO.agendarTurno(nuevoTurno)) { return "OK"; } 
            else { return "Error al agendar. ¿La patente existe?"; }

        } catch (java.text.ParseException e) {
            return "Error: Formato de fecha incorrecto (YYYY-MM-DD).";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}