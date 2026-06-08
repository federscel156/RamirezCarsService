package views;

import controllers.ClienteController;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/* =======================================================================================
 * CAPA DE PRESENTACIÓN (VISTA) - REGISTRO UNIFICADO (MEJORA UX VISUAL)
 * =======================================================================================
 * CONCEPTO APLICADO: Interfaz de usuario avanzada bajo Java Swing. Se implementa el uso 
 * de sub-paneles (JPanels) con bordes titulados (TitledBorder) para lograr una segregación 
 * visual clara y profesional de las secciones de datos, resolviendo problemas de usabilidad.
 * =======================================================================================
 */
public class ClienteView extends JFrame {

    // Componentes encapsulados
    private JTextField txtNombre, txtTelefono, txtEmail;
    private JTextField txtPatente, txtMarca, txtModelo, txtAnio, txtVin;
    private JButton btnGuardar, btnCancelar;
    private ClienteController clienteController;

    public ClienteView() {
        clienteController = new ClienteController();

        setTitle("Alta Unificada de Expediente Clìnico");
        setSize(520, 580);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        // Usamos GridBagLayout en el contenedor principal para controlar márgenes externos
        setLayout(new GridBagLayout());
        GridBagConstraints gbcMain = new GridBagConstraints();
        gbcMain.insets = new Insets(10, 10, 10, 10); // Margen externo de la ventana
        gbcMain.fill = GridBagConstraints.HORIZONTAL;
        gbcMain.weightx = 1.0;

        // Definimos un estilo de fuente y color para los títulos de las secciones
        Font fuenteTitulo = new Font("Arial", Font.BOLD, 13);
        Color colorTitulo = new Color(44, 62, 80); // Azul oscuro del dashboard

        // ===================================================================================
        // [ SECCIÓN 1: DATOS DEL CLIENTE ] - Panel con Borde Titulado
        // ===================================================================================
        JPanel panelCliente = new JPanel(new GridLayout(3, 2, 10, 10));
        
        // Creamos el borde titulado con estilo
        Border bordeBaseCliente = BorderFactory.createLineBorder(Color.LIGHT_GRAY);
        TitledBorder tituloCliente = BorderFactory.createTitledBorder(bordeBaseCliente, " [ DATOS DEL PROPIETARIO ] ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, fuenteTitulo, colorTitulo);
        panelCliente.setBorder(BorderFactory.createCompoundBorder(tituloCliente, BorderFactory.createEmptyBorder(10, 10, 10, 10))); // Margen interno
        
        panelCliente.add(new JLabel("Nombre Completo * :")); txtNombre = new JTextField(); panelCliente.add(txtNombre);
        panelCliente.add(new JLabel("Teléfono * :")); txtTelefono = new JTextField(); panelCliente.add(txtTelefono);
        panelCliente.add(new JLabel("Email :")); txtEmail = new JTextField(); panelCliente.add(txtEmail);

        // Agregamos el panel cliente al frame principal
        gbcMain.gridy = 0;
        add(panelCliente, gbcMain);

        // ===================================================================================
        // [ SECCIÓN 2: DATOS DEL VEHÍCULO ] - Panel con Borde Titulado
        // ===================================================================================
        JPanel panelVehiculo = new JPanel(new GridLayout(5, 2, 10, 10));
        
        Border bordeBaseVehiculo = BorderFactory.createLineBorder(Color.LIGHT_GRAY);
        TitledBorder tituloVehiculo = BorderFactory.createTitledBorder(bordeBaseVehiculo, " [ DATOS DEL RODADO ] ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, fuenteTitulo, colorTitulo);
        panelVehiculo.setBorder(BorderFactory.createCompoundBorder(tituloVehiculo, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        
        panelVehiculo.add(new JLabel("Patente * :")); txtPatente = new JTextField(); panelVehiculo.add(txtPatente);
        panelVehiculo.add(new JLabel("Marca * :")); txtMarca = new JTextField(); panelVehiculo.add(txtMarca);
        panelVehiculo.add(new JLabel("Modelo * :")); txtModelo = new JTextField(); panelVehiculo.add(txtModelo);
        panelVehiculo.add(new JLabel("Año * :")); txtAnio = new JTextField(); panelVehiculo.add(txtAnio);
        panelVehiculo.add(new JLabel("VIN / Chasis :")); txtVin = new JTextField(); panelVehiculo.add(txtVin);

        gbcMain.gridy = 1;
        gbcMain.insets = new Insets(20, 10, 10, 10); // Un poco más de espacio superior
        add(panelVehiculo, gbcMain);

        // ===================================================================================
        // [ SECCIÓN 3: BOTONES DE ACCIÓN ]
        // ===================================================================================
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        btnGuardar = new JButton("Confirmar Alta");
        btnGuardar.setFont(new Font("Arial", Font.BOLD, 12));
        btnCancelar = new JButton("Cancelar");
        
        panelBotones.add(btnCancelar);
        panelBotones.add(btnGuardar);

        gbcMain.gridy = 2;
        gbcMain.insets = new Insets(25, 10, 10, 10); // Espacio antes de los botones
        add(panelBotones, gbcMain);

        // --- LÓGICA DE EVENTOS (Sin cambios) ---
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String resultado = clienteController.procesarRegistro(
                    txtNombre.getText(), txtTelefono.getText(), txtEmail.getText(),
                    txtPatente.getText(), txtMarca.getText(), txtModelo.getText(),
                    txtAnio.getText(), txtVin.getText()
                );

                if (resultado.equals("OK")) {
                    JOptionPane.showMessageDialog(null, 
                        "Expediente inicializado con éxito.\nDatos persistidos en MySQL.", 
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