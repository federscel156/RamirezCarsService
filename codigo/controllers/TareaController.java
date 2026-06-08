package controllers;

import dao.TareaDAO;
import models.TareaRealizada;
import java.util.Date;

public class TareaController {
    private TareaDAO tareaDAO;

    public TareaController() {
        this.tareaDAO = new TareaDAO();
    }

    public String procesarRegistroTarea(String nroOTStr, String descripcion, String insumos) {
        if (descripcion == null || descripcion.trim().isEmpty()) {
            return "Error: La descripción del trabajo es obligatoria.";
        }
        try {
            int nroOT = Integer.parseInt(nroOTStr);
            TareaRealizada nuevaTarea = new TareaRealizada(0, nroOT, descripcion, insumos, new Date());
            
            if (tareaDAO.registrarTarea(nuevaTarea)) {
                return "OK";
            } else {
                return "Error: No se pudo guardar. Verifique que el Nro de OT exista.";
            }
        } catch (NumberFormatException e) {
            return "Error: El Número de OT debe ser numérico.";
        }
    }
}