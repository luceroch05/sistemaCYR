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
import peaches.proyectoalejo.model.Cliente;

/**
 *
 * @author santo
 */
public class ClienteDAO implements DAO<Cliente>{
    
    private Connection connection;
    
        //metodo para obtener la conexion a bd
      public ClienteDAO(Connection connection){
        this.connection = connection;
    }
      
      
      //metodo get para obtener solo 1 cliente
    
    
    public Optional<Cliente> get(String dni){
        Cliente cliente = null;
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM Clientes WHERE dni = ?")){
            statement.setString(1, dni);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                cliente = new Cliente();
                cliente.setNombre(resultSet.getString("nombre"));
                cliente.setApellido(resultSet.getString("apellido"));
                cliente.setTelefono(resultSet.getString("telefono"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(cliente);
    }
        
         @Override
    public List<Cliente> getAll(){
        List<Cliente> ListaDeClientes = new ArrayList<>();
        try (Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery("SELECT * FROM clientes");
            while(resultSet.next()){
                Cliente cliente = new Cliente();
                cliente.setDNI(resultSet.getString("dni"));
                cliente.setNombre(resultSet.getString("nombreCliente"));
                cliente.setApellido(resultSet.getString("apellidoCliente"));
                cliente.setTelefono(resultSet.getString("telefono"));

                ListaDeClientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ListaDeClientes;
    }
    
    
    
    //metodo para guardar un cliente
     @Override
public void save(Cliente cliente) {
    String sql = "INSERT INTO Clientes (dni, apellidoCliente, nombreCliente , telefono) VALUES (?, ?, ?, ?)";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setString(1, cliente.getDNI());
        statement.setString(2, cliente.getApellido());
        statement.setString(3, cliente.getNombre());
        statement.setString(4, cliente.getTelefono());
        
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
      public void update(Cliente cliente, String[] params) {
        String sql = "UPDATE Cliente SET nombre = ?, apellido = ?, telefono = ? WHERE dni = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, cliente.getNombre());
            statement.setString(2, cliente.getApellido());
            statement.setString(3, cliente.getTelefono());
            statement.setString(4, cliente.getDNI());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Updating socio failed, no rows affected.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
      
      
      
      
      //metodo para eliminar un socio
      @Override
    public void delete(Cliente cliente){
        try{
            connection.setAutoCommit(false);
          
            try(PreparedStatement statement = connection.prepareStatement("DELETE FROM Cliente WHERE dni = ?")){
                statement.setString(1, cliente.getDNI());
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
