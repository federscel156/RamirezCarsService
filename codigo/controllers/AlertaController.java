package controllers;

import dao.AlertaDAO;
import java.util.List;

/* =======================================================================================
 * CAPA LÓGICA DE NEGOCIO (CONTROLADOR - ALERTAS)
 * =======================================================================================
 * CONCEPTO APLICADO: Controlador que procesa el estado del sistema. Su rol es transformar 
 * la información técnica recuperada por el DAO en un formato legible para el actor 
 * Encargado, garantizando que el sistema sea capaz de generar inteligencia operativa.
 * =======================================================================================
 */
public class AlertaController {
    
    private AlertaDAO alertaDAO;

    public AlertaController() {
        this.alertaDAO = new AlertaDAO();
    }

    public List<String> obtenerListaAlertas() {
        return alertaDAO.obtenerVehiculosConMantenimientoPendiente();
    }
}