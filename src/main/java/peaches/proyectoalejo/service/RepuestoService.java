/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package peaches.proyectoalejo.service;

import java.util.List;
import peaches.proyectoalejo.DAO.RepuestoDAO;
import peaches.proyectoalejo.model.Repuesto;
import peaches.proyectoalejo.util.Conexion;

/**
 *
 * @author santo
 */
public class RepuestoService {
      private RepuestoDAO repuestoDAO; 
    
    public RepuestoService(){
        this.repuestoDAO = new RepuestoDAO(Conexion.conectar());
    }
    
    public RepuestoService(RepuestoDAO repuestoDAO){
        this.repuestoDAO = repuestoDAO;
    }
    
    public void guardarRepuesto(Repuesto repuesto){
        this.repuestoDAO.save(repuesto);
        
    }
    
    public List<Repuesto> obtenerTodosLosRepuestos(){
        return this.repuestoDAO.getAll();
    }
    
    public void eliminarRepuesto(Repuesto repuesto){
        this.repuestoDAO.delete(repuesto);
    }
    
        public void actualizarRepuesto(Repuesto repuesto){
        this.repuestoDAO.update(repuesto);
    }
    
    
}
