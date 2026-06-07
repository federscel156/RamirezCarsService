package models;
import java.util.Date;

public class OrdenTrabajo {
    private int nroOT;
    private String patente; // FK
    private Date fecha;
    private int kilometraje;
    private String diagnosticoInicial;
    private String estado;
    private Date fechaFinalizacion;

    public OrdenTrabajo(int nroOT, String patente, Date fecha, int kilometraje, String diagnosticoInicial, String estado, Date fechaFinalizacion) {
        this.nroOT = nroOT;
        this.patente = patente;
        this.fecha = fecha;
        this.kilometraje = kilometraje;
        this.diagnosticoInicial = diagnosticoInicial;
        this.estado = estado;
        this.fechaFinalizacion = fechaFinalizacion;
    }

    // Getters
    public int getNroOT() { return nroOT; }
    public String getPatente() { return patente; }
    public Date getFecha() { return fecha; }
    public int getKilometraje() { return kilometraje; }
    public String getDiagnosticoInicial() { return diagnosticoInicial; }
    public String getEstado() { return estado; }
    public Date getFechaFinalizacion() { return fechaFinalizacion; }
}