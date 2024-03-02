/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package peaches.proyectoalejo.model;

/**
 *
 * @author santo
 */
public class Vehiculo {
       
    private String placa;
    private String modelo;
    private String dni;
    
    public Vehiculo() {   
    }
    public Vehiculo(String placa, String modelo, String dni) {
        this.placa = placa;
        this.modelo = modelo;
        this.dni = dni;
    }
    
    public String getPlaca() {
        return placa;
    }

    public String getModelo() {
        return modelo;
    }

    public String getDni() {
        return dni;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
    
 
    
    
    
}
