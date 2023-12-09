/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package peaches.pelioficial.model;

/**
 *
 * @author q-ql
 */
public class Director {
    private int directorId;
    private String nombre;

    public Director() {
    }

    public Director(int directorId, String nombre) {
        this.directorId = directorId;
        this.nombre = nombre;
    }
    
    public int getDirectorId() {
        return directorId;
    }

    public void setDirectorId(int directorId) {
        this.directorId = directorId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    @Override
    public String toString(){
        return this.nombre;
    }
}
