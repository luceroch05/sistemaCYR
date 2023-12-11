/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package peaches.pelioficial.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import peaches.pelioficial.model.ListaDeEspera;

/**
 *
 * @author q-ql
 */
public class ListaEsperaDAO implements Dao<ListaDeEspera>{
    private Connection connection;
    
    public ListaEsperaDAO(Connection connection){
        this.connection = connection;
    }
    
    @Override
    public Optional<ListaDeEspera> get(long id){
        ListaDeEspera listaDeEspera = null;
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM ListaEspera WHERE lista_espera_id = ?")){
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                listaDeEspera = new ListaDeEspera();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(listaDeEspera);
    }
    
    @Override
    public List<ListaDeEspera> getAll(){
        List<ListaDeEspera> listaDeEsperas = new ArrayList<>();
        try (Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery("SELECT le.lista_espera_id, le.socio_id, s.nombre, le.fecha_solicitud " +
                     "FROM listaespera le " +
                     "INNER JOIN socios s ON le.socio_id = s.socio_id ");
            while(resultSet.next()){
                ListaDeEspera listaDeEspera = new ListaDeEspera();
                listaDeEspera.setIdListaEspera(resultSet.getInt("lista_espera_id"));
                listaDeEspera.setIdSocio(resultSet.getInt("socio_id"));
                listaDeEspera.setNombreSocio(resultSet.getString("nombre"));
                listaDeEspera.setFechaSolicitud(resultSet.getDate("fecha_solicitud").toLocalDate());
                listaDeEsperas.add(listaDeEspera);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaDeEsperas;
    }
    
    @Override
    public int save(ListaDeEspera listaDeEspera) {
        String sql = "INSERT INTO ListaEspera (pelicula_id, socio_id, fecha_solicitud) VALUES (?, ?, ?)";
        int generatedId = 0;
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            int affectedRows = statement.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        generatedId = generatedKeys.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return generatedId;
    }
    
    @Override
    public void update(ListaDeEspera listaDeEspera, String[] params) {
        String sql = "UPDATE ListaEspera SET pelicula_id = ?, socio_id = ?, fecha_solicitud = ? WHERE lista_espera_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void delete(ListaDeEspera listaDeEspera) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM ListaEspera WHERE lista_espera_id = ?")) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<ListaDeEspera> obtenerListaEsperaParaPelicula(int peliculaId) {
        List<ListaDeEspera> listaEspera = new ArrayList<>();
        String sql = "SELECT le.lista_espera_id, le.socio_id, s.nombre, le.fecha_solicitud " +
                     "FROM listaespera le " +
                     "INNER JOIN socios s ON le.socio_id = s.socio_id " +
                     "WHERE le.pelicula_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, peliculaId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ListaDeEspera listaDeEspera = new ListaDeEspera();
                 listaDeEspera.setIdListaEspera(resultSet.getInt("lista_espera_id"));
                 listaDeEspera.setIdSocio(resultSet.getInt("socio_id"));
                 listaDeEspera.setNombreSocio(resultSet.getString("nombre")); // Asume que tienes un campo para el nombre del socio
                 listaDeEspera.setFechaSolicitud(resultSet.getDate("fecha_solicitud").toLocalDate());
                listaEspera.add(listaDeEspera);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaEspera;
    }
    
    public boolean agregarSocioListaEspera(int peliculaId, int socioId) {
        String sql = "INSERT INTO listaespera (pelicula_id, socio_id, fecha_solicitud) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, peliculaId);
            statement.setInt(2, socioId);
            statement.setDate(3, new Date(System.currentTimeMillis())); // Fecha actual

            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean eliminarDeListaEspera(int listaEsperaId){
        String sql = "DELETE FROM listaespera WHERE lista_espera_id = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, listaEsperaId);
            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }
}
