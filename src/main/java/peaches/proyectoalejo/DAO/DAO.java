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
    
    List<T> getAll();
    void save (T t);
    void update(T t);
    void delete(T t);
    
    
}
