package models;
import java.util.Date;

// 4. HERENCIA: Cliente extiende de Persona
public class Cliente extends Persona {
    private int idCliente;
    private Date fechaRegistro;

    public Cliente(int idCliente, String nombreCompleto, String telefono, String email, Date fechaRegistro) {
        super(nombreCompleto, telefono, email); // Llama al constructor de la clase padre
        this.idCliente = idCliente;
        this.fechaRegistro = fechaRegistro;
    }

    // 5. POLIMORFISMO (Fase 2): Sobreescritura específica para el Cliente
    @Override
    public String obtenerDetalleRol() {
        return "Rol: CLIENTE - ID: " + this.idCliente + " | Nombre: " + this.nombreCompleto;
    }

    // Getters y Setters propios
    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }
    public Date getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(Date fechaRegistro) { this.fechaRegistro = fechaRegistro; }
}