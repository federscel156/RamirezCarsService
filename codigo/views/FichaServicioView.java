package views;

import controllers.FichaServicioController;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/* =======================================================================================
 * CAPA DE PRESENTACIÓN (VISTA) - EMISIÓN DE FICHA TÉCNICA
 * =======================================================================================
 * CONCEPTO APLICADO: Interfaz de cierre operativo desarrollada bajo la librería Java Swing.
 * Adopta controles dinámicos avanzados (JComboBox) para mitigar la carga cognitiva del 
 * operario y prevenir fallas de consistencia al clausurar la historia clínica automotriz.
 * =======================================================================================
 */
public class FichaServicioView extends JFrame {

    private JComboBox<String> cbxOTsActivas;
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
        setSize(500, 420);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(12, 12));

        Font fuenteTitulo = new Font("Arial", Font.BOLD, 12);
        Color colorTitulo = new Color(44, 62, 80);

        /*
         * Bloque de Identificación de Transacción (Panel Norte).
         * Renderiza un control selectivo alimentado dinámicamente con las órdenes abiertas,
         * simplificando la UX al evitar entradas manuales redundantes.
         */
        JPanel panelNorte = new JPanel(new BorderLayout(10, 10));
        Border bordeBaseNorte = BorderFactory.createLineBorder(Color.LIGHT_GRAY);
        TitledBorder tituloNorte = BorderFactory.createTitledBorder(bordeBaseNorte, " [ SELECCIÓN DE ORDEN ACTIVA A FINALIZAR ] ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, fuenteTitulo, colorTitulo);
        panelNorte.setBorder(BorderFactory.createCompoundBorder(tituloNorte, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        
        cbxOTsActivas = new JComboBox<>();
        List<String> ots = fichaController.obtenerOTsActivas();
        
        if (ots.isEmpty()) {
            cbxOTsActivas.addItem("No hay vehículos con Órdenes Abiertas en el taller");
            cbxOTsActivas.setEnabled(false);
        } else {
            for (String ot : ots) {
                cbxOTsActivas.addItem(ot);
            }
        }
        panelNorte.add(cbxOTsActivas, BorderLayout.CENTER);

        /*
         * Bloque de Documentación de Clausura (Panel Central).
         * Provee una caja de texto multilínea para recolectar las conclusiones técnicas 
         * e insumos finales que se exportarán al reporte físico iText.
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
         */
        JPanel panelSur = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 5));
        btnGenerar = new JButton("Finalizar y Emitir PDF");
        btnGenerar.setFont(new Font("Arial", Font.BOLD, 12));
        btnGenerar.setEnabled(!ots.isEmpty());
        btnCancelar = new JButton("Cancelar");
        panelSur.add(btnCancelar);
        panelSur.add(btnGenerar);

        add(panelNorte, BorderLayout.NORTH);
        add(panelCentro, BorderLayout.CENTER);
        add(panelSur, BorderLayout.SOUTH);

        /*
         * Desencadenador de Clausura y Renderizado.
         * Descompone la cadena seleccionada ("Nro - Patente: XXX") para procesar el cierre.
         */
        btnGenerar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String seleccion = (String) cbxOTsActivas.getSelectedItem();
                
                String nroOTLimpio = "";
                String patenteLimpia = "";
                
                if (seleccion != null && seleccion.contains(" - Patente: ")) {
                    String[] partes = seleccion.split(" - Patente: ");
                    nroOTLimpio = partes[0].trim();
                    patenteLimpia = partes.length > 1 ? partes[1].trim() : "";
                }

                String resultado = fichaController.procesarCierreYEmision(
                    nroOTLimpio, 
                    patenteLimpia, 
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

        btnCancelar.addActionListener(e -> dispose());
    }
}