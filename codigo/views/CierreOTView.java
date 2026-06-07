package views;

import controllers.OrdenTrabajoController;
import javax.swing.*;
import java.awt.*;

public class CierreOTView extends JFrame {
    private JTextField txtNroOT;
    private OrdenTrabajoController otController;

    public CierreOTView() {
        otController = new OrdenTrabajoController();
        setTitle("Cierre de Orden");
        setSize(350, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(3, 1, 10, 10));

        add(new JLabel(" Ingrese el Nro de OT a Cerrar:", SwingConstants.CENTER));
        txtNroOT = new JTextField();
        add(txtNroOT);

        JButton btnCerrar = new JButton("Cerrar Orden");
        btnCerrar.addActionListener(e -> {
            String res = otController.procesarCierreOT(txtNroOT.getText());
            if (res.equals("OK")) {
                JOptionPane.showMessageDialog(null, "Orden cerrada exitosamente en MySQL.");
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, res, "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        add(btnCerrar);
    }
}