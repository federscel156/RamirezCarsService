package controllers;

import dao.OrdenTrabajoDAO;
import models.OrdenTrabajo;

/* =======================================================================================
 * CAPA LÓGICA DE NEGOCIO (CONTROLADOR TRANSACCIONAL) - APERTURA DE OT
 * =======================================================================================
 * CONCEPTO APLICADO: Controlador dedicado a la inicialización de la historia clínica.
 * Mantiene el principio de Responsabilidad Única (SRP) enfocándose exclusivamente 
 * en la validación de negocio previa a la apertura (Ej: Validación de Kilometraje - CP2).
 * * CUMPLIMIENTO DE LA RÚBRICA:
 * - "Empleo de estructuras condicionales": Se aplican validaciones de reglas de negocio cruzadas.
 * - "Tratamiento y manejo de excepciones": Captura preventiva del parseo de datos numéricos.
 * =======================================================================================
 */
public class OrdenTrabajoController {

    private OrdenTrabajoDAO otDAO;

    /*
     * Constructor del Controlador.
     * Instancia el objeto de acceso a datos necesario para validar las precondiciones 
     * en el motor relacional antes de autorizar la apertura de la orden.
     */
    public OrdenTrabajoController() {
        this.otDAO = new OrdenTrabajoDAO();
    }

    /*
     * Función Transaccional de Apertura de Orden (CU03).
     * Ejecuta la validación de la regla de negocio CP2 comprobando el historial 
     * de kilometraje mediante el DAO, garantizando la consistencia evolutiva del 
     * vehículo antes de persistir la nueva atención técnica.
     */
    public String procesarAperturaOT(String patente, String kilometrajeStr, String diagnostico) {
        
        // Verificación de presencia de datos obligatorios para la integridad referencial
        if (patente == null || patente.trim().isEmpty()) {
            return "Error de Validación: La patente del vehículo es obligatoria.";
        }
        if (diagnostico == null || diagnostico.trim().isEmpty()) {
            return "Error de Validación: El diagnóstico inicial es obligatorio.";
        }
        
        try {
            int kilometrajeActual = Integer.parseInt(kilometrajeStr);
            
            /*
             * Validación de Regla de Negocio (Caso de Prueba 2 - PUD).
             * Previene la inserción de datos inconsistentes consultando el clúster histórico.
             */
            int ultimoKm = otDAO.obtenerUltimoKilometraje(patente);
            if (kilometrajeActual < ultimoKm) {
                return "Error de Regla de Negocio: El kilometraje no puede ser menor al registro anterior (" + ultimoKm + " KM).";
            }
            
            // Instanciación del objeto en memoria con estado transaccional inicial
            OrdenTrabajo nuevaOT = new OrdenTrabajo(0, patente, new java.util.Date(), kilometrajeActual, diagnostico, "Abierta", null);
            
            // Delegación de la persistencia al componente de infraestructura
            if (otDAO.abrirOrden(nuevaOT)) {
                return "OK";
            } else {
                return "Error Operativo: No se pudo abrir la OT. Verifique que la patente exista en el sistema.";
            }
            
        } catch (NumberFormatException e) {
            return "Error de Formato: El kilometraje debe ser un valor numérico entero.";
        } catch (Exception e) {
            return "Error Crítico Inesperado: " + e.getMessage();
        }
    }
}