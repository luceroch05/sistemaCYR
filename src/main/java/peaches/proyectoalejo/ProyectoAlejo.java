/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package peaches.proyectoalejo;
import static peaches.proyectoalejo.util.Conexion.conectar;
import peaches.proyectoalejo.view.frameLogin;
import peaches.proyectoalejo.view.framePrincipal;
/**
 *
 * @author Lucero
 */
public class ProyectoAlejo {
    
    public static void main(String[] args) {
        
        conectar();
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
             frameLogin frameLogin = new frameLogin();
             frameLogin.setVisible(true);
              frameLogin.setLocationRelativeTo(null);                
                
                //new framePrincipal().setVisible(true);
            }
        });
    }
}
