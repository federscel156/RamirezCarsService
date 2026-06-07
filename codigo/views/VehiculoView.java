package views;

import controllers.VehiculoController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VehiculoView extends JFrame {

    private JTextField txtPatente, txtMarca, txtModelo, txtAnio, txtVin, txtIdCliente;
    private JButton btnGuardar, btnCancelar;
    private VehiculoController vehiculoController;

    public VehiculoView() {
        vehiculoController = new VehiculoController();

        setTitle("Módulo Registro de Vehículo - CU01");
        setSize(450, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(7, 2, 10, 10));

        // Componentes
        add(new JLabel("  Patente:")); txtPatente = new JTextField(); add(txtPatente);
        add(new JLabel("  Marca:")); txtMarca = new JTextField(); add(txtMarca);
        add(new JLabel("  Modelo:")); txtModelo = new JTextField(); add(txtModelo);
        add(new JLabel("  Año:")); txtAnio = new JTextField(); add(txtAnio);
        add(new JLabel("  VIN / Chasis:")); txtVin = new JTextField(); add(txtVin);
        add(new JLabel("  ID del Cliente Dueño:")); txtIdCliente = new JTextField(); add(txtIdCliente);

        btnGuardar = new JButton("Guardar Vehículo");
        btnCancelar = new JButton("Cancelar");
        add(btnGuardar); add(btnCancelar);

        // Evento Guardar
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String resultado = vehiculoController.procesarRegistroVehiculo(
                    txtPatente.getText(), txtMarca.getText(), txtModelo.getText(),
                    txtAnio.getText(), txtVin.getText(), txtIdCliente.getText()
                );

                if (resultado.equals("OK")) {
                    JOptionPane.showMessageDialog(null, "Vehículo registrado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    dispose(); // Cierra la ventana al tener éxito
                } else {
                    JOptionPane.showMessageDialog(null, resultado, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnCancelar.addActionListener(e -> dispose());
    }
}