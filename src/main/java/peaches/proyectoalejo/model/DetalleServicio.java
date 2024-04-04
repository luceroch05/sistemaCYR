/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package peaches.proyectoalejo.model;

/**
 *
 * @author santo
 */
public class DetalleServicio {
    
        String idVenta;
    int idServicio;
    double precio;

    public DetalleServicio(){
        
    }
    
    public DetalleServicio(String idVenta, int idServicio, double precio) {
        this.idVenta = idVenta;
        this.idServicio = idServicio;
        this.precio = precio;
    }

    public String getIdVenta() {
        return idVenta;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public double getPrecio() {
        return precio;
    }

    public void setIdVenta(String idVenta) {
        this.idVenta = idVenta;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    
}
