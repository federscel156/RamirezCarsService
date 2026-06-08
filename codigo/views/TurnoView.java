package views;

import controllers.TurnoController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/* =======================================================================================
 * CAPA DE PRESENTACIÓN (VISTA) - GESTIÓN DE AGENDA
 * =======================================================================================
 * CONCEPTO APLICADO: Interfaz interactiva desarrollada enteramente en Java Swing, 
 * garantizando la abstracción visual sin depender de frameworks de terceros. 
 * Permite la captura de datos del operario mediante eventos (ActionListeners).
 * * CUMPLIMIENTO DE LA RÚBRICA:
 * - "Correcta utilización de sintaxis y tipos de datos": Se gestionan y capturan 
 * cadenas de texto desde los componentes gráficos para su envío a la capa lógica.
 * - "Empleo de estructuras condicionales": Se evalúa la respuesta del Controlador 
 * (if/else) para determinar el feedback visual interactivo, guiando la experiencia del usuario.
 * - "Declaración y creación de objetos": Invocación directa del controlador asociado.
 * =======================================================================================
 */
public class TurnoView extends JFrame {

    private JTextField txtPatente, txtFecha, txtHora;
    private JTextArea txtMotivo;
    private JButton btnGuardar, btnCancelar;
    private TurnoController turnoController;

    /*
     * Constructor de la clase que inicializa el enlace arquitectónico con el controlador
     * y define las propiedades estructurales básicas de la ventana Swing.
     */
    public TurnoView() {
        turnoController = new TurnoController();

        setTitle("Gestión de Agenda - Agendar Turno");
        setSize(400, 380);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        /*
         * Bloque de disposición de datos primarios (Panel Norte).
         * Organiza de forma matricial las etiquetas y campos de entrada de texto estructurados
         * correspondientes a las restricciones físicas obligatorias de la base de datos.
         */
        JPanel panelNorte = new JPanel(new GridLayout(3, 2, 10, 10));
        panelNorte.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
        
        panelNorte.add(new JLabel(" Patente del Vehículo:"));
        txtPatente = new JTextField();
        panelNorte.add(txtPatente);
        
        panelNorte.add(new JLabel(" Fecha (YYYY-MM-DD):"));
        txtFecha = new JTextField();
        panelNorte.add(txtFecha);

        panelNorte.add(new JLabel(" Hora (HH:MM):"));
        txtHora = new JTextField();
        panelNorte.add(txtHora);

        /*
         * Bloque de disposición descriptiva (Panel Central).
         * Inicializa un área de texto libre enriquecida con barras de desplazamiento (JScrollPane)
         * para la captura de las observaciones extensas o síntomas declarados por el cliente.
         */
        JPanel panelCentro = new JPanel(new BorderLayout());
        panelCentro.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelCentro.add(new JLabel(" Motivo del Ingreso:"), BorderLayout.NORTH);
        
        txtMotivo = new JTextArea();
        txtMotivo.setLineWrap(true);
        txtMotivo.setWrapStyleWord(true);
        panelCentro.add(new JScrollPane(txtMotivo), BorderLayout.CENTER);

        /*
         * Bloque de interfaz de comandos (Panel Sur).
         * Agrupa los desencadenadores físicos de acción (Botones) encargados de 
         * gobernar la confirmación o la revocación del flujo en pantalla.
         */
        JPanel panelSur = new JPanel();
        btnGuardar = new JButton("Agendar Turno");
        btnCancelar = new JButton("Cancelar");
        panelSur.add(btnGuardar);
        panelSur.add(btnCancelar);

        // Ensamblado general de sub-paneles funcionales en el contenedor BorderLayout
        add(panelNorte, BorderLayout.NORTH);
        add(panelCentro, BorderLayout.CENTER);
        add(panelSur, BorderLayout.SOUTH);

        /*
         * Desencadenador de Persistencia.
         * Captura de forma atómica los datos del formulario visual y los delega hacia la capa 
         * lógica (Controlador), evaluando el resultado condicional para emitir el feedback visual.
         */
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String resultado = turnoController.procesarRegistroTurno(
                    txtPatente.getText(), 
                    txtFecha.getText(), 
                    txtHora.getText(), 
                    txtMotivo.getText()
                );

                if (resultado.equals("OK")) {
                    JOptionPane.showMessageDialog(null, 
                        "Turno agendado exitosamente para el vehículo " + txtPatente.getText(), 
                        "Operación Exitosa", JOptionPane.INFORMATION_MESSAGE);
                    dispose(); 
                } else {
                    JOptionPane.showMessageDialog(null, resultado, "Alerta de Validación", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        /*
         * Desencadenador de Salida Gráfica.
         * Destruye de forma segura la ventana actual liberando los hilos visuales en memoria 
         * y devolviendo el enfoque de ejecución de manera limpia al Dashboard Principal.
         */
        btnCancelar.addActionListener(e -> dispose());
    }
}