/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package peaches.proyectoalejo.view;

import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import peaches.proyectoalejo.agregar.popProveedor;
import peaches.proyectoalejo.model.Proveedor;
import peaches.proyectoalejo.service.ProveedorService;
import java.util.List;
import javax.swing.JOptionPane;
import peaches.proyectoalejo.update.upProveedor;
import peaches.proyectoalejo.util.Oyente;
import peaches.proyectoalejo.util.PersonalizarTabla;


/**
 *
 * @author Lucero
 */
public class panelProveedores extends javax.swing.JPanel implements Oyente {
    
    ProveedorService proveedorService = new ProveedorService();
   

    /**
     * Creates new form panelProveedores
     */
    public panelProveedores() {
        initComponents();
        actualizarTabla();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableProveedores = new javax.swing.JTable();
        btnAgregarProveedores = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setMinimumSize(new java.awt.Dimension(650, 540));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextField1.setText("buscar");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jPanel2.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 90, 260, 30));

        tableProveedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Nombre", "Telefono"
            }
        ));
        jScrollPane1.setViewportView(tableProveedores);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 160, -1, 340));

        btnAgregarProveedores.setBackground(new java.awt.Color(0, 51, 51));
        btnAgregarProveedores.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregarProveedores.setText("AGREGAR");
        btnAgregarProveedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAgregarProveedoresMouseClicked(evt);
            }
        });
        btnAgregarProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarProveedoresActionPerformed(evt);
            }
        });
        jPanel2.add(btnAgregarProveedores, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, 160, 30));

        btnEliminar.setBackground(new java.awt.Color(0, 51, 51));
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setText("ELIMINAR");
        btnEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEliminarMouseClicked(evt);
            }
        });
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel2.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, 160, 30));

        btnActualizar.setBackground(new java.awt.Color(0, 51, 51));
        btnActualizar.setForeground(new java.awt.Color(255, 255, 255));
        btnActualizar.setText("EDITAR");
        btnActualizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnActualizarMouseClicked(evt);
            }
        });
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        jPanel2.add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 40, 140, 30));

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 650, 540));
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void btnAgregarProveedoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarProveedoresMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAgregarProveedoresMouseClicked

    private void btnAgregarProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarProveedoresActionPerformed

        popProveedor proveedor = new popProveedor(this);
        proveedor.setVisible(true);
        proveedor.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Configurar el comportamiento de cierre
        proveedor.setLocationRelativeTo(null); // Centra el JFrame en la pantalla

        // TODO add your handling code here:
    }//GEN-LAST:event_btnAgregarProveedoresActionPerformed

    private void btnEliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEliminarMouseClicked

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int fila = tableProveedores.getSelectedRow();
        if(fila != -1){
            int idProveedor = Integer.parseInt(tableProveedores.getValueAt(fila, 0).toString());
            int confirmacion = JOptionPane.showConfirmDialog(null, "Estás seguro de que deseas eliminar el proveedor seleccionado?", "Eliminar Proveedor", JOptionPane.YES_NO_OPTION);
            if(confirmacion == JOptionPane.YES_OPTION){
                Proveedor proveedorParaEliminar = new Proveedor();
                proveedorParaEliminar.setIdProveedor(idProveedor);
                proveedorService.eliminarProveedor(proveedorParaEliminar);
                actualizarTabla();
                JOptionPane.showMessageDialog(null, "Proveedor eliminado con exito.", "Eliminar Proveedor", JOptionPane.INFORMATION_MESSAGE);

            }
        }else{
            JOptionPane.showMessageDialog(null, "Por favor, seleccione un proveedor de la tabla.", "Eliminar Proveedor", JOptionPane.ERROR_MESSAGE);
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnActualizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnActualizarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnActualizarMouseClicked

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed

        int filaSeleccionada = tableProveedores.getSelectedRow();
        if (filaSeleccionada != -1) { // Verifica si se ha seleccionado una fila
        int idProveedor = Integer.parseInt(tableProveedores.getValueAt(filaSeleccionada, 0).toString());
            upProveedor upProv = new upProveedor(idProveedor, this);
            upProv.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Configurar el comportamiento de cierre
            upProv.setVisible(true);
            upProv.setLocationRelativeTo(null); // Centra el JFrame en la pantalla

        } else {
            JOptionPane.showMessageDialog(null, "No se seleccionó ninguna fila para actualizar", "Error Actualizar", JOptionPane.INFORMATION_MESSAGE);

        }

        // TODO add your handling code here:
    }//GEN-LAST:event_btnActualizarActionPerformed

    
    void actualizarTabla(){
         List<Proveedor> ListadeProveedores = proveedorService.obtenerTodosLosProveedores();
        DefaultTableModel model = (DefaultTableModel) tableProveedores.getModel();
        model.setRowCount(0);
        for (Proveedor proveedor : ListadeProveedores) {
            
            model.addRow(new Object[]{
                proveedor.getIdProveedor(),
                proveedor.getNombre(),
                proveedor.getTelefono(),
                
            });
        }
    };
    
        public void anadido() {
        actualizarTabla();
        System.out.println("comunico con el proveedor");

    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAgregarProveedores;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable tableProveedores;
    // End of variables declaration//GEN-END:variables
}
