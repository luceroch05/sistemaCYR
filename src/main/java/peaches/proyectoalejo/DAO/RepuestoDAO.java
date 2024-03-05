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
import peaches.proyectoalejo.model.Repuesto;

/**
 *
 * @author santo
 */
public class RepuestoDAO implements DAO<Repuesto> {
    
    
        private Connection connection;
    
        public RepuestoDAO(Connection connection){
        this.connection = connection;
    }

    
    public Optional<Repuesto> get(int id) {
  Repuesto repuesto = null;
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM repuesto WHERE idRepuesto = ?")){
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            
            if(resultSet.next()){
                repuesto = new Repuesto();
                repuesto.setDescripcion(resultSet.getString("descripcion"));
                repuesto.setStock(resultSet.getInt("stock"));
                repuesto.setPrecio(resultSet.getDouble("precio"));
                repuesto.setIdProveedor(resultSet.getInt("idProveedor"));


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(repuesto);  
    }
    

        @Override
    public List<Repuesto> getAll(){
        
          List<Repuesto> ListaDeRepuesto = new ArrayList<>();
          
            try (Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery("SELECT * FROM repuesto");
            while(resultSet.next()){
                Repuesto repuesto = new Repuesto();
                repuesto.setIdRepuesto(resultSet.getInt("idRepuesto"));
                repuesto.setDescripcion(resultSet.getString("descripcion"));
                repuesto.setStock(resultSet.getInt("stock"));
                repuesto.setPrecio(resultSet.getDouble("precio"));
               repuesto.setIdProveedor(resultSet.getInt("idProveedor"));


                ListaDeRepuesto.add(repuesto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
                System.out.println("get all erorrrr");
        }
        return ListaDeRepuesto;
    }

    @Override
    public void save(Repuesto repuesto) {
        String sql = "INSERT INTO repuesto (descripcion, stock, precio, idProveedor) VALUES (?, ?, ?, ?)";
        int generatedId = 0;
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, repuesto.getDescripcion());
            statement.setLong(2, repuesto.getStock());
             statement.setDouble(3, repuesto.getPrecio());
            statement.setLong(4, repuesto.getIdProveedor());

            int affectedRows = statement.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        generatedId = generatedKeys.getInt(1);
                        repuesto.setIdProveedor(generatedId);
                    }
                }
            }
        
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error es akiiiiii");
        }    }

    public void update(Repuesto repuesto) {
        
  String sql = "UPDATE repuesto SET descripcion = ?, stock = ?, precio = ?, idProveedor =? WHERE idRepuesto = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, repuesto.getDescripcion());
            statement.setLong(2, repuesto.getStock());
            statement.setDouble(3, repuesto.getPrecio());
             statement.setLong(4, repuesto.getIdProveedor());
              statement.setLong(5, repuesto.getIdRepuesto());



            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("No se pudo actualizar el repuesto");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }    }

    public void delete(Repuesto repuesto) {
         try{
            connection.setAutoCommit(false);
          
            try(PreparedStatement statement = connection.prepareStatement("DELETE FROM repuesto WHERE idRepuesto = ?")){
                statement.setLong(1, repuesto.getIdRepuesto());
                int affectedRows = statement.executeUpdate();
                if(affectedRows == 0){
                    throw new SQLException("El Repuesto no se pudo eliminar");
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
