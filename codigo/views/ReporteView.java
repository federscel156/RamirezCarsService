package views;

import dao.ReporteDAO;
import javax.swing.*;
import java.awt.*;

/*
 * CONCEPTO APLICADO: Vista para el CU07.
 * Presenta de forma gráfica los indicadores clave (KPIs) del taller.
 */
public class ReporteView extends JFrame {
    public ReporteView() {
        setTitle("Reportes Operativos");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        ReporteDAO reporteDAO = new ReporteDAO();
        String metricas = reporteDAO.obtenerTotalOrdenesAbiertas();

        JLabel lblReporte = new JLabel(metricas, SwingConstants.CENTER);
        lblReporte.setFont(new Font("Arial", Font.BOLD, 14));
        add(lblReporte, BorderLayout.CENTER);
    }
}