package models;
import java.util.Date;

public class TareaRealizada {
    private int idTarea;
    private int nroOT; // FK
    private String descripcionTrabajo;
    private String insumosTexto;
    private Date fechaRegistro;

    public TareaRealizada(int idTarea, int nroOT, String descripcionTrabajo, String insumosTexto, Date fechaRegistro) {
        this.idTarea = idTarea;
        this.nroOT = nroOT;
        this.descripcionTrabajo = descripcionTrabajo;
        this.insumosTexto = insumosTexto;
        this.fechaRegistro = fechaRegistro;
    }

    public int getIdTarea() { return idTarea; }
    public int getNroOT() { return nroOT; }
    public String getDescripcionTrabajo() { return descripcionTrabajo; }
    public String getInsumosTexto() { return insumosTexto; }
    public Date getFechaRegistro() { return fechaRegistro; }
}