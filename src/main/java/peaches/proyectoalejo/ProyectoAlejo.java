/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package peaches.proyectoalejo;

/**
 *
 * @author Lucero
 */
public class ProyectoAlejo {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new framePrincipal().setVisible(true);
            }
        });
    }
}
