package dao;

import models.TareaRealizada;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TareaDAO {
    public boolean registrarTarea(TareaRealizada tarea) {
        String sql = "INSERT INTO Tarea_Realizada (nro_ot, descripcion_trabajo, insumos_texto, fecha_registro) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, tarea.getNroOT());
            stmt.setString(2, tarea.getDescripcionTrabajo());
            stmt.setString(3, tarea.getInsumosTexto());
            stmt.setDate(4, new java.sql.Date(tarea.getFechaRegistro().getTime()));
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error en TareaDAO: " + e.getMessage());
            return false;
        }
    }
}