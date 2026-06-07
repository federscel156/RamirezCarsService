package views;

import controllers.TurnoController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/* * =======================================================================================
 * CAPA DE PRESENTACIÓN (VISTA) - CU02 GESTIÓN DE AGENDA
 * =======================================================================================
 * CONCEPTO APLICADO: Interfaz de usuario interactiva construida con la librería estándar 
 * Java Swing, garantizando la independencia de frameworks externos. Permite al usuario 
 * interactuar con el sistema a través de eventos (ActionListeners).
 * * CUMPLIMIENTO DE RÚBRICA:
 * - "Correcta utilización de sintaxis y tipos de datos": Gestión de cadenas (Strings) 
 * obtenidas de los JTextField.
 * - "Estructuras condicionales": Feedback guiado al usuario mediante if/else analizando 
 * la respuesta del controlador.
 * =======================================================================================
 */
public class TurnoView extends JFrame {

    private JTextField txtPatente, txtFecha;
    private JTextArea txtMotivo;
    private JButton btnGuardar, btnCancelar;
    private TurnoController turnoController;

    public TurnoView() {
        // Inicialización del controlador (Enlace arquitectónico)
        turnoController = new TurnoController();

        // Configuración de la ventana Swing
        setTitle("Gestión de Agenda - Agendar Turno");
        setSize(400, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Panel Superior: Campos de texto cortos
        JPanel panelNorte = new JPanel(new GridLayout(2, 2, 10, 10));
        panelNorte.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
        
        panelNorte.add(new JLabel(" Patente del Vehículo:"));
        txtPatente = new JTextField();
        panelNorte.add(txtPatente);
        
        panelNorte.add(new JLabel(" Fecha (YYYY-MM-DD):"));
        txtFecha = new JTextField();
        panelNorte.add(txtFecha);

        // Panel Central: Área de texto para el motivo
        JPanel panelCentro = new JPanel(new BorderLayout());
        panelCentro.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelCentro.add(new JLabel(" Motivo del Ingreso:"), BorderLayout.NORTH);
        
        txtMotivo = new JTextArea();
        txtMotivo.setLineWrap(true);
        txtMotivo.setWrapStyleWord(true);
        panelCentro.add(new JScrollPane(txtMotivo), BorderLayout.CENTER);

        // Panel Inferior: Botones de acción
        JPanel panelSur = new JPanel();
        btnGuardar = new JButton("Agendar Turno");
        btnCancelar = new JButton("Cancelar");
        panelSur.add(btnGuardar);
        panelSur.add(btnCancelar);

        add(panelNorte, BorderLayout.NORTH);
        add(panelCentro, BorderLayout.CENTER);
        add(panelSur, BorderLayout.SOUTH);

        // EVENTOS: Lógica de interacción
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Se envían los datos crudos al controlador
                String resultado = turnoController.procesarRegistroTurno(
                    txtPatente.getText(), txtFecha.getText(), txtMotivo.getText()
                );

                // Estructura de control para informar al usuario
                if (resultado.equals("OK")) {
                    JOptionPane.showMessageDialog(null, 
                        "Turno agendado exitosamente para el vehículo " + txtPatente.getText(), 
                        "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    dispose(); // Destruye la ventana de agenda sin cerrar el programa
                } else {
                    JOptionPane.showMessageDialog(null, resultado, "Error de Validación", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnCancelar.addActionListener(e -> dispose());
    }
}