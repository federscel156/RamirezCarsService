package dao;

import models.TareaRealizada;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/* =======================================================================================
 * CAPA DE ACCESO A DATOS (PERSISTENCIA - TAREAS E INSUMOS)
 * =======================================================================================
 * CONCEPTO APLICADO: Componente DAO que ejecuta sentencias DML (Data Manipulation Language).
 * Vincula inmutablemente el trabajo realizado a una Orden de Trabajo activa garantizando 
 * la integridad referencial del historial clínico del taller.
 * =======================================================================================
 */
public class TareaDAO {

    public boolean registrarTarea(TareaRealizada tarea) {
        String sql = "INSERT INTO Tarea_Realizada (nro_ot, descripcion_trabajo, insumos_texto, fecha_registro) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, tarea.getNroOT());
            stmt.setString(2, tarea.getDescripcionTrabajo());
            stmt.setString(3, tarea.getInsumosTexto());
            // Conversión de java.util.Date a java.sql.Date para el motor relacional
            stmt.setDate(4, new java.sql.Date(tarea.getFechaRegistro().getTime()));
            
            return stmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error de Base de Datos al registrar tarea: " + e.getMessage());
            return false;
        }
    }
}