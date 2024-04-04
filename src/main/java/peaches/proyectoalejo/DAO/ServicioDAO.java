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
import peaches.proyectoalejo.model.Servicio;

/**
 *
 * @author Lucero
 */
public class ServicioDAO implements DAO<Servicio> {
     private Connection connection;
    
        public ServicioDAO(Connection connection){
        this.connection = connection;
    }
        
        
    public Optional<Servicio> get(int idServicio){
        Servicio servicio= null;
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM servicio WHERE idServicio = ?")){
            statement.setInt(1, idServicio);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                servicio = new Servicio();
              
                servicio.setDescripcion(resultSet.getString("descripcion"));
                servicio.setPrecio(resultSet.getDouble("precio"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(servicio);
    }
        
        @Override
    public List<Servicio> getAll(){
        
          List<Servicio> ListaDeRepuesto = new ArrayList<>();
          
            try (Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery("SELECT * FROM servicio");
            while(resultSet.next()){
                Servicio servicio = new Servicio();
                servicio.setIdServicio(resultSet.getInt("idServicio"));
                servicio.setDescripcion(resultSet.getString("descripcion"));
                servicio.setPrecio(resultSet.getDouble("precio"));
       


                ListaDeRepuesto.add(servicio);
            }
        } catch (SQLException e) {
            e.printStackTrace();
                System.out.println("get all erorrrr");
        }
        return ListaDeRepuesto;
    }

    @Override
    public void save(Servicio servicio) {
        String sql = "INSERT INTO servicio (descripcion,precio) VALUES (?, ?)";
        int generatedId = 0;
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, servicio.getDescripcion());
             statement.setDouble(2, servicio.getPrecio());


            int affectedRows = statement.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        generatedId = generatedKeys.getInt(1);
                        servicio.setIdServicio(generatedId);
                    }
                }
            }
        
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error es akiiiiii");
        }    }

    public void update(Servicio servicio) {
        
    String sql = "UPDATE servicio SET descripcion = ?, precio = ? WHERE idServicio = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, servicio.getDescripcion()); 
            statement.setDouble(2, servicio.getPrecio());
              statement.setLong(3, servicio.getIdServicio());



            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("No se pudo actualizar el servicio");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }    }

    public void delete(Servicio servicio) {
         try{
            connection.setAutoCommit(false);
          
            try(PreparedStatement statement = connection.prepareStatement("DELETE FROM servicio WHERE idServicio = ?")){
                statement.setLong(1, servicio.getIdServicio());
                int affectedRows = statement.executeUpdate();
                if(affectedRows == 0){
                    throw new SQLException("El servicio no se pudo eliminar");
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
    
    
    

