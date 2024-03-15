/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package peaches.proyectoalejo.model;

/**
 *
 * @author santo
 */
public class Empleado {
    
    String dniEmpleado;
    String nombreEmpleado;
    String contrasena;
    
    public Empleado(){
    }
    

    public Empleado(String dniEmpleado, String nombreEmpleado, String contrasena) {
        this.dniEmpleado = dniEmpleado;
        this.nombreEmpleado = nombreEmpleado;
        this.contrasena = contrasena;
    }

    public String getDniEmpleado() {
        return dniEmpleado;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setDniEmpleado(String dniEmpleado) {
        this.dniEmpleado = dniEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    

    
    
    
    
}
