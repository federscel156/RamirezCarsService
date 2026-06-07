package views;

import controllers.OrdenTrabajoController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrdenTrabajoView extends JFrame {

    private JTextField txtPatente, txtKilometraje;
    private JTextArea txtDiagnostico;
    private JButton btnGuardar, btnCancelar;
    private OrdenTrabajoController otController;

    public OrdenTrabajoView() {
        // Inicialización del controlador (MVC)
        otController = new OrdenTrabajoController();

        // Configuración de la ventana
        setTitle("Apertura de Orden de Trabajo");
        setSize(450, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Panel Superior (Datos cortos)
        JPanel panelNorte = new JPanel(new GridLayout(2, 2, 10, 10));
        panelNorte.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
        
        panelNorte.add(new JLabel("Patente del Vehículo (Existente):"));
        txtPatente = new JTextField();
        panelNorte.add(txtPatente);
        
        panelNorte.add(new JLabel("Kilometraje Actual:"));
        txtKilometraje = new JTextField();
        panelNorte.add(txtKilometraje);

        // Panel Central (Área de texto para el diagnóstico)
        JPanel panelCentro = new JPanel(new BorderLayout());
        panelCentro.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelCentro.add(new JLabel("Diagnóstico Inicial / Motivo de Ingreso:"), BorderLayout.NORTH);
        
        txtDiagnostico = new JTextArea();
        txtDiagnostico.setLineWrap(true);
        txtDiagnostico.setWrapStyleWord(true);
        // Se agrega un Scroll para que el texto largo no rompa la interfaz
        panelCentro.add(new JScrollPane(txtDiagnostico), BorderLayout.CENTER);

        // Panel Inferior (Botones)
        JPanel panelSur = new JPanel();
        btnGuardar = new JButton("Abrir Orden");
        btnCancelar = new JButton("Cancelar");
        panelSur.add(btnGuardar);
        panelSur.add(btnCancelar);

        // Armado final del Frame
        add(panelNorte, BorderLayout.NORTH);
        add(panelCentro, BorderLayout.CENTER);
        add(panelSur, BorderLayout.SOUTH);

        // EVENTOS
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Pasamos los datos a la capa lógica
                String resultado = otController.procesarAperturaOT(
                    txtPatente.getText(), txtKilometraje.getText(), txtDiagnostico.getText()
                );

                if (resultado.equals("OK")) {
                    JOptionPane.showMessageDialog(null, "Orden de Trabajo abierta y guardada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    dispose(); // Cierra la pantalla
                } else {
                    JOptionPane.showMessageDialog(null, resultado, "Error de Validación/BD", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnCancelar.addActionListener(e -> dispose());
    }
}