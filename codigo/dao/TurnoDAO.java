package dao;

import models.Turno;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
 * CONCEPTO APLICADO: Capa de Acceso a Datos (DAO). 
 * Su responsabilidad funcional es abstraer la complejidad de la base de datos.
 * Cumple con el MANEJO DE EXCEPCIONES requerido capturando SQLExceptions, 
 * protegiendo así al sistema de caídas si el motor MySQL falla.
 */
public class TurnoDAO {
    public boolean agendarTurno(Turno turno) {
        // Se agrega hora_inicio al script
        String sql = "INSERT INTO Turno (patente, fecha, hora_inicio, motivo, estado) VALUES (?, ?, ?, ?, 'Pendiente')";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, turno.getPatente());
            stmt.setDate(2, new java.sql.Date(turno.getFecha().getTime()));
            stmt.setString(3, turno.getHoraInicio()); // Se envía la hora
            stmt.setString(4, turno.getMotivo());
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error de Base de Datos al agendar turno: " + e.getMessage());
            return false;
        }
    }
}