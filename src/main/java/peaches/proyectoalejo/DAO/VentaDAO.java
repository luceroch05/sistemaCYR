/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package peaches.proyectoalejo.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import peaches.proyectoalejo.model.Venta;

/**
 *
 * @author santo
 */
public class VentaDAO implements DAO<Venta>{
    
        private Connection connection;
    
        //metodo para obtener la conexion a bd
      public VentaDAO(Connection connection){
        this.connection = connection;
    }

    @Override
    public List<Venta> getAll() {
         List<Venta> ListaDeVentas = new ArrayList<>();
        try (Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery("SELECT * FROM venta");
            while(resultSet.next()){
                Venta venta = new Venta();
                venta.setIdVenta(resultSet.getString("idVenta"));
                venta.setDni(resultSet.getString("dni"));
                Date fecha = resultSet.getDate("fecha");
                venta.setFecha(fecha != null ? fecha.toLocalDate() : null);
              // venta.setFecha(resultSet.getDate("fecha").toLocalDate());
                venta.setDniEmpleado(resultSet.getString("dniEmpleado"));

                
                ListaDeVentas.add(venta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ListaDeVentas;
    }


public String save2(Venta venta) {
    String idVentaGenerado = null; // Inicializar el ID generado como null por defecto

    String sql = "{CALL usp_nueva_Venta(?, ?, ?, ?)}"; // Incluir el parámetro de salida en la llamada al procedimiento
    try (CallableStatement statement = connection.prepareCall(sql)) {
        statement.setString(1, venta.getDni());
        statement.setDate(2, Date.valueOf(venta.getFecha()));
        statement.setString(3, venta.getDniEmpleado());
        statement.registerOutParameter(4, Types.VARCHAR); // Registrar el parámetro de salida

        // Ejecutar la inserción
        statement.executeUpdate();
        
        // Obtener el valor del parámetro de salida
        idVentaGenerado = statement.getString(4);

        if (idVentaGenerado != null) {
            System.out.println("¡Venta insertada exitosamente! ID de venta: " + idVentaGenerado);
        } else {
            System.out.println("¡No se pudo obtener el ID de la venta generada!");
        }
    } catch (SQLException e) {
        e.printStackTrace();
        // Manejar la excepción de manera adecuada, ya sea lanzando una excepción personalizada o manejando el error de otra manera según tus necesidades.
    }
    
    return idVentaGenerado;
}


    @Override
    public void update(Venta venta) {
         String sql = "UPDATE venta SET dni = ?, fecha = ?, dniEmpleado = ? WHERE idVenta = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, venta.getDni());
            statement.setDate(2, Date.valueOf(venta.getFecha()));
            statement.setString(3, venta.getDniEmpleado());
            statement.setString(4, venta.getIdVenta());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Updating venta failed, no rows affected.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }

    @Override
    public void delete(Venta venta) {
        
                try{
            connection.setAutoCommit(false);
          
            try(PreparedStatement statement = connection.prepareStatement("DELETE FROM venta WHERE idVenta = ?")){
                statement.setString(1, venta.getIdVenta());
                int affectedRows = statement.executeUpdate();
                if(affectedRows == 0){
                    throw new SQLException("Deleting venta failed, no rows affected.");
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

    @Override
    public void save(Venta t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public Optional<Venta> getAllById(String idVenta) {
         Venta venta = new Venta();
          try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM venta WHERE idVenta = ?")){
            statement.setString(1, idVenta);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                venta.setDni(resultSet.getString("dni"));
                Date fecha = resultSet.getDate("fecha");
                venta.setFecha(fecha != null ? fecha.toLocalDate() : null);;

                venta.setDniEmpleado(resultSet.getString("dniEmpleado"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(venta);
            }
       

    
}
