package dao;

import models.Vehiculo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/* =======================================================================================
 * CAPA DE ACCESO A DATOS (PERSISTENCIA - VEHÍCULO Y ALERTAS)
 * =======================================================================================
 * CONCEPTO APLICADO: Componente DAO que centraliza las operaciones de la entidad Vehículo.
 * Implementa persistencia transaccional y consultas de agregación (JOIN) para resolver 
 * el estado clínico de la flota, cumpliendo estrictamente con el Diagrama de Secuencia 
 * del CU05 y la estructura relacional 3FN.
 * =======================================================================================
 */
public class VehiculoDAO {

    /*
     * Función de Persistencia de Alta.
     * Inserta un nuevo registro asegurando la integridad referencial con el propietario.
     */
    public boolean registrarVehiculo(Vehiculo vehiculo) {
        String sql = "INSERT INTO Vehiculo (patente, marca, modelo, anio, vin, id_cliente, fecha_registro) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, vehiculo.getPatente());
            stmt.setString(2, vehiculo.getMarca());
            stmt.setString(3, vehiculo.getModelo());
            stmt.setInt(4, vehiculo.getAnio());
            stmt.setString(5, vehiculo.getVin());
            stmt.setInt(6, vehiculo.getIdCliente());
            stmt.setDate(7, new java.sql.Date(vehiculo.getFechaRegistro().getTime()));
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error relacional al registrar vehículo: " + e.getMessage());
            return false;
        }
    }

    /*
     * Función de Diagnóstico Preventivo (CU05).
     * Ejecuta una consulta técnica cruzada (JOIN) para determinar vehículos que requieren 
     * atención preventiva según el kilometraje acumulado, delegando la carga al motor MySQL.
     */
    public List<String> getVehiculosParaMantenimiento() {
        List<String> alertas = new ArrayList<>();
        String sql = "SELECT V.patente, MAX(OT.kilometraje) as ultimo_km " +
                     "FROM Vehiculo V JOIN Orden_Trabajo OT ON V.patente = OT.patente " +
                     "GROUP BY V.patente HAVING ultimo_km % 10000 < 500";
        
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                alertas.add("Patente: " + rs.getString("patente") + " | KM Acumulado: " + rs.getInt("ultimo_km"));
            }
        } catch (SQLException e) {
            System.err.println("Error de agregación en consulta de mantenimiento: " + e.getMessage());
        }
        return alertas;
    }
}