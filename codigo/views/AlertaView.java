package views;

import controllers.AlertaController;
import javax.swing.*;
import java.awt.*;
import java.util.List;

/* =======================================================================================
 * CAPA DE PRESENTACIÓN (VISTA) - MÓDULO DE ALERTAS PREVENTIVAS
 * =======================================================================================
 * CONCEPTO APLICADO: Vista operativa para la visualización de indicadores clave (KPIs). 
 * Utiliza componentes de lista dinámica para informar al Encargado sobre el estado 
 * de la flota atendida, cumpliendo con la inteligencia operativa requerida en el PUD.
 * =======================================================================================
 */
public class AlertaView extends JFrame {

    private AlertaController alertaController;

    public AlertaView() {
        alertaController = new AlertaController();

        setTitle("Alertas de Mantenimiento Preventivo");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        /*
         * Bloque de renderizado dinámico.
         * Recupera la colección de datos procesados por el controlador y los despliega 
         * en un componente JList, facilitando la lectura rápida para el actor Encargado.
         */
        List<String> alertas = alertaController.obtenerListaAlertas();
        DefaultListModel<String> model = new DefaultListModel<>();
        for (String alerta : alertas) {
            model.addElement(alerta);
        }
        
        JList<String> listaAlertas = new JList<>(model);
        add(new JScrollPane(listaAlertas), BorderLayout.CENTER);

        if (model.isEmpty()) {
            add(new JLabel("No hay alertas pendientes."), BorderLayout.NORTH);
        }
    }
}