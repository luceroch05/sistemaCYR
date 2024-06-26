/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package peaches.proyectoalejo.view;

import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import peaches.proyectoalejo.agregar.popRepuesto;
import peaches.proyectoalejo.model.Proveedor;
import peaches.proyectoalejo.model.Repuesto;
import peaches.proyectoalejo.service.ProveedorService;
import peaches.proyectoalejo.service.RepuestoService;
import peaches.proyectoalejo.update.upRepuesto;
import peaches.proyectoalejo.util.Oyente;

/**
 *
 * @author Lucero
 */
public class panelRepuesto extends javax.swing.JPanel implements Oyente {
    
    RepuestoService repuestoService = new RepuestoService();
    ProveedorService proveedorService = new ProveedorService();

    /**
     * Creates new form panelRepuesto
     */
    public panelRepuesto() {
        initComponents();
        actualizarTabla();

    }

    
       public void actualizarTabla(){
        List<Repuesto> ListaDeRepuestos = repuestoService.obtenerTodosLosRepuestos();
        DefaultTableModel model = (DefaultTableModel) tableRepuesto.getModel(); 
        model.setRowCount(0);
        for (Repuesto repuesto : ListaDeRepuestos) {
            
            Proveedor proveedor = proveedorService.obtenerProveedorPorId(repuesto.getIdProveedor());
            
            model.addRow(new Object[]{
                repuesto.getIdRepuesto(),
                repuesto.getDescripcion(),
                repuesto.getStock(),
                repuesto.getPrecio(),
                proveedor.getNombre(),

                
            });
        }
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableRepuesto = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        btnAgregarProveedores = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(650, 540));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 0, -1, -1));

        tableRepuesto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Descripción", "Stock", "Precio", "Proveedor"
            }
        ));
        jScrollPane1.setViewportView(tableRepuesto);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 180, 510, 290));

        jTextField1.setText("buscar");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 110, 350, 40));

        btnAgregarProveedores.setBackground(new java.awt.Color(0, 51, 51));
        btnAgregarProveedores.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregarProveedores.setText("+");
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
        add(btnAgregarProveedores, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, 130, 40));

        btnActualizar.setBackground(new java.awt.Color(0, 51, 51));
        btnActualizar.setForeground(new java.awt.Color(255, 255, 255));
        btnActualizar.setText("Actualizar");
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
        add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, 130, 30));

        btnEliminar.setBackground(new java.awt.Color(0, 51, 51));
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setText("Eliminar");
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
        add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 60, 130, 30));
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void btnAgregarProveedoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarProveedoresMouseClicked

    }//GEN-LAST:event_btnAgregarProveedoresMouseClicked

    private void btnAgregarProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarProveedoresActionPerformed
        popRepuesto repuesto = new popRepuesto(this);
        repuesto.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Configurar el comportamiento de cierre

        repuesto.setVisible(true);
        repuesto.setLocationRelativeTo(null); // Centra el JFrame en la pantalla

        // TODO add your handling code here:
    }//GEN-LAST:event_btnAgregarProveedoresActionPerformed

    private void btnActualizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnActualizarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnActualizarMouseClicked

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed

        int filaSeleccionada = tableRepuesto.getSelectedRow();
        if (filaSeleccionada != -1) { // Verifica si se ha seleccionado una fila
            int idRepuesto = Integer.parseInt(tableRepuesto.getValueAt(filaSeleccionada, 0).toString());
            upRepuesto upRe = new upRepuesto(this, idRepuesto);
            upRe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Configurar el comportamiento de cierre
            upRe.setVisible(true);
            upRe.setLocationRelativeTo(null); // Centra el JFrame en la pantalla

        } else {
            JOptionPane.showMessageDialog(null, "No se seleccionó ninguna fila", "Error Actualizar", JOptionPane.INFORMATION_MESSAGE);

        }

        // TODO add your handling code here:
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnEliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEliminarMouseClicked

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int fila = tableRepuesto.getSelectedRow();
        if(fila != -1){
            int idRepuesto = Integer.parseInt(tableRepuesto.getValueAt(fila, 0).toString());
            int confirmacion = JOptionPane.showConfirmDialog(null, "Estás seguro de que deseas eliminar el repuesto seleccionado?", "Eliminar Socio", JOptionPane.YES_NO_OPTION);
            if(confirmacion == JOptionPane.YES_OPTION){
                Repuesto repuestoParaEliminar = new Repuesto();
                repuestoParaEliminar.setIdRepuesto(idRepuesto);
                repuestoService.eliminarRepuesto(repuestoParaEliminar);
                actualizarTabla();
                JOptionPane.showMessageDialog(null, "Repuesto eliminado con exito.", "Eliminar Repuesto", JOptionPane.INFORMATION_MESSAGE);

            }
        }else{
            JOptionPane.showMessageDialog(null, "Por favor, seleccione un repuesto de la tabla.", "Eliminar Repuesto", JOptionPane.ERROR_MESSAGE);
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_btnEliminarActionPerformed

   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAgregarProveedores;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable tableRepuesto;
    // End of variables declaration//GEN-END:variables

    @Override
    public void anadido() {
        actualizarTabla();
    }
}
