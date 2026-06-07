package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/* * =======================================================================================
 * CAPA DE PRESENTACIÓN (VISTA) - MENÚ PRINCIPAL
 * =======================================================================================
 * CONCEPTO APLICADO: Esta clase actúa como el "Dashboard" o enrutador principal del 
 * patrón MVC. Su funcionalidad para el sistema es proveer una interfaz gráfica centralizada 
 * desde la cual el Encargado del Taller puede acceder a todos los Casos de Uso.
 * * CUMPLIMIENTO DE RÚBRICA: 
 * 1. "Disponibilidad de un menú de selección": Implementado mediante JFrame y botones.
 * 2. "Estructuras condicionales": Uso de switch para gestionar las opciones del menú.
 * 3. "Creación de objetos": Instanciación en tiempo de ejecución de las vistas secundarias.
 * =======================================================================================
 */
public class DashboardPrincipal extends JFrame {

    private JButton btnRegistroCliente, btnAgenda, btnOrdenTrabajo, btnTareas, btnCierreOT, btnReportes, btnSalir;

    // CONSTRUCTOR: Inicializa los componentes visuales en memoria al arrancar el programa
    public DashboardPrincipal() {
        setTitle("Ramirez Cars Service - Sistema Técnico Automotriz");
        setSize(900, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
        setLayout(new BorderLayout());

        // --- PANEL LATERAL (MENÚ DE NAVEGACIÓN) ---
        JPanel panelMenu = new JPanel(new GridLayout(8, 1, 10, 10));
        panelMenu.setBackground(new Color(44, 62, 80)); 
        panelMenu.setBorder(BorderFactory.createEmptyBorder(20, 15, 20, 15));

        JLabel lblMenuTitulo = new JLabel("RAMIREZ CARS", SwingConstants.CENTER);
        lblMenuTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        lblMenuTitulo.setForeground(Color.WHITE);
        panelMenu.add(lblMenuTitulo);

        // Instanciación de las opciones correspondientes a los Casos de Uso del PUD
        btnRegistroCliente = new JButton("1. Registrar Cliente (CU01)");
        btnAgenda = new JButton("2. Gestión de Agenda (CU02)");
        btnOrdenTrabajo = new JButton("3. Apertura de OT (CU03)");
        btnTareas = new JButton("4. Registrar Tareas (CU04)");
        btnCierreOT = new JButton("5. Cerrar OT (CU05)");
        btnReportes = new JButton("6. Reportes Operativos (CU07)");
        btnSalir = new JButton("7. Salir del Sistema");

        panelMenu.add(btnRegistroCliente);
        panelMenu.add(btnAgenda);
        panelMenu.add(btnOrdenTrabajo);
        panelMenu.add(btnTareas);
        panelMenu.add(btnCierreOT);
        panelMenu.add(btnReportes);
        panelMenu.add(btnSalir);

        // --- PANEL CENTRAL (BIENVENIDA) ---
        JPanel panelCentral = new JPanel(new GridBagLayout());
        panelCentral.setBackground(new Color(236, 240, 241));
        JLabel lblBienvenida = new JLabel("Historial Clínico Automotriz - Prototipo Operacional");
        lblBienvenida.setFont(new Font("Arial", Font.BOLD, 22));
        panelCentral.add(lblBienvenida);

        add(panelMenu, BorderLayout.WEST);
        add(panelCentral, BorderLayout.CENTER);

        /* * LÓGICA DE EVENTOS (CONTROL DE FLUJO)
         * Se captura el clic del usuario y se utiliza una estructura 'switch' para 
         * derivar el control a la Vista correspondiente, instanciando el objeto necesario.
         */
        ActionListener menuListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String comando = e.getActionCommand();

                switch (comando) {
                    case "1. Registrar Cliente":
                        new ClienteView().setVisible(true); 
                        break;
                    case "2. Gestión de Agenda":
                        new TurnoView().setVisible(true);
                        break;
                    case "3. Apertura de OT":
                        new OrdenTrabajoView().setVisible(true);
                        break;
                    case "4. Registrar Tareas":
                        new TareaView().setVisible(true);
                        break;
                    case "5. Cerrar OT":
                        new CierreOTView().setVisible(true);
                        break;
                    case "6. Reportes Operativos":
                        new ReporteView().setVisible(true);
                        break;
                    case "7. Salir del Sistema":
                        int confirmacion = JOptionPane.showConfirmDialog(null, "¿Desea cerrar el sistema?", "Salir", JOptionPane.YES_NO_OPTION);
                        if (confirmacion == JOptionPane.YES_OPTION) System.exit(0);
                        break;
                }
            }
        };

        // Asignación del listener a los botones
        btnRegistroCliente.addActionListener(menuListener);
        btnAgenda.addActionListener(menuListener);
        btnOrdenTrabajo.addActionListener(menuListener);
        btnTareas.addActionListener(menuListener);
        btnCierreOT.addActionListener(menuListener);
        btnReportes.addActionListener(menuListener);
        btnSalir.addActionListener(menuListener);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new DashboardPrincipal().setVisible(true);
            } catch (Exception ex) {
                System.err.println("Error crítico: " + ex.getMessage());
            }
        });
    }
}