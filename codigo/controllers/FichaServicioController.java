package controllers;

import dao.OrdenTrabajoDAO;
import java.util.List;

/* =======================================================================================
 * CAPA LÓGICA DE NEGOCIO (CONTROLADOR TRANSACCIONAL) - FINALIZACIÓN DE SERVICIO
 * =======================================================================================
 * CONCEPTO APLICADO: Orquestador de transacciones complejas bajo el patrón MVC.
 * Implementa el principio de Responsabilidad Única (SRP) al delegar el acceso a datos al DAO 
 * y la renderización del documento al controlador de infraestructura, actuando exclusivamente 
 * como un puente validador de las reglas de negocio de la fase de cierre (CU06).
 * =======================================================================================
 */
public class FichaServicioController {
    
    private OrdenTrabajoDAO otDAO;
    private GeneradorPDFController pdfController;

    /*
     * Constructor del Controlador.
     * Instancia y acopla los componentes necesarios para llevar a cabo el flujo 
     * de cierre operativo, aislando a la Vista de estos detalles de implementación.
     */
    public FichaServicioController() {
        this.otDAO = new OrdenTrabajoDAO();
        this.pdfController = new GeneradorPDFController();
    }

    /*
     * Proveedor de Datos Dinámicos.
     * Canaliza la colección de atenciones activas ('Abiertas') hacia la capa de presentación.
     */
    public List<String> obtenerOTsActivas() {
        return otDAO.obtenerOrdenesAbiertas();
    }

    /*
     * Función Transaccional de Cierre y Emisión.
     * Recibe los estímulos validados desde la interfaz y ejecuta la persistencia en dos fases:
     * Fase 1: Cambio de estado ('Finalizada') en el motor relacional.
     * Fase 2: Renderizado del documento probatorio físico (PDF).
     */
    public String procesarCierreYEmision(String nroOTStr, String patente, String diagnosticoFinal) {
        
        if (nroOTStr == null || nroOTStr.trim().isEmpty()) {
            return "Error de Validación: Debe seleccionar una Orden de Trabajo válida.";
        }
        if (patente == null || patente.trim().isEmpty()) {
            return "Error de Validación: La patente es requerida para identificar el comprobante.";
        }
        if (diagnosticoFinal == null || diagnosticoFinal.trim().isEmpty()) {
            return "Error de Validación: Debe ingresar el resumen técnico para la impresión.";
        }

        try {
            int nroOT = Integer.parseInt(nroOTStr.trim());
            
            /*
             * Ejecución Secuencial Condicionada.
             * Evalúa primero la respuesta del servidor MySQL. Solo si la historia clínica queda 
             * inmutablemente sellada, se procede a consumir el recurso del generador PDF.
             */
            if (otDAO.finalizarOrden(nroOT)) {
                
                boolean pdfGenerado = pdfController.generarFicha(nroOT, patente, diagnosticoFinal);
                
                if (pdfGenerado) {
                    return "OK";
                } else {
                    return "Alerta de Sistema: La Orden fue sellada en la BD, pero ocurrió un fallo al exportar el archivo PDF.";
                }
            } else {
                return "Error Operativo: No se pudo finalizar. Verifique que el Nro de OT sea válido y se encuentre Abierta.";
            }
            
        } catch (NumberFormatException e) {
            return "Error de Formato: El Número de Orden (OT) debe contener exclusivamente caracteres numéricos.";
        } catch (Exception e) {
            return "Error Crítico Inesperado: " + e.getMessage();
        }
    }
}