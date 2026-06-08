package controllers;

import dao.VehiculoDAO;
import java.util.List;

/* =======================================================================================
 * CAPA LÓGICA DE NEGOCIO (CONTROLADOR - ALERTAS PREVENTIVAS)
 * =======================================================================================
 * CONCEPTO APLICADO: Controlador que procesa el estado del sistema (Inteligencia Operativa).
 * Orquesta la recuperación de datos delegando exclusivamente al VehiculoDAO, respetando 
 * la traza definida en el PUD sin dependencias a DAOs inexistentes en la arquitectura.
 * =======================================================================================
 */
public class AlertaController {
    
    private VehiculoDAO vehiculoDAO;

    /*
     * Constructor del Controlador.
     * Instancia el objeto de acceso a datos consolidado.
     */
    public AlertaController() {
        this.vehiculoDAO = new VehiculoDAO();
    }

    /*
     * Orquestador de Extracción de Alertas.
     * Canaliza los datos de la infraestructura relacional hacia la Vista.
     */
    public List<String> obtenerListaAlertas() {
        return vehiculoDAO.getVehiculosParaMantenimiento();
    }
}