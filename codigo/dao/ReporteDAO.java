package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * CONCEPTO APLICADO: Funcionalidad analítica del sistema.
 * Este DAO ejecuta consultas de agregación (COUNT) para devolver métricas de negocio.
 * Se implementa la gestión de ResultSet junto con el bloque try-catch estructural.
 */
public class ReporteDAO {
    public String obtenerTotalOrdenesAbiertas() {
        String sql = "SELECT COUNT(*) AS total FROM Orden_Trabajo WHERE estado = 'Abierta'";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            if (rs.next()) {
                return "Órdenes de Trabajo Activas en el Taller: " + rs.getInt("total");
            }
        } catch (SQLException e) {
            return "Error al generar reporte: " + e.getMessage();
        }
        return "No hay datos disponibles.";
    }
}