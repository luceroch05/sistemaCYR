/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package peaches.proyectoalejo.DAO;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author santo
 */
public interface DAO<T> {
    
    Optional<T> get(String dni);
    List<T> getAll();
    void save (T t);
    void update(T t, String[] param);
    void delete(T t);
    
    
}
