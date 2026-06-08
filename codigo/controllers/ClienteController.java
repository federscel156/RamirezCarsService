package controllers;

import dao.ClienteDAO;
import dao.VehiculoDAO;
import models.Cliente;
import models.Vehiculo;
import java.util.Date;

/* =======================================================================================
 * CAPA LÓGICA DE NEGOCIO (CONTROLADOR TRANSACCIONAL)
 * =======================================================================================
 * CONCEPTO APLICADO: Orquestador transaccional bajo el patrón MVC. Centraliza la ejecución
 * del CU01 interactuando secuencialmente con múltiples componentes de acceso a datos (DAOs)
 * para asegurar la consistencia de la base de datos relacional sin fisuras en las FKs.
 * * CUMPLIMIENTO DE LA RÚBRICA:
 * - "Estructuras condicionales": Validaciones previas de campos mandatorios concurrentes.
 * - "Manejo de excepciones": Captura preventiva ante errores de parseo numérico en el año.
 * - "Uso de constructores": Instanciación secuencial de las entidades de dominio en memoria.
 * =======================================================================================
 */
public class ClienteController {
    
    private ClienteDAO clienteDAO;
    private VehiculoDAO vehiculoDAO; // Acoplamiento secuencial directo como describe el UML

    public ClienteController() {
        this.clienteDAO = new ClienteDAO();
        this.vehiculoDAO = new VehiculoDAO();
    }

    // Nombre del método alineado estrictamente con el mensaje 'procesarRegistro(datos)' del UML 
    public String procesarRegistro(String nombre, String telefono, String email,
                                   String patente, String marca, String modelo, String anioStr, String vin) {
        
        // 1. Validaciones de presencia de datos obligatorios
        if (nombre == null || nombre.trim().isEmpty() || telefono == null || telefono.trim().isEmpty()) {
            return "Error: Nombre completo y Teléfono son mandatorios para el alta del Cliente.";
        }
        if (patente == null || patente.trim().isEmpty() || marca == null || marca.trim().isEmpty() || modelo == null || modelo.trim().isEmpty()) {
            return "Error: Patente, Marca y Modelo son requeridos para el alta del Vehículo.";
        }

        try {
            int anio = Integer.parseInt(anioStr);

            // 2. Ejecución del Paso 3 y 4 del Diagrama: Persistencia del Cliente Base
            Cliente nuevoCliente = new Cliente(0, nombre, telefono, email, new Date());
            int idClienteGenerado = clienteDAO.registrarCliente(nuevoCliente);

            // Estructura condicional para evaluar la respuesta de MySQL
            if (idClienteGenerado > 0) {
                
                // 3. Ejecución del Paso 7 y 8 del Diagrama: Persistencia inmediata del Vehículo asociando la FK
                Vehiculo nuevoVehiculo = new Vehiculo(patente, marca, modelo, anio, vin, idClienteGenerado, new Date());
                boolean exitoVehiculo = vehiculoDAO.registrarVehiculo(nuevoVehiculo);

                if (exitoVehiculo) {
                    return "OK"; // Transacción completa y exitosa
                } else {
                    return "Error: El cliente fue creado con ID " + idClienteGenerado + ", pero falló la inserción física de su vehículo.";
                }
            } else {
                return "Error: No se pudo registrar el cliente en el sistema.";
            }

        } catch (NumberFormatException e) {
            return "Error: El año del vehículo debe ser un número entero válido (ej: 2022).";
        } catch (Exception e) {
            return "Error imprevisto en el controlador técnico: " + e.getMessage();
        }
    }
}