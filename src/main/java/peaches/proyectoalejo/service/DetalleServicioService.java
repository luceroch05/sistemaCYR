/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package peaches.proyectoalejo.service;

import java.util.List;
import peaches.proyectoalejo.DAO.DetalleRepuestoDAO;
import peaches.proyectoalejo.DAO.DetalleServicioDAO;
import peaches.proyectoalejo.model.DetalleRepuesto;
import peaches.proyectoalejo.model.DetalleServicio;
import peaches.proyectoalejo.util.Conexion;

/**
 *
 * @author santo
 */
public class DetalleServicioService {
    
     
     private DetalleServicioDAO detalleServicioDAO; 
    
    public DetalleServicioService(){
        this.detalleServicioDAO = new DetalleServicioDAO(Conexion.conectar());
    }
        public DetalleServicioService(DetalleServicioDAO detalle){
        this.detalleServicioDAO = detalle;
    }
        
         public List<DetalleServicio> obtenerTodosDetalleServicio(){
        return detalleServicioDAO.getAll();
    }
         
     public List<DetalleServicio> obtenerTodosDetalleServicioPorVenta(String idVenta){
        return detalleServicioDAO.getAllByIdServicio(idVenta);
    }
         
         public void guardarDetalleServicio(DetalleServicio detalleServicio){
             detalleServicioDAO.save(detalleServicio);
         }
         
         
         public void eliminarDetalleServicio(DetalleServicio detalleServicio){
             detalleServicioDAO.delete(detalleServicio);
         }
         
         public void actualizarDetalleServicio(DetalleServicio detalleServicio){
             detalleServicioDAO.update(detalleServicio);
         }
    
}
