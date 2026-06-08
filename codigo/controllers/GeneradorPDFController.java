package controllers;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;

/* =======================================================================================
 * CAPA LÓGICA DE NEGOCIO (CONTROLADOR DE INFRAESTRUCTURA) - CU06
 * =======================================================================================
 * CONCEPTO APLICADO: Controlador especializado en la gestión de salidas (Output Controller).
 * Mantiene la coherencia arquitectónica del patrón MVC definido en el PUD alojándose en 
 * la capa de controladores. Encapsula la complejidad de la librería externa iText para 
 * generar dinámicamente el comprobante físico (PDF) sin acoplar la lógica a la vista.
 * =======================================================================================
 */
public class GeneradorPDFController {

    /*
     * Función de Renderizado de Documento Físico.
     * Utiliza los flujos de entrada/salida nativos de Java (FileOutputStream) combinados 
     * con la librería iText para construir y persistir el archivo PDF en el disco local,
     * materializando la historia clínica del vehículo.
     */
    public boolean generarFicha(int nroOT, String patente, String diagnostico) {
        Document documento = new Document();
        try {
            // Se genera el archivo físico en la raíz del proyecto para fácil acceso
            PdfWriter.getInstance(documento, new FileOutputStream("Ficha_Tecnica_OT_" + nroOT + ".pdf"));
            documento.open();
            
            // Renderizado estructural del contenido del documento
            documento.add(new Paragraph("=================================================="));
            documento.add(new Paragraph("       RAMIREZ CARS SERVICE - FICHA TÉCNICA       "));
            documento.add(new Paragraph("=================================================="));
            documento.add(new Paragraph("\n"));
            documento.add(new Paragraph("Número de Orden de Trabajo: " + nroOT));
            documento.add(new Paragraph("Patente del Vehículo: " + patente));
            documento.add(new Paragraph("Fecha de Emisión: " + new java.util.Date().toString()));
            documento.add(new Paragraph("\n"));
            documento.add(new Paragraph("Conclusión Técnica e Insumos:"));
            documento.add(new Paragraph(diagnostico));
            documento.add(new Paragraph("\n"));
            documento.add(new Paragraph("Estado de la Orden: FINALIZADA"));
            documento.add(new Paragraph("=================================================="));
            
            documento.close();
            return true;
            
        } catch (Exception e) {
            System.err.println("Excepción de I/O al renderizar el documento PDF: " + e.getMessage());
            return false;
        }
    }
}