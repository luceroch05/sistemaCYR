/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package peaches.pelioficial.view;

import com.formdev.flatlaf.FlatLightLaf;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import peaches.pelioficial.util.Placeholders;

/**
 *
 * @author Lucero
 */
public class framePrincipal extends javax.swing.JFrame {
    private panelMenu panelMenu;
    
    public framePrincipal() {
        initComponents();
        panelMenu = new panelMenu(this);
     
        
        
        add(panelMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
        
        panelMenu.setVisible(false);
    }
    
    public void mostrarPanelCartelera(){
        panelMenu.setVisible(false);
 
    }
    
    public void mostrarPanelMenu(){
  
        panelMenu.setVisible(true);
    }
    
 int xMouse, yMouse;
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBarra = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        Fondo = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        txtPassword = new javax.swing.JPasswordField();
        jSeparator3 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelBarra.setBackground(new java.awt.Color(0, 0, 0));
        panelBarra.setPreferredSize(new java.awt.Dimension(920, 18));
        panelBarra.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                panelBarraMouseDragged(evt);
            }
        });
        panelBarra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelBarraMousePressed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imgs/Imgs Login/closeButton.png"))); // NOI18N
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelBarraLayout = new javax.swing.GroupLayout(panelBarra);
        panelBarra.setLayout(panelBarraLayout);
        panelBarraLayout.setHorizontalGroup(
            panelBarraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBarraLayout.createSequentialGroup()
                .addGap(0, 1180, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelBarraLayout.setVerticalGroup(
            panelBarraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
        );

        getContentPane().add(panelBarra, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, 20));

        Fondo.setBackground(new java.awt.Color(255, 255, 255));
        Fondo.setForeground(new java.awt.Color(255, 153, 153));
        Fondo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imgs/Imgs Login/Banner1.jpg"))); // NOI18N
        Fondo.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 0, -1, 720));

        jLabel2.setFont(new java.awt.Font("Poppins Black", 0, 60)); // NOI18N
        jLabel2.setText("¡Bienvenido!");
        Fondo.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 170, 430, 80));

        txtUsuario.setFont(new java.awt.Font("Poppins Medium", 0, 18)); // NOI18N
        txtUsuario.setForeground(new java.awt.Color(204, 204, 204));
        txtUsuario.setText("user");
        txtUsuario.setBorder(null);
        txtUsuario.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        Fondo.add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 330, 310, -1));
        txtUsuario.setForeground(Color.GRAY);
        txtUsuario.addFocusListener(new Placeholders("user", new Color(204, 204, 204), Color.BLACK));

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        Fondo.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 430, 310, 20));

        jLabel3.setFont(new java.awt.Font("Poppins SemiBold", 0, 20)); // NOI18N
        jLabel3.setText("INICIAR SESIÓN");
        Fondo.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 250, 230, 30));

        jButton1.setBackground(new java.awt.Color(0, 0, 0));
        jButton1.setFont(new java.awt.Font("Poppins ExtraBold", 0, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Ingresar");
        jButton1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setName(""); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        Fondo.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 490, 310, 40));

        txtPassword.setFont(new java.awt.Font("Poppins SemiBold", 0, 18)); // NOI18N
        txtPassword.setForeground(new java.awt.Color(204, 204, 204));
        txtPassword.setText("jPasswordField1");
        txtPassword.setBorder(null);
        Fondo.add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 400, 300, -1));
        txtPassword.setForeground(Color.GRAY);
        txtPassword.addFocusListener(new Placeholders("jPasswordField1", new Color(204, 204, 204), Color.BLACK));

        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));
        Fondo.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 360, 310, 30));

        getContentPane().add(Fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 1200, 720));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void panelBarraMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBarraMousePressed
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_panelBarraMousePressed

    private void panelBarraMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBarraMouseDragged
        int x = evt.getXOnScreen();
        int y  = evt.getYOnScreen();
        this.setLocation(x -xMouse, y- yMouse);
    }//GEN-LAST:event_panelBarraMouseDragged

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.setLayout(new BorderLayout());
        this.getContentPane().remove(Fondo);
        panelMenu.setVisible(true);
        this.revalidate();
        this.repaint();
    }//GEN-LAST:event_jButton1ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Fondo;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JPanel panelBarra;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
