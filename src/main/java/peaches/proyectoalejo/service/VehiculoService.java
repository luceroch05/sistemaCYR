/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package peaches.proyectoalejo.service;

import java.util.List;
import peaches.proyectoalejo.DAO.VehiculoDAO;
import peaches.proyectoalejo.model.Vehiculo;
import peaches.proyectoalejo.util.Conexion;

/**
 *
 * @author Lucero
 */
public class VehiculoService {

    private VehiculoDAO vehiculoDAO; 
    
    public VehiculoService(){
        this.vehiculoDAO = new VehiculoDAO(Conexion.conectar());
    }
        public VehiculoService(VehiculoDAO vehiculoDAO){
        this.vehiculoDAO = vehiculoDAO;
    }
        
         public List<Vehiculo> obtenerTodosLosVehiculos(){
        return vehiculoDAO.getAll();
    }
         
         public void guardarVehiculo(Vehiculo vehiculo){
             vehiculoDAO.save(vehiculo);
         }
         
         
         public void eliminarVehiculo(Vehiculo vehiculo){
             vehiculoDAO.delete(vehiculo);
         }
         
         public void actualizarVehiculo(Vehiculo vehiculo){
             vehiculoDAO.update(vehiculo);
         }
    
    
}
