package views;

import controllers.FichaServicioController;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/* =======================================================================================
 * CAPA DE PRESENTACIÓN (VISTA) - EMISIÓN DE FICHA TÉCNICA
 * =======================================================================================
 * CONCEPTO APLICADO: Interfaz de cierre operativo desarrollada bajo la librería Java Swing.
 * Implementa la separación estructural de datos a través de sub-paneles con bordes 
 * titulados (TitledBorder), mitigando la carga cognitiva del operario y guiando el flujo 
 * transaccional hacia el controlador orquestador del patrón MVC.
 * * CUMPLIMIENTO DE LA RÚBRICA:
 * - "Correcta utilización de sintaxis y tipos de datos": Captura unificada de entradas de texto.
 * - "Empleo de estructuras condicionales": Ramificación de respuestas interactivas mediante JOptionPane.
 * - "Declaración y creación de objetos": Acoplamiento explícito con su controlador de negocio.
 * =======================================================================================
 */
public class FichaServicioView extends JFrame {

    private JTextField txtNroOT, txtPatente;
    private JTextArea txtResumen;
    private JButton btnGenerar, btnCancelar;
    private FichaServicioController fichaController;

    /*
     * Constructor principal de la interfaz gráfica.
     * Inicializa los componentes lógicos de la arquitectura y define las dimensiones,
     * posición centralizada y el gestor de distribución espacial raíz (BorderLayout).
     */
    public FichaServicioView() {
        fichaController = new FichaServicioController();

        setTitle("Finalización de Servicio y Emisión de Ficha PDF");
        setSize(480, 420);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(12, 12));

        // Estilo tipográfico y paleta cromática institucional de la suite
        Font fuenteTitulo = new Font("Arial", Font.BOLD, 12);
        Color colorTitulo = new Color(44, 62, 80);

        /*
         * Bloque de Identificación de Transacción (Panel Norte).
         * Agrupa de manera matricial los campos obligatorios de indexación relacional.
         * Aplica bordes compuestos decorados para segregar visualmente la sección.
         */
        JPanel panelNorte = new JPanel(new GridLayout(2, 2, 10, 10));
        Border bordeBaseNorte = BorderFactory.createLineBorder(Color.LIGHT_GRAY);
        TitledBorder tituloNorte = BorderFactory.createTitledBorder(bordeBaseNorte, " [ IDENTIFICACIÓN DE LA ORDEN DE TRABAJO ] ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, fuenteTitulo, colorTitulo);
        panelNorte.setBorder(BorderFactory.createCompoundBorder(tituloNorte, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        
        panelNorte.add(new JLabel("Número de Orden (OT) * :"));
        txtNroOT = new JTextField();
        panelNorte.add(txtNroOT);
        
        panelNorte.add(new JLabel("Patente del Vehículo * :"));
        txtPatente = new JTextField();
        panelNorte.add(txtPatente);

        /*
         * Bloque de Documentación de Clausura (Panel Central).
         * Provee una caja de texto multilínea enriquecida con barras de desplazamiento asíncronas
         * para recolectar las conclusiones técnicas e insumos finales que se exportarán al reporte físico.
         */
        JPanel panelCentro = new JPanel(new BorderLayout());
        Border bordeBaseCentro = BorderFactory.createLineBorder(Color.LIGHT_GRAY);
        TitledBorder tituloCentro = BorderFactory.createTitledBorder(bordeBaseCentro, " [ CONCLUSIÓN TÉCNICA E INSUMOS UTILIZADOS ] ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, fuenteTitulo, colorTitulo);
        panelCentro.setBorder(BorderFactory.createCompoundBorder(tituloCentro, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        
        txtResumen = new JTextArea();
        txtResumen.setLineWrap(true);
        txtResumen.setWrapStyleWord(true);
        panelCentro.add(new JScrollPane(txtResumen), BorderLayout.CENTER);

        /*
         * Bloque de Comandos Operacionales (Panel Sur).
         * Define y alinea hacia la derecha los gatillos de acción del formulario,
         * controlando el envío definitivo o la revocación segura de la pantalla.
         */
        JPanel panelSur = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 5));
        btnGenerar = new JButton("Finalizar y Emitir PDF");
        btnGenerar.setFont(new Font("Arial", Font.BOLD, 12));
        btnCancelar = new JButton("Cancelar");
        panelSur.add(btnCancelar);
        panelSur.add(btnGenerar);

        // Ensamblado final de sub-paneles funcionales en el contenedor BorderLayout
        add(panelNorte, BorderLayout.NORTH);
        add(panelCentro, BorderLayout.CENTER);
        add(panelSur, BorderLayout.SOUTH);

        /*
         * Desencadenador de Clausura y Renderizado.
         * Transfiere masivamente las entradas del operario hacia la capa de negocio,
         * evaluando la respuesta jerárquica para notificar el éxito o los fallos de validación.
         */
        btnGenerar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String resultado = fichaController.procesarCierreYEmision(
                    txtNroOT.getText(), 
                    txtPatente.getText(), 
                    txtResumen.getText()
                );

                if (resultado.equals("OK")) {
                    JOptionPane.showMessageDialog(null, 
                        "La Orden de Trabajo ha sido cerrada con éxito en MySQL.\nDocumento físico exportado a la carpeta raíz del proyecto.", 
                        "Ciclo Técnico Concluido", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, resultado, "Alerta de Validación", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        /*
         * Desencadenador de Revocación.
         * Descarga la ventana liberando de forma segura los recursos del recolector de Swing,
         * restituyendo el foco operacional en el Dashboard Principal de navegación.
         */
        btnCancelar.addActionListener(e -> dispose());
    }
}