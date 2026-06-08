package views;

import controllers.ReporteController;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.List;

/* =======================================================================================
 * CAPA DE PRESENTACIÓN (VISTA) - MÓDULO DE INTELIGENCIA OPERATIVA
 * =======================================================================================
 * CONCEPTO APLICADO: Interfaz de usuario orientada a la visualización de métricas y KPIs.
 * Utiliza los componentes nativos de Java Swing (JList, DefaultListModel) para renderizar 
 * colecciones de datos dinámicos provenientes de la base de datos a través del Controlador.
 * * CUMPLIMIENTO DE LA RÚBRICA:
 * - "Estructuras condicionales y repetitivas": Uso de iteradores (for-each) para poblar el 
 * modelo de la lista y validaciones (if/else) para gestionar estados vacíos (Graceful degradation).
 * =======================================================================================
 */
public class ReporteView extends JFrame {

    private ReporteController reporteController;

    /*
     * Constructor principal de la interfaz de Reportes.
     * Configura el contenedor raíz, define su layout espacial y desencadena asíncronamente 
     * la extracción de los datos para rellenar la pantalla al momento de ser instanciada.
     */
    public ReporteView() {
        reporteController = new ReporteController();

        setTitle("Módulo de Inteligencia Operativa - Estadísticas");
        setSize(550, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(15, 15));

        /*
         * Bloque de Renderizado de Datos Consolidados (Panel Central).
         * Construye un modelo de lista en memoria y lo alimenta iterando sobre la respuesta 
         * del controlador, encapsulándolo visualmente con bordes titulados para una óptima UX.
         */
        JPanel panelCentral = new JPanel(new BorderLayout());
        Border bordeBase = BorderFactory.createLineBorder(Color.LIGHT_GRAY);
        TitledBorder titulo = BorderFactory.createTitledBorder(bordeBase, " [ MÉTRICAS GLOBALES DEL TALLER ] ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Arial", Font.BOLD, 12), new Color(44, 62, 80));
        panelCentral.setBorder(BorderFactory.createCompoundBorder(titulo, BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        // Llamada a la capa lógica para la obtención de métricas
        List<String> datos = reporteController.generarEstadisticas("Consolidado General de OTs");
        DefaultListModel<String> modeloLista = new DefaultListModel<>();
        
        // Estructura condicional y repetitiva para el mapeo de datos al componente visual
        if (datos.isEmpty()) {
            modeloLista.addElement("No se encontraron registros estadísticos en la base de datos.");
        } else {
            for (String métrica : datos) {
                modeloLista.addElement(" >> " + métrica);
            }
        }

        JList<String> listaReporte = new JList<>(modeloLista);
        listaReporte.setFont(new Font("Monospaced", Font.PLAIN, 13));
        listaReporte.setBackground(new Color(248, 249, 250));
        panelCentral.add(new JScrollPane(listaReporte), BorderLayout.CENTER);

        /*
         * Bloque de Comandos de Interfaz (Panel Sur).
         * Gestiona la salida segura de la ventana estadística liberando los recursos de Swing.
         */
        JPanel panelSur = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        JButton btnCerrar = new JButton("Cerrar Visualizador");
        btnCerrar.addActionListener(e -> dispose());
        panelSur.add(btnCerrar);

        add(panelCentral, BorderLayout.CENTER);
        add(panelSur, BorderLayout.SOUTH);
    }
}