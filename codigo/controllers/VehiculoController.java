package controllers;

import dao.VehiculoDAO;
import models.Vehiculo;
import java.util.Date;

public class VehiculoController {
    
    private VehiculoDAO vehiculoDAO;

    public VehiculoController() {
        this.vehiculoDAO = new VehiculoDAO();
    }

    public String procesarRegistroVehiculo(String patente, String marca, String modelo, String anioStr, String vin, String idClienteStr) {
        
        // Estructuras de control y validación
        if (patente == null || patente.trim().isEmpty()) {
            return "Error: La patente es obligatoria.";
        }
        
        try {
            int anio = Integer.parseInt(anioStr);
            int idCliente = Integer.parseInt(idClienteStr);
            
            // Instanciación del objeto
            Vehiculo nuevoVehiculo = new Vehiculo(patente, marca, modelo, anio, vin, idCliente, new Date());
            
            boolean exito = vehiculoDAO.registrarVehiculo(nuevoVehiculo);
            
            if (exito) {
                return "OK";
            } else {
                return "Error: No se pudo guardar el vehículo. Verifique que el ID de Cliente exista.";
            }
            
        } catch (NumberFormatException e) {
            return "Error: El año y el ID del Cliente deben ser valores numéricos.";
        } catch (Exception e) {
            return "Error inesperado: " + e.getMessage();
        }
    }
}