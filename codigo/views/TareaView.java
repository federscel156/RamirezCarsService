package views;

import controllers.TareaController;
import javax.swing.*;
import java.awt.*;

public class TareaView extends JFrame {
    private JTextField txtNroOT;
    private JTextArea txtDescripcion, txtInsumos;
    private TareaController tareaController;

    public TareaView() {
        tareaController = new TareaController();
        setTitle("Registro de Tareas");
        setSize(450, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel panelNorte = new JPanel(new GridLayout(1, 2));
        panelNorte.add(new JLabel(" Nro de OT:"));
        txtNroOT = new JTextField();
        panelNorte.add(txtNroOT);

        JPanel panelCentro = new JPanel(new GridLayout(2, 1, 5, 5));
        
        JPanel panelDesc = new JPanel(new BorderLayout());
        panelDesc.add(new JLabel(" Descripción del Trabajo:"), BorderLayout.NORTH);
        txtDescripcion = new JTextArea();
        panelDesc.add(new JScrollPane(txtDescripcion), BorderLayout.CENTER);

        JPanel panelInsumos = new JPanel(new BorderLayout());
        panelInsumos.add(new JLabel(" Insumos Utilizados:"), BorderLayout.NORTH);
        txtInsumos = new JTextArea();
        panelInsumos.add(new JScrollPane(txtInsumos), BorderLayout.CENTER);

        panelCentro.add(panelDesc);
        panelCentro.add(panelInsumos);

        JPanel panelSur = new JPanel();
        JButton btnGuardar = new JButton("Registrar Tarea");
        btnGuardar.addActionListener(e -> {
            String res = tareaController.procesarRegistroTarea(txtNroOT.getText(), txtDescripcion.getText(), txtInsumos.getText());
            if (res.equals("OK")) {
                JOptionPane.showMessageDialog(null, "Tarea registrada exitosamente.");
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, res, "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        panelSur.add(btnGuardar);

        add(panelNorte, BorderLayout.NORTH);
        add(panelCentro, BorderLayout.CENTER);
        add(panelSur, BorderLayout.SOUTH);
    }
}