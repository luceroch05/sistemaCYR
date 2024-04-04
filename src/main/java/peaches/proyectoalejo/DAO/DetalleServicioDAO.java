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
import peaches.proyectoalejo.model.DetalleRepuesto;
import peaches.proyectoalejo.model.DetalleServicio;

/**
 *
 * @author santo
 */
public class DetalleServicioDAO implements DAO<DetalleServicio>{
     private Connection connection;
    
        public DetalleServicioDAO(Connection connection){
        this.connection = connection;
    }
         
           
        
     
     @Override
    public List<DetalleServicio> getAll(){
        List<DetalleServicio> ListaDeDetalleServicio = new ArrayList<>();
        try (Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery("SELECT * FROM detalledeservicio");
            while(resultSet.next()){
               DetalleServicio detalle = new DetalleServicio();
               detalle.setIdVenta(resultSet.getString("idVenta"));
               detalle.setIdServicio(resultSet.getInt("idServicio"));
               detalle.setPrecio(resultSet.getDouble("precio")); 
                        

                ListaDeDetalleServicio.add(detalle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ListaDeDetalleServicio;
    }
    
    
    
    //metodo para guardar un cliente

public void save(DetalleServicio detalle) {
    String sql = "INSERT INTO detalledeservicio (idVenta, idservicio, precio) VALUES (?, ?, ?)";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setString(1, detalle.getIdVenta());
        statement.setInt(2, detalle.getIdServicio());
        statement.setDouble(3, detalle.getPrecio());

        
        // Ejecutar la inserción
        int affectedRows = statement.executeUpdate();
        if (affectedRows == 0) {
            System.out.println("¡Advertencia: No se ha insertado ninguna fila!");
            // Puedes decidir lanzar una excepción o manejar de otra manera esta situación.
        } else {
            System.out.println("¡detalle insertado exitosamente!");
            // Puedes imprimir un mensaje de éxito o realizar otras acciones necesarias.
        }
    } catch (SQLException e) {
        e.printStackTrace();
        // Manejar la excepción de manera adecuada, ya sea lanzando una excepción personalizada o manejando el error de otra manera según tus necesidades.
    }
}


    
    
    //metodo para actualizar el cliente
      public void update(DetalleServicio detalle) {
//        String sql = "UPDATE vehiculo SET modelo = ?, dni = ? WHERE placa = ?";
//        try (PreparedStatement statement = connection.prepareStatement(sql)) {
//            statement.setString(1, vehiculo.getModelo());
//            statement.setString(2, vehiculo.getDni());
//            statement.setString(3, vehiculo.getPlaca());
//
//            int affectedRows = statement.executeUpdate();
//            if (affectedRows == 0) {
//                throw new SQLException("Updating socio failed, no rows affected.");
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }
      
      
      
      

public void delete(DetalleServicio detalle) {
    try {
        connection.setAutoCommit(false);

        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM detalledeservicio WHERE idVenta = ? AND idServicio = ?")) {
            statement.setString(1, detalle.getIdVenta());
            statement.setInt(2, detalle.getIdServicio());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Deleting detalleServicio failed, no rows affected.");
            }
        }

        connection.commit();
    } catch (SQLException e) {
        e.printStackTrace();
        try {
            connection.rollback();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    } finally {
        try {
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

 public List<DetalleServicio> getAllByIdServicio(String idVenta) {
    List<DetalleServicio> ListaDeDetalleServicio = new ArrayList<>();
    String sql = "SELECT * FROM detalledeservicio WHERE idVenta = ?";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setString(1, idVenta);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            DetalleServicio detalle = new DetalleServicio();
            detalle.setIdVenta(resultSet.getString("idVenta"));
            detalle.setIdServicio(resultSet.getInt("idServicio"));
            detalle.setPrecio(resultSet.getDouble("precio"));
            // Agrega el detalle a la lista
            ListaDeDetalleServicio.add(detalle);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return ListaDeDetalleServicio;
}

    
    
}
