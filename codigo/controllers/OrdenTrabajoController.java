package controllers;

import dao.OrdenTrabajoDAO;
import models.OrdenTrabajo;
import java.util.Date;

public class OrdenTrabajoController {
    
    private OrdenTrabajoDAO otDAO;

    public OrdenTrabajoController() {
        this.otDAO = new OrdenTrabajoDAO();
    }

    public String procesarAperturaOT(String patente, String kilometrajeStr, String diagnostico) {
        
        if (patente == null || patente.trim().isEmpty()) {
            return "Error: La patente es obligatoria para asociar la OT.";
        }
        
        try {
            int kilometraje = Integer.parseInt(kilometrajeStr);
            
            // Se crea la OT con estado inicial "Abierta"
            OrdenTrabajo nuevaOT = new OrdenTrabajo(0, patente, new Date(), kilometraje, diagnostico, "Abierta", null);
            
            if (otDAO.abrirOrden(nuevaOT)) {
                return "OK";
            } else {
                return "Error: No se pudo abrir la OT. Verifique que la patente exista.";
            }
            
        } catch (NumberFormatException e) {
            return "Error: El kilometraje debe ser un valor numérico.";
        } catch (Exception e) {
            return "Error inesperado: " + e.getMessage();
        }
    }

    public String procesarCierreOT(String nroOTStr) {
        try {
            int nroOT = Integer.parseInt(nroOTStr);
            if (otDAO.cerrarOrden(nroOT)) {
                return "OK";
            } else {
                return "Error: No se pudo cerrar la OT. Verifique que el Nro exista.";
            }
        } catch (NumberFormatException e) {
            return "Error: El Número de OT debe ser numérico.";
        }
    }
}