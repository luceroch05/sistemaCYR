/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package peaches.proyectoalejo.model;

/**
 *
 * @author santo
 */
public class Cliente {
    private int idCliente;
    private String nombre;
    private String apellido;
    private int telefono;
    
    public Cliente(){};
    
    public Cliente(int idCliente, String nombre, String apellido, int telefono) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
    }
    
    
     public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }
    
 
}
