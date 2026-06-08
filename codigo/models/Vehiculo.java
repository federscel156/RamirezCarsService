package models;
import java.util.Date;

public class Vehiculo {
    // Encapsulamiento
    private String patente;
    private String marca;
    private String modelo;
    private int anio;
    private String vin;
    private int idCliente; // Foreign Key
    private Date fechaRegistro;

    // Uso de constructores (Requisito de la rúbrica)
    public Vehiculo(String patente, String marca, String modelo, int anio, String vin, int idCliente, Date fechaRegistro) {
        this.patente = patente;
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.vin = vin;
        this.idCliente = idCliente;
        this.fechaRegistro = fechaRegistro;
    }

    // Getters
    public String getPatente() { return patente; }
    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }
    public int getAnio() { return anio; }
    public String getVin() { return vin; }
    public int getIdCliente() { return idCliente; }
    public Date getFechaRegistro() { return fechaRegistro; }
}