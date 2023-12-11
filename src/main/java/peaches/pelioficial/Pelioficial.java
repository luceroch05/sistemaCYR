/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package peaches.pelioficial;

import peaches.pelioficial.view.framePrincipal;
import java.awt.Font;
import static peaches.pelioficial.util.DatabaseConnector.conectar;
import peaches.pelioficial.util.FontUtil;

/**
 *
 * @author Lucero
 */
public class Pelioficial {

    public static void main(String[] args) {
        conectar();
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Font poppinsFont = FontUtil.loadFontFromResources("/Fonts/Poppins-Semibold.ttf", 16f);
                framePrincipal frame = new framePrincipal();
                FontUtil.applyFontToComponent(frame, poppinsFont);
                frame.setVisible(true);
            }
        });
    }
}
