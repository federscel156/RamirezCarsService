package dao;

import models.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

/* =======================================================================================
 * CAPA DE ACCESO A DATOS (PERSISTENCIA - CLIENTE)
 * =======================================================================================
 * CONCEPTO APLICADO: Componente DAO transaccional. Implementa JDBC nativo para aislar las 
 * sentencias DML de la aplicación. Aplica la recuperación de claves primarias generadas 
 * en tiempo de ejecución para dar soporte al encadenamiento relacional del negocio.
 * =======================================================================================
 */
public class ClienteDAO {

    public int registrarCliente(Cliente cliente) {
        String sql = "INSERT INTO Cliente (nombre_completo, telefono, email, fecha_registro) VALUES (?, ?, ?, ?)";
        
        // Configuramos el PreparedStatement para retornar las llaves autogeneradas por MySQL
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, cliente.getNombreCompleto());
            stmt.setString(2, cliente.getTelefono());
            stmt.setString(3, cliente.getEmail());
            stmt.setDate(4, new java.sql.Date(cliente.getFechaRegistro().getTime()));
            
            int filasAfectadas = stmt.executeUpdate();
            
            if (filasAfectadas > 0) {
                // Recuperamos el puntero del id_cliente creado por el motor relacional
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getInt(1); // Retorna el entero autoincremental
                    }
                }
            }
            return -1;

        } catch (SQLException e) {
            System.err.println("Error en ClienteDAO al registrar: " + e.getMessage());
            return -1;
        }
    }
}