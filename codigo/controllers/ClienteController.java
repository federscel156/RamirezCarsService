package controllers;

import dao.ClienteDAO;
import models.Cliente;
import java.util.Date;

public class ClienteController {
    
    private ClienteDAO clienteDAO;

    public ClienteController() {
        this.clienteDAO = new ClienteDAO();
    }

    public String procesarRegistroCliente(String nombre, String telefono, String email) {
        
        if (nombre == null || nombre.trim().isEmpty()) {
            return "Error: El nombre completo es obligatorio.";
        }
        if (telefono == null || telefono.trim().isEmpty()) {
            return "Error: El teléfono es obligatorio.";
        }

        try {
            Cliente nuevoCliente = new Cliente(0, nombre, telefono, email, new Date());
            boolean exito = clienteDAO.registrarCliente(nuevoCliente);

            if (exito) {
                System.out.println("Registrado exitosamente: " + nuevoCliente.obtenerDetalleRol());
                return "OK";
            } else {
                return "Error: No se pudo guardar en la base de datos.";
            }

        } catch (Exception e) {
            return "Error inesperado en el controlador: " + e.getMessage();
        }
    }
}