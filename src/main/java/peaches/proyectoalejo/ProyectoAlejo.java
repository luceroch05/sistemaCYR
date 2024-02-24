/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package peaches.proyectoalejo;
import peaches.proyectoalejo.view.framePrincipal;
import static peaches.proyectoalejo.util.Conexion.conectar;
/**
 *
 * @author Lucero
 */
public class ProyectoAlejo {
    
    public static void main(String[] args) {
        conectar();
        System.out.println("Hello World!");
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new framePrincipal().setVisible(true);
            }
        });
    }
}
