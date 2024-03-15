/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package peaches.proyectoalejo.service;

import java.util.List;
import java.util.Optional;
import peaches.proyectoalejo.DAO.EmpleadoDAO;
import peaches.proyectoalejo.model.Empleado;
import peaches.proyectoalejo.util.Conexion;

/**
 *
 * @author santo
 */
public class EmpleadoService {
    private EmpleadoDAO empleadoDAO; 
    
    public EmpleadoService(){
        this.empleadoDAO = new EmpleadoDAO(Conexion.conectar());
    }
    
    
    public Empleado obtenerEmpleadoDni(String dni){
        Optional<Empleado> socioOpt = empleadoDAO.get(dni);
        return socioOpt.orElse(null);
    }    
    public void agregarEmpleado(Empleado empleado){
        this.empleadoDAO.save(empleado);
    }
    
    public List<Empleado> obtenerTodosEmpleados(){
        return this.empleadoDAO.getAll();
    }
    
    public void eliminarEmpleado(Empleado empleado){
         this.empleadoDAO.delete(empleado);
            
         }
    
    public void actualizarEmpleado(Empleado empleado){
        this.empleadoDAO.update(empleado);
    }
    
    
}
