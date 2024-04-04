/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package peaches.proyectoalejo.model;

import java.time.LocalDate;

/**
 *
 * @author santo
 */
public class Venta {
    
    
    private String idVenta;
    private String dni;
    private LocalDate fecha;
    private String dniEmpleado;
    
    public Venta(){
        
    }

    public Venta(String idVenta, String dni, LocalDate fecha, String dniEmpleado) {
        this.idVenta = idVenta;
        this.dni = dni;
        this.fecha = fecha;
        this.dniEmpleado = dniEmpleado;
    }

    public String getIdVenta() {
        return idVenta;
    }

    public String getDni() {
        return dni;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getDniEmpleado() {
        return dniEmpleado;
    }

    public void setIdVenta(String idVenta) {
        this.idVenta = idVenta;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setDniEmpleado(String dniEmpleado) {
        this.dniEmpleado = dniEmpleado;
    }
    
    
}
