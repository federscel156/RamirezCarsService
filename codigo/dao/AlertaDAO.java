package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/* =======================================================================================
 * CAPA DE ACCESO A DATOS (PERSISTENCIA - ALERTAS)
 * =======================================================================================
 * CONCEPTO APLICADO: DAO especializado en lógica de consulta agregada.
 * Ejecuta una consulta técnica cruzada (JOIN) para determinar vehículos que requieren 
 * atención preventiva según el kilometraje acumulado, delegando la carga pesada al motor MySQL.
 * =======================================================================================
 */
public class AlertaDAO {
    
    public List<String> obtenerVehiculosConMantenimientoPendiente() {
        List<String> alertas = new ArrayList<>();
        // Query: Busca vehículos cuyo último kilometraje registrado supere umbrales preventivos
        String sql = "SELECT V.patente, MAX(OT.kilometraje) as ultimo_km " +
                     "FROM Vehiculo V JOIN Orden_Trabajo OT ON V.patente = OT.patente " +
                     "GROUP BY V.patente HAVING ultimo_km % 10000 < 500"; // Lógica de ejemplo: alerta cada 10k km
        
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                alertas.add("Patente: " + rs.getString("patente") + " | KM Acumulado: " + rs.getInt("ultimo_km"));
            }
        } catch (SQLException e) {
            System.err.println("Error en AlertaDAO: " + e.getMessage());
        }
        return alertas;
    }
}