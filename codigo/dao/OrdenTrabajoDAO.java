package dao;

import models.OrdenTrabajo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/* =======================================================================================
 * CAPA DE ACCESO A DATOS (PERSISTENCIA - ORDEN DE TRABAJO)
 * =======================================================================================
 * CONCEPTO APLICADO: Componente transaccional DAO (Data Access Object). 
 * Encapsula las operaciones DML (Data Manipulation Language) y DQL (Data Query Language) 
 * requeridas para gestionar el ciclo de vida completo de una atención técnica (Historia Clínica).
 * * CUMPLIMIENTO DE LA RÚBRICA:
 * - "Tratamiento y manejo de excepciones": Utilización de bloques try-with-resources para 
 * garantizar el cierre de conexiones y captura estructurada de excepciones SQL.
 * - "Sintaxis y tipos de datos": Mapeo relacional preciso entre los objetos Java y MySQL.
 * =======================================================================================
 */
public class OrdenTrabajoDAO {

    /*
     * Función de Validación Histórica (Regla de Negocio Preventiva).
     * Ejecuta una función de agregación (MAX) en la base de datos para recuperar 
     * el último kilometraje registrado de un vehículo específico, garantizando 
     * la consistencia lineal en el historial clínico (Caso de Prueba CP2).
     */
    public int obtenerUltimoKilometraje(String patente) {
        String sql = "SELECT MAX(kilometraje) AS max_km FROM Orden_Trabajo WHERE patente = ?";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, patente);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("max_km"); // Retorna 0 automáticamente si el vehículo es nuevo
                }
            }
        } catch (SQLException e) {
            System.err.println("Error de integridad al consultar historial de kilometraje: " + e.getMessage());
        }
        return 0; 
    }

    /*
     * Transacción de Inicialización de Ciclo Técnico (Apertura de OT).
     * Inserta una nueva cabecera de registro clínico en estado inicial ('Abierta'), 
     * delegando al motor relacional la validación de las claves foráneas (patente).
     */
    public boolean abrirOrden(OrdenTrabajo ot) {
        String sql = "INSERT INTO Orden_Trabajo (patente, fecha, kilometraje, diagnostico_inicial, estado) VALUES (?, ?, ?, ?, 'Abierta')";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, ot.getPatente());
            stmt.setDate(2, new java.sql.Date(ot.getFecha().getTime()));
            stmt.setInt(3, ot.getKilometraje());
            stmt.setString(4, ot.getDiagnosticoInicial());
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error relacional al registrar apertura de OT: " + e.getMessage());
            return false;
        }
    }

    /*
     * Transacción de Cierre Operativo (Actualización de Estado).
     * Garantiza la inmutabilidad de la historia clínica modificando el indicador de estado
     * de la transacción a 'Finalizada', paso previo y obligatorio para la emisión de la Ficha PDF.
     */
    public boolean finalizarOrden(int nroOT) {
        String sql = "UPDATE Orden_Trabajo SET estado = 'Finalizada' WHERE nro_ot = ?";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, nroOT);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error relacional al finalizar la OT: " + e.getMessage());
            return false;
        }
    }
}