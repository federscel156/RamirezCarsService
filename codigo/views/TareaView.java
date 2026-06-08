package views;

import controllers.TareaController;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/* =======================================================================================
 * CAPA DE PRESENTACIÓN (VISTA) - REGISTRO DE TAREAS E INSUMOS
 * =======================================================================================
 * CONCEPTO APLICADO: Interfaz desarrollada con controles dinámicos (JComboBox) para 
 * mitigar la carga cognitiva del operario y prevenir violaciones de integridad referencial.
 * =======================================================================================
 */
public class TareaView extends JFrame {

    private JComboBox<String> cbxOTsActivas;
    private JTextArea txtDescripcion, txtInsumos;
    private JButton btnGuardar, btnCancelar;
    private TareaController tareaController;

    public TareaView() {
        tareaController = new TareaController();

        setTitle("Registro de Tareas e Insumos");
        setSize(500, 480);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(12, 12));

        Font fuenteTitulo = new Font("Arial", Font.BOLD, 12);
        Color colorTitulo = new Color(44, 62, 80);

        /*
         * Bloque de Indexación Referencial (Panel Norte).
         * Renderiza un componente de selección alimentado dinámicamente.
         */
        JPanel panelNorte = new JPanel(new BorderLayout(10, 10));
        Border bordeBaseNorte = BorderFactory.createLineBorder(Color.LIGHT_GRAY);
        TitledBorder tituloNorte = BorderFactory.createTitledBorder(bordeBaseNorte, " [ SELECCIÓN DE VEHÍCULO / OT ] ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, fuenteTitulo, colorTitulo);
        panelNorte.setBorder(BorderFactory.createCompoundBorder(tituloNorte, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        
        cbxOTsActivas = new JComboBox<>();
        List<String> ots = tareaController.obtenerOTsActivas();
        
        if (ots.isEmpty()) {
            cbxOTsActivas.addItem("No hay vehículos con Órdenes Abiertas");
            cbxOTsActivas.setEnabled(false);
        } else {
            for (String ot : ots) {
                cbxOTsActivas.addItem(ot);
            }
        }
        panelNorte.add(cbxOTsActivas, BorderLayout.CENTER);

        /*
         * Bloque de Captura de Textos (Panel Central).
         * Áreas de texto enriquecidas para el registro de la intervención.
         */
        JPanel panelCentro = new JPanel(new GridLayout(2, 1, 10, 10));
        
        JPanel panelDesc = new JPanel(new BorderLayout());
        panelDesc.setBorder(BorderFactory.createTitledBorder(" Descripción del Trabajo Realizado: "));
        txtDescripcion = new JTextArea();
        txtDescripcion.setLineWrap(true);
        panelDesc.add(new JScrollPane(txtDescripcion), BorderLayout.CENTER);
        
        JPanel panelInsumos = new JPanel(new BorderLayout());
        panelInsumos.setBorder(BorderFactory.createTitledBorder(" Insumos / Repuestos Utilizados: "));
        txtInsumos = new JTextArea();
        txtInsumos.setLineWrap(true);
        panelInsumos.add(new JScrollPane(txtInsumos), BorderLayout.CENTER);

        panelCentro.add(panelDesc);
        panelCentro.add(panelInsumos);

        /*
         * Bloque de Comandos (Panel Sur).
         */
        JPanel panelSur = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 5));
        btnGuardar = new JButton("Registrar Tarea");
        btnGuardar.setFont(new Font("Arial", Font.BOLD, 12));
        btnGuardar.setEnabled(!ots.isEmpty()); // Bloquea si no hay OTs abiertas
        btnCancelar = new JButton("Cancelar");
        panelSur.add(btnCancelar);
        panelSur.add(btnGuardar);

        add(panelNorte, BorderLayout.NORTH);
        add(panelCentro, BorderLayout.CENTER);
        add(panelSur, BorderLayout.SOUTH);

        /*
         * Desencadenador Transaccional.
         */
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Se extrae solo el número antes del guion (Ej: "1 - Patente: ABC" -> "1")
                String seleccion = (String) cbxOTsActivas.getSelectedItem();
                String nroOTLimpio = seleccion != null ? seleccion.split(" - ")[0] : "";

                String resultado = tareaController.procesarRegistroTarea(
                    nroOTLimpio, 
                    txtDescripcion.getText(), 
                    txtInsumos.getText()
                );

                if (resultado.equals("OK")) {
                    JOptionPane.showMessageDialog(null, 
                        "La tarea y los insumos han sido indexados correctamente a la OT.", 
                        "Operación Exitosa", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, resultado, "Alerta de Validación", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        btnCancelar.addActionListener(e -> dispose());
    }
}