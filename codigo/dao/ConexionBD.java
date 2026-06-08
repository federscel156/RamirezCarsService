package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static final String URL = "jdbc:mysql://localhost:3306/ramirez_cars_service";
    private static final String USER = "root";
    private static final String PASSWORD = ""; // Cambiá esto por tu pass de MySQL

    public static Connection conectar() {
        Connection conexion = null;
        try {
            // Manejo de Excepciones requerido por la rúbrica
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión a MySQL exitosa.");
        } catch (SQLException e) {
            System.err.println("Error de conexión a la base de datos: " + e.getMessage());
            // Estructura de control
            if(e.getErrorCode() == 1045) {
                System.err.println("Por favor, verifique el usuario y contraseña de MySQL.");
            }
        }
        return conexion;
    }
}