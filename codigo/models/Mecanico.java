package models;

public class Mecanico extends Persona {
    private int idMecanico;
    private String especialidad;

    public Mecanico(int idMecanico, String nombreCompleto, String telefono, String email, String especialidad) {
        super(nombreCompleto, telefono, email);
        this.idMecanico = idMecanico;
        this.especialidad = especialidad;
    }

    // POLIMORFISMO (Fase 3): La misma función actúa distinto para el Mecánico
    @Override
    public String obtenerDetalleRol() {
        return "Rol: MECÁNICO - Especialidad: " + this.especialidad + " | Operario: " + this.nombreCompleto;
    }

    public int getIdMecanico() { return idMecanico; }
    public void setIdMecanico(int idMecanico) { this.idMecanico = idMecanico; }
    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }
}