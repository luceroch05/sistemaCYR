/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package peaches.proyectoalejo.service;

import java.util.List;
import peaches.proyectoalejo.DAO.ServicioDAO;
import peaches.proyectoalejo.model.Servicio;
import peaches.proyectoalejo.util.Conexion;

/**
 *
 * @author Lucero
 */
public class ServicioService {
    
    private ServicioDAO servicioDAO; 
    
    public ServicioService(){
        this.servicioDAO = new ServicioDAO(Conexion.conectar());
    }
    
    public ServicioService(ServicioDAO servicioDAO){
        this.servicioDAO = servicioDAO;
    }
    
    public void guardarServicio(Servicio servicio){
        this.servicioDAO.save(servicio);
        
    }
    
    public List<Servicio> obtenerTodosLosServicios(){
        return this.servicioDAO.getAll();
    }
    
    public void eliminarRepuesto(Servicio servicio){
        this.servicioDAO.delete(servicio);
    }
    
        public void actualizarServicio(Servicio servicio){
        this.servicioDAO.update(servicio);
    }
    
    
    
}
