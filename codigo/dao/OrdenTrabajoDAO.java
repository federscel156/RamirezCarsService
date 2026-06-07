package dao;

import models.OrdenTrabajo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrdenTrabajoDAO {

    public boolean abrirOrden(OrdenTrabajo ot) {
        // Respeta la estructura de tu tabla Orden_Trabajo en MySQL
        String sql = "INSERT INTO Orden_Trabajo (patente, fecha, kilometraje, diagnostico_inicial, estado) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, ot.getPatente());
            stmt.setDate(2, new java.sql.Date(ot.getFecha().getTime()));
            stmt.setInt(3, ot.getKilometraje());
            stmt.setString(4, ot.getDiagnosticoInicial());
            stmt.setString(5, ot.getEstado());
            // No insertamos fecha_finalizacion porque al abrir la OT es null
            
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error en OT DAO: " + e.getMessage());
            return false;
        }
    }

    public boolean cerrarOrden(int nroOT) {
        String sql = "UPDATE Orden_Trabajo SET estado = 'Cerrada', fecha_finalizacion = ? WHERE nro_ot = ?";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
            stmt.setInt(2, nroOT);
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al cerrar OT: " + e.getMessage());
            return false;
        }
    }
}