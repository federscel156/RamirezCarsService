package controllers;

import dao.TareaDAO;
import dao.OrdenTrabajoDAO;
import models.TareaRealizada;
import java.util.List;

/* =======================================================================================
 * CAPA LÓGICA DE NEGOCIO (CONTROLADOR TRANSACCIONAL) - REGISTRO DE TAREAS
 * =======================================================================================
 * CONCEPTO APLICADO: Orquestador transaccional mixto. Coordina la persistencia de tareas 
 * (TareaDAO) y la provisión de datos dinámicos de contexto (OrdenTrabajoDAO) para 
 * alimentar las interfaces de selección, garantizando la integridad de los modelos de dominio.
 * =======================================================================================
 */
public class TareaController {
    
    private TareaDAO tareaDAO;
    private OrdenTrabajoDAO otDAO;

    /*
     * Constructor del Controlador.
     * Inyecta las dependencias a las capas de infraestructura necesarias para el módulo.
     */
    public TareaController() {
        this.tareaDAO = new TareaDAO();
        this.otDAO = new OrdenTrabajoDAO();
    }

    /*
     * Proveedor de Datos Dinámicos.
     * Canaliza la colección de atenciones activas hacia la capa de presentación (UX).
     */
    public List<String> obtenerOTsActivas() {
        return otDAO.obtenerOrdenesAbiertas();
    }

    /*
     * Función Transaccional de Inserción de Tarea.
     * Instancia el modelo exacto del dominio (TareaRealizada) inyectando la marca temporal 
     * del sistema (Date) antes de delegar la persistencia al motor MySQL.
     */
    public String procesarRegistroTarea(String nroOTStr, String descripcion, String insumos) {
        if (nroOTStr == null || nroOTStr.trim().isEmpty() || nroOTStr.contains("No hay")) {
            return "Error de Validación: Debe seleccionar una Orden de Trabajo válida.";
        }
        if (descripcion == null || descripcion.trim().isEmpty()) {
            return "Error de Validación: La descripción de la tarea es obligatoria.";
        }
        
        try {
            int nroOT = Integer.parseInt(nroOTStr.trim());
            
            // Instanciación fiel al modelo TareaRealizada del repositorio
            TareaRealizada nuevaTarea = new TareaRealizada(0, nroOT, descripcion, insumos, new java.util.Date());
            
            if (tareaDAO.registrarTarea(nuevaTarea)) {
                return "OK";
            } else {
                return "Error Operativo: No se pudo registrar la tarea en la base de datos.";
            }
            
        } catch (NumberFormatException e) {
            return "Error de Formato: El número de Orden de Trabajo es inválido.";
        } catch (Exception e) {
            return "Error Crítico Inesperado: " + e.getMessage();
        }
    }
}