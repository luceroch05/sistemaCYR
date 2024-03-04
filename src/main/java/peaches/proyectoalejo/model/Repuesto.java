/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package peaches.proyectoalejo.model;

/**
 *
 * @author santo
 */
public class Repuesto {
    
    private int idRepuesto;
    private String descripcion;
    private int stock;
    private double precio;
    private int idProveedor;

    public Repuesto(int idRepuesto, String descripcion, int stock, double precio, int idProveedor) {
        this.idRepuesto = idRepuesto;
        this.descripcion = descripcion;
        this.stock = stock;
        this.precio = precio;
        this.idProveedor = idProveedor;
    }

    public Repuesto() {
        
    }

    public int getIdRepuesto() {
        return idRepuesto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getStock() {
        return stock;
    }

    public double getPrecio() {
        return precio;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdRepuesto(int idRepuesto) {
        this.idRepuesto = idRepuesto;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }
    

    
}
