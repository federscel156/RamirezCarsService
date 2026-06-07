package models;
import java.util.Date;

/*
 * CONCEPTO APLICADO: Entidad del modelo de dominio. 
 * Cumple con el ENCAPSULAMIENTO mediante el uso de atributos privados y 
 * la exposición de constructores y métodos Getters. Representa una reserva en la agenda.
 */
public class Turno {
    private String patente;
    private Date fecha;
    private String motivo;

    public Turno(String patente, Date fecha, String motivo) {
        this.patente = patente;
        this.fecha = fecha;
        this.motivo = motivo;
    }

    public String getPatente() { return patente; }
    public Date getFecha() { return fecha; }
    public String getMotivo() { return motivo; }
}