package dao;

import models.Vehiculo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class VehiculoDAO {

    public boolean registrarVehiculo(Vehiculo vehiculo) {
        // Sentencia SQL respetando tu esquema de base de datos
        String sql = "INSERT INTO Vehiculo (patente, marca, modelo, anio, vin, id_cliente, fecha_registro) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, vehiculo.getPatente());
            stmt.setString(2, vehiculo.getMarca());
            stmt.setString(3, vehiculo.getModelo());
            stmt.setInt(4, vehiculo.getAnio());
            stmt.setString(5, vehiculo.getVin());
            stmt.setInt(6, vehiculo.getIdCliente());
            
            // Conversión de fecha
            java.sql.Date sqlDate = new java.sql.Date(vehiculo.getFechaRegistro().getTime());
            stmt.setDate(7, sqlDate);
            
            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            // Manejo de excepciones requerido por la rúbrica
            System.err.println("Error en VehiculoDAO al registrar: " + e.getMessage());
            return false;
        }
    }
}