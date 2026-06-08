package dao;

import models.Vehiculo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/* =======================================================================================
 * CAPA DE ACCESO A DATOS (PERSISTENCIA - VEHÍCULO)
 * =======================================================================================
 * CONCEPTO APLICADO: Componente DAO para la entidad Vehículo. 
 * Implementa JDBC nativo para aislar las sentencias DML de la aplicación.
 * Asegura la persistencia completa del objeto, incluyendo su fecha de alta en el sistema.
 * =======================================================================================
 */
public class VehiculoDAO {

    public boolean registrarVehiculo(Vehiculo vehiculo) {
        // Sentencia SQL alineada con la base de datos actualizada (incluye fecha_registro)
        String sql = "INSERT INTO Vehiculo (patente, marca, modelo, anio, vin, id_cliente, fecha_registro) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, vehiculo.getPatente());
            stmt.setString(2, vehiculo.getMarca());
            stmt.setString(3, vehiculo.getModelo());
            stmt.setInt(4, vehiculo.getAnio());
            stmt.setString(5, vehiculo.getVin());
            stmt.setInt(6, vehiculo.getIdCliente());
            
            // Conversión de la fecha del objeto Java a formato java.sql.Date
            java.sql.Date sqlDate = new java.sql.Date(vehiculo.getFechaRegistro().getTime());
            stmt.setDate(7, sqlDate);
            
            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.err.println("Error en VehiculoDAO al registrar: " + e.getMessage());
            return false;
        }
    }
}