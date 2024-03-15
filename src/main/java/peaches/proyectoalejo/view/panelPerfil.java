/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package peaches.proyectoalejo.view;

import java.util.List;
import javax.swing.JFrame;
import peaches.proyectoalejo.model.Empleado;
import peaches.proyectoalejo.service.EmpleadoService;
import peaches.proyectoalejo.update.upCliente;
import peaches.proyectoalejo.update.upEmpleado;

/**
 *
 * @author santo
 */
public class panelPerfil extends javax.swing.JPanel {
    
    EmpleadoService empleadoService = new EmpleadoService();
    Empleado empleadoEnUso;
    

    /**
     * Creates new form panelPerfil
     */
    public panelPerfil(Empleado empleadoEnUso) {

        
        this.empleadoEnUso = empleadoEnUso;
        initComponents(); // Debes llamar a initComponents() antes de acceder a los componentes
        
      if (empleadoEnUso == null) {
        System.out.println("El empleadoEnUso es nulo");
    } else {
        // El empleadoEnUso no es nulo, establecer los valores de los componentes con los datos del empleado
        lblDni.setText(empleadoEnUso.getDniEmpleado());
        lblNombre.setText(empleadoEnUso.getNombreEmpleado());
        lblContraseña.setText(empleadoEnUso.getContrasena());
    }
        // Establecer los valores de los componentes con los datos del empleado
   
    
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        lblContraseña = new javax.swing.JLabel();
        lblDni = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(650, 540));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Perfil");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, 90, 30));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Contraseña");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 260, 100, 20));

        lblNombre.setText("name");
        add(lblNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 230, 90, -1));

        jButton1.setText("EDITAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 360, 120, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("DNI");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 190, 100, 20));

        lblContraseña.setText("password");
        add(lblContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 260, 80, 20));

        lblDni.setText("dni");
        add(lblDni, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 190, 70, 20));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Usuario");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 220, 100, 20));
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    String dni = empleadoEnUso.getDniEmpleado();

        upEmpleado upEmpleado = new upEmpleado(dni);
        upEmpleado.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Configurar el comportamiento de cierre
        upEmpleado.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel lblContraseña;
    private javax.swing.JLabel lblDni;
    private javax.swing.JLabel lblNombre;
    // End of variables declaration//GEN-END:variables
}