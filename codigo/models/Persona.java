package models;

// 1. ABSTRACCIÓN: Clase abstracta que no puede ser instanciada por sí sola.
public abstract class Persona {
    
    // 2. ENCAPSULAMIENTO: Atributos protegidos (visibles para las hijas) o privados.
    protected String nombreCompleto;
    protected String telefono;
    protected String email;

    // Constructor
    public Persona(String nombreCompleto, String telefono, String email) {
        this.nombreCompleto = nombreCompleto;
        this.telefono = telefono;
        this.email = email;
    }

    // 3. POLIMORFISMO (Fase 1): Declaración del método abstracto a sobreescribir
    public abstract String obtenerDetalleRol();

    // Getters y Setters (Encapsulamiento)
    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}