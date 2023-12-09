/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package peaches.pelioficial.model;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author q-ql
 */
public class ListaDeEspera {
    private int idListaEspera;
    private int idSocio;
    private String nombreSocio;
    private LocalDate fechaSolicitud;
    
    public ListaDeEspera(){
        
    }

    public ListaDeEspera(int idListaEspera, int idSocio, String nombreSocio, LocalDate fechaSolicitud) {
        this.idListaEspera = idListaEspera;
        this.idSocio = idSocio;
        this.nombreSocio = nombreSocio;
        this.fechaSolicitud = fechaSolicitud;
    }
    
    public int getIdListaEspera() {
        return idListaEspera;
    }

    public void setIdListaEspera(int idListaEspera) {
        this.idListaEspera = idListaEspera;
    }

    public int getIdSocio() {
        return idSocio;
    }

    public void setIdSocio(int idSocio) {
        this.idSocio = idSocio;
    }

    public String getNombreSocio() {
        return nombreSocio;
    }

    public void setNombreSocio(String nombreSocio) {
        this.nombreSocio = nombreSocio;
    }

    public LocalDate getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(LocalDate fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }
    
    
}
