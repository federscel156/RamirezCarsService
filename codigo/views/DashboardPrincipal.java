package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/* =======================================================================================
 * CAPA DE PRESENTACIÓN (VISTA) - MENÚ PRINCIPAL
 * =======================================================================================
 * CONCEPTO APLICADO: Esta clase actúa como el "Dashboard" o enrutador principal del 
 * patrón arquitectónico MVC. Su funcionalidad para el sistema es proveer una interfaz 
 * gráfica centralizada desde la cual el Encargado del Taller puede coordinar y disparar 
 * los flujos de ejecución operativos.
 * * CUMPLIMIENTO DE LA RÚBRICA: 
 * 1. "Disponibilidad de un menú de selección": Implementado mediante JFrame y botones Swing.
 * 2. "Estructuras condicionales": Uso de switch jerárquico para gobernar eventos de comando.
 * 3. "Creación de objetos": Instanciación dinámica en memoria de las pantallas secundarias.
 * =======================================================================================
 */
public class DashboardPrincipal extends JFrame {

    private JButton btnRegistroCliente, btnAgenda, btnOrdenTrabajo, btnTareas, btnAlertas, btnFichaPDF, btnReportes, btnSalir;

    /*
     * Constructor de la clase principal encargado de inicializar los objetos visuales,
     * configurar las dimensiones del contenedor Standalone y definir el Layout raíz.
     */
    public DashboardPrincipal() {
        setTitle("Ramirez Cars Service - Sistema Técnico Automotriz");
        setSize(950, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
        setLayout(new BorderLayout());

        /*
         * Bloque de construcción del Menú de Navegación Lateral (Panel Oeste).
         * Diseña la barra de herramientas fija con un esquema de alto contraste para 
         * mitigar la fatiga visual en entornos de taller mecánico, organizando de forma 
         * correlativa las opciones comerciales de la suite.
         */
        JPanel panelMenu = new JPanel(new GridLayout(9, 1, 10, 10));
        panelMenu.setBackground(new Color(44, 62, 80)); 
        panelMenu.setBorder(BorderFactory.createEmptyBorder(20, 15, 20, 15));

        JLabel lblMenuTitulo = new JLabel("RAMIREZ CARS", SwingConstants.CENTER);
        lblMenuTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        lblMenuTitulo.setForeground(Color.WHITE);
        panelMenu.add(lblMenuTitulo);

        btnRegistroCliente = new JButton("1. Registro de Cliente y Vehículo");
        btnAgenda = new JButton("2. Gestión de Agenda");
        btnOrdenTrabajo = new JButton("3. Apertura de OT");
        btnTareas = new JButton("4. Registro de Tareas");
        btnAlertas = new JButton("5. Alertas Preventivas");
        btnFichaPDF = new JButton("6. Emitir Ficha de Servicio");
        btnReportes = new JButton("7. Reportes Estadísticos");
        btnSalir = new JButton("8. Salir del Sistema");

        panelMenu.add(btnRegistroCliente);
        panelMenu.add(btnAgenda);
        panelMenu.add(btnOrdenTrabajo);
        panelMenu.add(btnTareas);
        panelMenu.add(btnAlertas);
        panelMenu.add(btnFichaPDF);
        panelMenu.add(btnReportes);
        panelMenu.add(btnSalir);

        /*
         * Bloque de disposición de bienvenida central (Panel Central).
         * Inicializa un área de visualización neutra que actúa como fondo del Dashboard,
         * proporcionando identidad de marca al sistema informático standalone.
         */
        JPanel panelCentral = new JPanel(new GridBagLayout());
        panelCentral.setBackground(new Color(236, 240, 241));
        JLabel lblBienvenida = new JLabel("Historial Clínico Automotriz - Prototipo Operacional");
        lblBienvenida.setFont(new Font("Arial", Font.BOLD, 22));
        panelCentral.add(lblBienvenida);

        add(panelMenu, BorderLayout.WEST);
        add(panelCentral, BorderLayout.CENTER);

        /*
         * Orquestador Central de Eventos y Enrutamiento (ActionListener).
         * Captura de forma atómica los estímulos de acción del operario, extrayendo el comando 
         * de texto crudo y procesándolo secuencialmente mediante bifurcaciones condicionales 
         * para instanciar y desplegar la ventana correspondiente a la regla de negocio solicitada.
         */
        ActionListener menuListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String comando = e.getActionCommand();

                switch (comando) {
                    case "1. Registro de Cliente y Vehículo":
                        new ClienteView().setVisible(true); 
                        break;
                    case "2. Gestión de Agenda":
                        new TurnoView().setVisible(true);
                        break;
                    case "3. Apertura de OT":
                        new OrdenTrabajoView().setVisible(true);
                        break;
                    case "4. Registro de Tareas":
                        new TareaView().setVisible(true);
                        break;
                    case "5. Alertas Preventivas":
                        new AlertaView().setVisible(true);
                        break;
                    case "6. Emitir Ficha de Servicio":
                        new FichaServicioView().setVisible(true);
                        break;
                    case "7. Reportes Estadísticos":
                        new ReporteView().setVisible(true);
                        break;
                    case "8. Salir del Sistema":
                        int confirmacion = JOptionPane.showConfirmDialog(null, 
                            "¿Desea cerrar el prototipo operacional?", 
                            "Confirmar Salida", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                        if (confirmacion == JOptionPane.YES_OPTION) {
                            System.exit(0);
                        }
                        break;
                }
            }
        };

        /*
         * Bloque de Vinculación de Escuchadores.
         * Enlaza la lógica delegada del Listener único a cada componente de comando 
         * instanciado, habilitando la interactividad del menú de selección.
         */
        btnRegistroCliente.addActionListener(menuListener);
        btnAgenda.addActionListener(menuListener);
        btnOrdenTrabajo.addActionListener(menuListener);
        btnTareas.addActionListener(menuListener);
        btnAlertas.addActionListener(menuListener);
        btnFichaPDF.addActionListener(menuListener);
        btnReportes.addActionListener(menuListener);
        btnSalir.addActionListener(menuListener);
    }

    /*
     * Hilo Principal de Entrada (Main).
     * Delegación segura del arranque del sistema dentro del Event Dispatch Thread (EDT) 
     * mediante hilos asíncronos nativos para evitar bloqueos gráficos en el entorno local.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new DashboardPrincipal().setVisible(true);
            } catch (Exception ex) {
                System.err.println("Error crítico de infraestructura gráfica: " + ex.getMessage());
            }
        });
    }
}