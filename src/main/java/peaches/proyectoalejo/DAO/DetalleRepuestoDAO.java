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
import peaches.proyectoalejo.model.DetalleRepuesto;
import peaches.proyectoalejo.model.Vehiculo;

/**
 *
 * @author santo
 */
public class DetalleRepuestoDAO implements DAO<DetalleRepuesto>{

    private Connection connection;
    
        public DetalleRepuestoDAO(Connection connection){
        this.connection = connection;
    }
         
           
   
        
    public List<DetalleRepuesto> getAll(){
        List<DetalleRepuesto> ListaDeDetalleRepuesto = new ArrayList<>();
        try (Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery("SELECT * FROM detallederepuesto");
            while(resultSet.next()){
               DetalleRepuesto detalle = new DetalleRepuesto();
               detalle.setIdVenta(resultSet.getString("idVenta"));
               detalle.setIdRepuesto(resultSet.getInt("idRepuesto"));
               detalle.setCantidad(resultSet.getInt("cantidad")); 
               detalle.setPrecioUnidad(resultSet.getDouble("precioUnidad"));              
               detalle.setTotal(resultSet.getDouble("total"));              

                ListaDeDetalleRepuesto.add(detalle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ListaDeDetalleRepuesto;
    }
    
    
    
    //metodo para guardar un cliente

public void save(DetalleRepuesto detalle) {
    String sql = "INSERT INTO detallederepuesto (idVenta, idRepuesto, cantidad, preciounidad, total) VALUES (?, ?, ?,?, ?)";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setString(1, detalle.getIdVenta());
        statement.setInt(2, detalle.getIdRepuesto());
        statement.setInt(3, detalle.getCantidad());
       statement.setDouble(4, detalle.getPrecioUnidad());
        statement.setDouble(5, detalle.getTotal());

        
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
      public void update(DetalleRepuesto vehiculo) {
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
      
      
      
      

public void delete(DetalleRepuesto detalleRepuesto) {
    try {
        connection.setAutoCommit(false);

        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM detallederepuesto WHERE idVenta = ? AND idRepuesto = ?")) {
            statement.setString(1, detalleRepuesto.getIdVenta());
            statement.setInt(2, detalleRepuesto.getIdRepuesto());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Deleting detalleRepuesto failed, no rows affected.");
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

 
public List<DetalleRepuesto> getAllByIdVenta(String idVenta) {
    List<DetalleRepuesto> ListaDeDetalleRepuesto = new ArrayList<>();
    String sql = "SELECT * FROM detallederepuesto WHERE idVenta = ?";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setString(1, idVenta);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            DetalleRepuesto detalle = new DetalleRepuesto();
            detalle.setIdVenta(resultSet.getString("idVenta"));
            detalle.setIdRepuesto(resultSet.getInt("idRepuesto"));
           detalle.setCantidad(resultSet.getInt("cantidad"));
            detalle.setPrecioUnidad(resultSet.getDouble("precioUnidad"));
            detalle.setTotal(resultSet.getDouble("total"));
            // Agrega el detalle a la lista
            ListaDeDetalleRepuesto.add(detalle);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return ListaDeDetalleRepuesto;
}


    
    
    
    
}
