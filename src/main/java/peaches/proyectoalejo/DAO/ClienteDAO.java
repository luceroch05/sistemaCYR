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
    
    @Override
    public Optional<Cliente> get(int id){
        Cliente cliente = null;
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM Clientes WHERE id = ?")){
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                cliente = new Cliente();
                cliente.setNombre(resultSet.getString("nombre"));
                cliente.setNombre(resultSet.getString("apellido"));
   
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
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Cliente");
            while(resultSet.next()){
                Cliente cliente = new Cliente();
                cliente.setIdCliente(resultSet.getInt("idCliente"));
                cliente.setNombre(resultSet.getString("nombre"));
                cliente.setApellido(resultSet.getString("apellido"));
       
                ListaDeClientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ListaDeClientes;
    }
    
    
    
    //metodo para guardar un cliente
     @Override
    public int save(Cliente cliente) {
        String sql = "INSERT INTO Clientes (nombre, apellido, telefono) VALUES (?, ?, ?)";
        int generatedId = 0;
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, cliente.getNombre());
            statement.setString(2, cliente.getApellido());
            statement.setInt(3, cliente.getTelefono());
            int affectedRows = statement.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        generatedId = generatedKeys.getInt(1);
                        cliente.setIdCliente(generatedId);
                    }
                }
            }
   
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return generatedId;
    }
    
    
    //metodo para actualizar el cliente
      public void update(Cliente cliente, String[] params) {
        String sql = "UPDATE Cliente SET nombre = ?, apellido = ?, telefono = ? WHERE idCliente = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, cliente.getNombre());
            statement.setString(2, cliente.getApellido());
            statement.setInt(3, cliente.getTelefono());
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
     
            
            try(PreparedStatement statement = connection.prepareStatement("DELETE FROM Cliente WHERE idCliente = ?")){
                statement.setInt(1, cliente.getIdCliente());
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
