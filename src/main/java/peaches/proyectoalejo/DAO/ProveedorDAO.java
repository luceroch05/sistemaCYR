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
import peaches.proyectoalejo.model.Proveedor;

/**
 *
 * @author santo
 */
public class ProveedorDAO {
        private Connection connection;
        
       public ProveedorDAO(Connection connection){
        this.connection = connection;
    }
       
         //metodo get para obtener solo 1 cliente
    
    
    public Optional<Proveedor> get(int id){
        Proveedor proveedor = null;
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM proveedores WHERE idProveedor = ?")){
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                proveedor = new Proveedor();
                proveedor.setNombre(resultSet.getString("nombreProveedor"));
                proveedor.setTelefono(resultSet.getString("telefono"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(proveedor);
    }
        
    public List<Proveedor> getAll(){
        List<Proveedor> ListaDeProveedores = new ArrayList<>();
        try (Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery("SELECT * FROM proveedores");
            while(resultSet.next()){
                Proveedor proveedor = new Proveedor();
                proveedor.setIdProveedor(resultSet.getInt("idProveedor"));
                proveedor.setNombre(resultSet.getString("nombreProveedor"));
                proveedor.setTelefono(resultSet.getString("telefono"));

                ListaDeProveedores.add(proveedor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ListaDeProveedores;
    }
    
    
    
    //metodo para guardar un cliente
 
    public void save(Proveedor proveedor) {
        String sql = "INSERT INTO proveedores (nombreProveedor, telefono) VALUES (?, ?)";
        int generatedId = 0;
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, proveedor.getNombre());
            statement.setString(2, proveedor.getTelefono());
 
            int affectedRows = statement.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        generatedId = generatedKeys.getInt(1);
                        proveedor.setIdProveedor(generatedId);
                    }
                }
            }
        
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    
    
    //metodo para actualizar el cliente
      public void update(Proveedor proveedor) {
        String sql = "UPDATE proveedores SET nombreProveedor = ?, telefono = ? WHERE idProveedor = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, proveedor.getNombre());
            statement.setString(2, proveedor.getTelefono());
             statement.setLong(3, proveedor.getIdProveedor());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Updating socio failed, no rows affected.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
      
      
      
      
      //metodo para eliminar un socio
    public void delete(Proveedor proveedor){
        try{
            connection.setAutoCommit(false);
          
            try(PreparedStatement statement = connection.prepareStatement("DELETE FROM proveedores WHERE idProveedor = ?")){
                statement.setLong(1, proveedor.getIdProveedor());
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
