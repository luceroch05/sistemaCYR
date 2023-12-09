/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package peaches.pelioficial.service;

import java.util.List;
import peaches.pelioficial.dao.ListaEsperaDAO;
import peaches.pelioficial.model.ListaDeEspera;
import peaches.pelioficial.util.DatabaseConnector;

/**
 *
 * @author mtx0v
 */
public class ListaEsperaService {
    private ListaEsperaDAO listaEsperaDAO;
    
    public ListaEsperaService(){
        this.listaEsperaDAO = new ListaEsperaDAO(DatabaseConnector.conectar());
    }
    
    public List<ListaDeEspera> obtenerListaEsperaPararPelicula(int peliculaId){
        return listaEsperaDAO.obtenerListaEsperaParaPelicula(peliculaId);
    }
    
    public boolean agregarSocioAListaEspera(int peliculaId, int socioId) {
        return listaEsperaDAO.agregarSocioListaEspera(peliculaId, socioId);
    }
    
    public boolean eliminarDeListaEspera(int listaEsperaId){
        return listaEsperaDAO.eliminarDeListaEspera(listaEsperaId);
    }
    
    public List<ListaDeEspera> obtenerTodaListaDeEspera(){
        return listaEsperaDAO.getAll();
    }
}
