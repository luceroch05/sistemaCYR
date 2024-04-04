/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package peaches.proyectoalejo.model;

/**
 *
 * @author santo
 */
public class DetalleRepuesto {
    
        String idVenta;
    int idRepuesto;
    int cantidad;
    double precioUnidad;
    double total;
    
    public DetalleRepuesto(){
        
    }

    public DetalleRepuesto(String idVenta, int idRepuesto, int cantidad, double precioUnidad, double total) {
        this.idVenta = idVenta;
        this.idRepuesto = idRepuesto;
        this.cantidad = cantidad;
        this.precioUnidad = precioUnidad;
        this.total = total;
    }

    public String getIdVenta() {
        return idVenta;
    }

    public int getIdRepuesto() {
        return idRepuesto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecioUnidad() {
        return precioUnidad;
    }

    public double getTotal() {
        return total;
    }

    public void setIdVenta(String idVenta) {
        this.idVenta = idVenta;
    }

    public void setIdRepuesto(int idRepuesto) {
        this.idRepuesto = idRepuesto;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setPrecioUnidad(double precioUnidad) {
        this.precioUnidad = precioUnidad;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    
    
    
    
    
    
}
