/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package peaches.proyectoalejo.service;

import java.util.List;
import peaches.proyectoalejo.DAO.ClienteDAO;
import peaches.proyectoalejo.model.Cliente;
import peaches.proyectoalejo.util.Conexion;


/**
 *
 * @author santo
 */
public class ClienteService {
    
    private ClienteDAO clienteDAO; 
    
    public ClienteService(){
        this.clienteDAO = new ClienteDAO(Conexion.conectar());
    }
        public ClienteService(ClienteDAO clienteDAO){
        this.clienteDAO = clienteDAO;
    }
        
         public List<Cliente> obtenerTodosLosClientes(){
        return clienteDAO.getAll();
    }
         
         public void guardarCliente(Cliente cliente){
             clienteDAO.save(cliente);
         }
        
        
     
}
