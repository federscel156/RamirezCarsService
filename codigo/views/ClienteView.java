package views;

import controllers.ClienteController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClienteView extends JFrame {

    // Componentes visuales encapsulados
    private JTextField txtNombre;
    private JTextField txtTelefono;
    private JTextField txtEmail;
    private JButton btnGuardar;
    private JButton btnCancelar;

    // Instancia del controlador (El puente en la arquitectura MVC)
    private ClienteController clienteController;

    // Constructor de la Vista
    public ClienteView() {
        // 1. Inicializamos el controlador
        clienteController = new ClienteController();

        // 2. Configuración de la ventana Swing
        setTitle("Módulo Registro");
        setSize(400, 250);
        setLocationRelativeTo(null); // Centrar en la pantalla
        // Usamos DISPOSE_ON_CLOSE para que al cerrar esta ventana no se cierre el menú principal
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        
        // Layout simple en formato grilla (4 filas, 2 columnas)
        setLayout(new GridLayout(4, 2, 10, 10)); 

        // 3. Inicialización de componentes
        JLabel lblNombre = new JLabel("  Nombre Completo:");
        txtNombre = new JTextField();

        JLabel lblTelefono = new JLabel("  Teléfono:");
        txtTelefono = new JTextField();

        JLabel lblEmail = new JLabel("  Email:");
        txtEmail = new JTextField();

        btnGuardar = new JButton("Guardar Cliente");
        btnCancelar = new JButton("Cancelar");

        // 4. Agregamos los componentes a la ventana
        add(lblNombre); add(txtNombre);
        add(lblTelefono); add(txtTelefono);
        add(lblEmail); add(txtEmail);
        add(btnGuardar); add(btnCancelar);

        // 5. EVENTOS: Lógica al presionar "Guardar"
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Capturamos lo escrito en pantalla
                String nombre = txtNombre.getText();
                String telefono = txtTelefono.getText();
                String email = txtEmail.getText();

                // Delegamos la lógica al Controlador (Cumpliendo el MVC)
                String resultado = clienteController.procesarRegistroCliente(nombre, telefono, email);

                // Estructura condicional para darle feedback al usuario
                if (resultado.equals("OK")) {
                    JOptionPane.showMessageDialog(null, "Cliente registrado con éxito en MySQL.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    limpiarCampos();
                    VehiculoView vistaVehiculo = new VehiculoView();
                    vistaVehiculo.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, resultado, "Error de Validación", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Lógica al presionar "Cancelar"
        btnCancelar.addActionListener(e -> dispose()); // Cierra esta ventana específica
    }

    // Método auxiliar para limpiar las cajas de texto tras guardar
    private void limpiarCampos() {
        txtNombre.setText("");
        txtTelefono.setText("");
        txtEmail.setText("");
    }
}