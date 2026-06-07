package dao;

import models.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClienteDAO {

    // Método para insertar un cliente en la BD
    public boolean registrarCliente(Cliente cliente) {
        String sql = "INSERT INTO Cliente (nombre_completo, telefono, email, fecha_registro) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, cliente.getNombreCompleto());
            stmt.setString(2, cliente.getTelefono());
            stmt.setString(3, cliente.getEmail());
            
            java.sql.Date sqlDate = new java.sql.Date(cliente.getFechaRegistro().getTime());
            stmt.setDate(4, sqlDate);
            
            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.err.println("Error en ClienteDAO al registrar: " + e.getMessage());
            return false;
        }
    }
}