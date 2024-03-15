/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package peaches.proyectoalejo.model;

import java.time.LocalDate;
import java.util.logging.Logger;

/**
 *
 * @author santo
 */
public class Boleta {
    
    int idBoleta;
    String dni;
    LocalDate fecha;
    
    public Boleta(){
    }
    

    public Boleta(int idBoleta, String dni, LocalDate fecha) {
        this.idBoleta = idBoleta;
        this.dni = dni;
        this.fecha = fecha;
    }


    public int getIdBoleta() {
        return idBoleta;
    }

    public String getDni() {
        return dni;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setIdBoleta(int idBoleta) {
        this.idBoleta = idBoleta;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    

    
    
}
