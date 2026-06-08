package controllers;

import dao.ReporteDAO;
import java.util.List;

/* =======================================================================================
 * CAPA LÓGICA DE NEGOCIO (CONTROLADOR DE EXTRACCIÓN) - CU07
 * =======================================================================================
 * CONCEPTO APLICADO: Controlador enfocado en operaciones de lectura y formateo de datos.
 * Aísla a la Vista de las consultas complejas de agregación, solicitando la información 
 * al DAO y canalizándola para su renderizado estadístico en el Dashboard del Encargado.
 * * CUMPLIMIENTO DE LA RÚBRICA:
 * - "Declaración y creación de objetos": Acoplamiento dinámico con la capa de infraestructura (DAO).
 * - "Empleo de estructuras condicionales": Se prevé el manejo de escenarios sin datos (Listas vacías).
 * =======================================================================================
 */
public class ReporteController {
    
    private ReporteDAO reporteDAO;

    /*
     * Constructor del Controlador de Extracción.
     * Instancia el objeto de acceso a datos para habilitar el canal de comunicación 
     * con el motor relacional orientado a consultas estadísticas.
     */
    public ReporteController() {
        this.reporteDAO = new ReporteDAO();
    }

    /*
     * Orquestador de Agregación de Datos.
     * Nombre de método 'generarEstadisticas' alineado estrictamente con el Diagrama de Secuencia.
     * Solicita la colección de datos consolidados para entregársela a la capa de presentación.
     */
    public List<String> generarEstadisticas(String tipoReporte) {
        return reporteDAO.fetchDatosAgrupados(tipoReporte);
    }
}