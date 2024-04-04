/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package peaches.proyectoalejo.service;

import java.util.List;
import java.util.Optional;
import peaches.proyectoalejo.DAO.VentaDAO;
import peaches.proyectoalejo.model.Venta;
import peaches.proyectoalejo.util.Conexion;

/**
 *
 * @author santo
 */
public class VentaService {
    private VentaDAO ventaDAO;
    
    
    
     public VentaService(){
        this.ventaDAO = new VentaDAO(Conexion.conectar());
    }
     
     
        public VentaService(VentaDAO ventaDAO){
        this.ventaDAO = ventaDAO;
    }
        
         public List<Venta> obtenerTodasLasVentas(){
        return ventaDAO.getAll();
    }
         
           public Venta obtenerVentaPorId(String idVenta){
          Optional<Venta> venta = ventaDAO.getAllById(idVenta);
        return venta.orElse(null);
        }
         
         public String guardarVenta(Venta venta){
            return ventaDAO.save2(venta);
         }
         
         
         public void eliminarVenta(Venta venta){
             ventaDAO.delete(venta);
         }
         
         public void actualizarVenta(Venta venta){
             ventaDAO.update(venta);
         }
    
    
}
