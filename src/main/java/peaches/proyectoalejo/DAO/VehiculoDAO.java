/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package peaches.proyectoalejo.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import peaches.proyectoalejo.model.Vehiculo;

/**
 *
 * @author santo
 */
public class VehiculoDAO implements DAO<Vehiculo>{
    
    private Connection connection;
    
        public VehiculoDAO(Connection connection){
        this.connection = connection;
    }
         
           
    public Optional<Vehiculo> get(String placa){
        Vehiculo vehiculo = null;
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM Vehiculo WHERE placa = ?")){
            statement.setString(1, placa);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                vehiculo = new Vehiculo();
                vehiculo.setPlaca(resultSet.getString("placa"));
                vehiculo.setModelo(resultSet.getString("modelo"));
                vehiculo.setDni(resultSet.getString("dni"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(vehiculo);
    }
        
        
    public List<Vehiculo> getAll(){
        List<Vehiculo> ListaDeVehiculos = new ArrayList<>();
        try (Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery("SELECT * FROM vehiculo");
            while(resultSet.next()){
               Vehiculo vehiculo = new Vehiculo();
               vehiculo.setPlaca(resultSet.getString("placa"));
               vehiculo.setModelo(resultSet.getString("modelo"));
               vehiculo.setDni(resultSet.getString("dni"));              
                ListaDeVehiculos.add(vehiculo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ListaDeVehiculos;
    }
    
    
    
    //metodo para guardar un cliente

public void save(Vehiculo vehiculo) {
    String sql = "INSERT INTO vehiculo (placa, modelo, dni) VALUES (?, ?, ?)";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setString(1, vehiculo.getPlaca());
        statement.setString(2, vehiculo.getModelo());
        statement.setString(3, vehiculo.getDni());
        
        // Ejecutar la inserción
        int affectedRows = statement.executeUpdate();
        if (affectedRows == 0) {
            System.out.println("¡Advertencia: No se ha insertado ninguna fila!");
            // Puedes decidir lanzar una excepción o manejar de otra manera esta situación.
        } else {
            System.out.println("¡Cliente insertado exitosamente!");
            // Puedes imprimir un mensaje de éxito o realizar otras acciones necesarias.
        }
    } catch (SQLException e) {
        e.printStackTrace();
        // Manejar la excepción de manera adecuada, ya sea lanzando una excepción personalizada o manejando el error de otra manera según tus necesidades.
    }
}


    
    
    //metodo para actualizar el cliente
      public void update(Vehiculo vehiculo) {
        String sql = "UPDATE vehiculo SET modelo = ?, dni = ? WHERE placa = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, vehiculo.getModelo());
            statement.setString(2, vehiculo.getDni());
            statement.setString(3, vehiculo.getPlaca());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Updating socio failed, no rows affected.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
      
      
      
      
      //metodo para eliminar un socio

    public void delete(Vehiculo vehiculo){
        try{
            connection.setAutoCommit(false);
          
            try(PreparedStatement statement = connection.prepareStatement("DELETE FROM vehiculo WHERE placa = ?")){
                statement.setString(1, vehiculo.getPlaca());
                int affectedRows = statement.executeUpdate();
                if(affectedRows == 0){
                    throw new SQLException("Deleting socio failed, no rows affected.");
                }
            }
            
            connection.commit();
        }catch(SQLException e){
            e.printStackTrace();
            try{
                connection.rollback();
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }finally{
            try{
                connection.setAutoCommit(true);
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

    

    
 
    
}
