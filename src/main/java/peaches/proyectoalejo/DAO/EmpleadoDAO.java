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
import peaches.proyectoalejo.model.Empleado;

/**
 *
 * @author santo
 */
public class EmpleadoDAO implements DAO<Empleado> {
    
        private Connection connection;
    
        //metodo para obtener la conexion a bd
      public EmpleadoDAO(Connection connection){
        this.connection = connection;
    }

    public Optional<Empleado> get(String dni){
        Empleado empleado = null;
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM empleado WHERE dniEmpleado = ?")){
            statement.setString(1, dni);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                empleado = new Empleado();
               empleado.setDniEmpleado(resultSet.getString("dniEmpleado"));

                empleado.setNombreEmpleado(resultSet.getString("nombreEmpleado"));
                empleado.setContrasena(resultSet.getString("contrasena"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(empleado);
    }
    
        @Override
    public List<Empleado> getAll() {
          List<Empleado> ListaDeEmpleado = new ArrayList<>();
        try (Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery("SELECT * FROM empleado");
            while(resultSet.next()){
                Empleado empleado = new Empleado();
                empleado.setDniEmpleado(resultSet.getString("dniEmpleado"));
                empleado.setNombreEmpleado(resultSet.getString("nombreEmpleado"));
                empleado.setContrasena(resultSet.getString("contrasena"));

                ListaDeEmpleado.add(empleado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ListaDeEmpleado;
    }

    @Override
    public void save(Empleado empleado) {
 String sql = "INSERT INTO Empleado (dniEmpleado, nombreEmpleado, contrasena) VALUES (?,?, ?)";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setString(1, empleado.getDniEmpleado());
        statement.setString(2, empleado.getNombreEmpleado());
        statement.setString(3, empleado.getContrasena());
        
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
    }    }

    @Override
    public void update(Empleado empleado) {
        
 String sql = "UPDATE empleado SET nombreEmpleado = ?, contrasena = ? WHERE dniEmpleado = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, empleado.getNombreEmpleado());
            statement.setString(2, empleado.getContrasena());
             statement.setString(3, empleado.getDniEmpleado());


            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Updating socio failed, no rows affected.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }    }

    @Override
    public void delete(Empleado empleado) {
        try{
            connection.setAutoCommit(false);
          
            try(PreparedStatement statement = connection.prepareStatement("DELETE FROM empleado WHERE dniEmpleado = ?")){
                statement.setString(1, empleado.getDniEmpleado());
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
