/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package peaches.proyectoalejo.service;

import java.util.List;
import peaches.proyectoalejo.DAO.DetalleRepuestoDAO;
import peaches.proyectoalejo.model.Cliente;
import peaches.proyectoalejo.model.DetalleRepuesto;
import peaches.proyectoalejo.util.Conexion;

/**
 *
 * @author santo
 */
public class DetalleRepuestoService {
    
     private DetalleRepuestoDAO detalleRepuestoDAO; 
    
    public DetalleRepuestoService(){
        this.detalleRepuestoDAO = new DetalleRepuestoDAO(Conexion.conectar());
    }
        public DetalleRepuestoService(DetalleRepuestoDAO detalleRepuestoDAO){
        this.detalleRepuestoDAO = detalleRepuestoDAO;
    }
        
         public List<DetalleRepuesto> obtenerTodosDetalleRepuestos(){
        return detalleRepuestoDAO.getAll();
    }
         
                  public List<DetalleRepuesto> obtenerTodosDetalleRepuestosPorVenta(String idVenta){
        return detalleRepuestoDAO.getAllByIdVenta(idVenta);
    }
         
         public void guardarDetalleRepuesto(DetalleRepuesto detalleRepuesto){
             detalleRepuestoDAO.save(detalleRepuesto);
         }
         
         
         public void eliminarDetalleRepuesto(DetalleRepuesto detalleRepuesto){
             detalleRepuestoDAO.delete(detalleRepuesto);
         }
         
         public void actualizarDetalleRepuesto(DetalleRepuesto detalleRepuesto){
             detalleRepuestoDAO.update(detalleRepuesto);
         }
        
        
    
}
