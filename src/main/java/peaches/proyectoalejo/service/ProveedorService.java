/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package peaches.proyectoalejo.service;

import java.util.List;
import peaches.proyectoalejo.DAO.ProveedorDAO;
import peaches.proyectoalejo.model.Proveedor;
import peaches.proyectoalejo.util.Conexion;

/**
 *
 * @author santo
 */
public class ProveedorService {
     private ProveedorDAO proveedorDAO; 
    
    public ProveedorService(){
        this.proveedorDAO = new ProveedorDAO(Conexion.conectar());
    }
    
    public ProveedorService(ProveedorDAO proveedorDAO){
        this.proveedorDAO = proveedorDAO;
    }
    
    public void guardarProveedor(Proveedor proveedor){
        this.proveedorDAO.save(proveedor);
        
    }
    
    public List<Proveedor> obtenerTodosLosProveedores(){
        return this.proveedorDAO.getAll();
    }
    
    public void eliminarProveedor(Proveedor proveedor){
        this.proveedorDAO.delete(proveedor);
    }
    
        public void actualizarProveedor(Proveedor proveedor){
        this.proveedorDAO.update(proveedor);
    }
    
}
