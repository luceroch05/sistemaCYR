/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package peaches.proyectoalejo.view;

  

import java.awt.Color;
import java.awt.Font;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import peaches.proyectoalejo.agregar.popCliente;
import peaches.proyectoalejo.model.Cliente;
import peaches.proyectoalejo.service.ClienteService;
import peaches.proyectoalejo.update.upCliente;
import peaches.proyectoalejo.util.Conexion;
import peaches.proyectoalejo.util.Oyente;
import peaches.proyectoalejo.util.PersonalizarTabla;

/**
 *
 * @author Lucero
 */
public class panelCliente extends javax.swing.JPanel implements Oyente {
     ClienteService clienteService = new ClienteService();
      PersonalizarTabla personalizarTabla;
    /**
     * Creates new form panelCliente
     */
    public panelCliente() {
        //this.personalizarTabla = new PersonalizarTabla(tableClientes);
        initComponents();
        actualizarTabla();
        tableClientes.getTableHeader().setBackground(new Color(255,255,255));
        tableClientes.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD,12));
        
    //tableClientes.setDefaultRenderer(Object.class, personalizarTabla); // aplica el personalizador a la tabla
        
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tableClientes = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        btnAgregar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(650, 540));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(null);

        tableClientes.setBackground(new java.awt.Color(255, 255, 255));
        tableClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "DNI", "Nombre", "Apellido", "Teléfono"
            }
        ));
        tableClientes.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tableClientes.setGridColor(new java.awt.Color(255, 255, 255));
        tableClientes.setRowHeight(25);
        tableClientes.setShowHorizontalLines(true);
        tableClientes.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tableClientes);
        if (tableClientes.getColumnModel().getColumnCount() > 0) {
            tableClientes.getColumnModel().getColumn(0).setResizable(false);
            tableClientes.getColumnModel().getColumn(0).setPreferredWidth(10);
            tableClientes.getColumnModel().getColumn(1).setResizable(false);
            tableClientes.getColumnModel().getColumn(1).setPreferredWidth(10);
            tableClientes.getColumnModel().getColumn(2).setResizable(false);
            tableClientes.getColumnModel().getColumn(2).setPreferredWidth(10);
            tableClientes.getColumnModel().getColumn(3).setResizable(false);
            tableClientes.getColumnModel().getColumn(3).setPreferredWidth(10);
        }

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, 550, 330));

        jTextField1.setText("buscar");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 90, 210, 30));

        btnAgregar.setBackground(new java.awt.Color(0, 51, 51));
        btnAgregar.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregar.setText("Agregar");
        btnAgregar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAgregarMouseClicked(evt);
            }
        });
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        jPanel1.add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, 130, 30));

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
        jPanel1.add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 90, 130, 30));

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
        jPanel1.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, 130, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void btnActualizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnActualizarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnActualizarMouseClicked

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
       
    int filaSeleccionada = tableClientes.getSelectedRow();
    if (filaSeleccionada != -1) { // Verifica si se ha seleccionado una fila
        String dni = (String) tableClientes.getValueAt(filaSeleccionada, 0); // Suponiendo que la primera columna es el DNI
     upCliente upCli = new upCliente(dni, this);
        upCli.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Configurar el comportamiento de cierre
        upCli.setVisible(true);
        actualizarTabla();
    } else {
        JOptionPane.showMessageDialog(null, "No se seleccionó ninguna fila", "Error Actualizar", JOptionPane.INFORMATION_MESSAGE);

    }
                   
        
     // TODO add your handling code here:
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnEliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEliminarMouseClicked

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
       int fila = tableClientes.getSelectedRow();
        if(fila != -1){
            String dniCliente = tableClientes.getValueAt(fila, 0).toString();
            int confirmacion = JOptionPane.showConfirmDialog(null, "Estás seguro de que deseas eliminar el socio seleccionado?", "Eliminar Socio", JOptionPane.YES_NO_OPTION);
            if(confirmacion == JOptionPane.YES_OPTION){
                Cliente clienteParaEliminar = new Cliente();
                clienteParaEliminar.setDNI(dniCliente);
                clienteService.eliminarCliente(clienteParaEliminar);
                actualizarTabla();
                JOptionPane.showMessageDialog(null, "Socio eliminado con exito.", "Eliminar Socio", JOptionPane.INFORMATION_MESSAGE);
                
            
            }
        }else{
            JOptionPane.showMessageDialog(null, "Por favor, seleccione un socio de la tabla.", "Eliminar Socio", JOptionPane.ERROR_MESSAGE);
        }
    

        // TODO add your handling code here:
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        popCliente cliente = new popCliente(this);
        cliente.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Configurar el comportamiento de cierre
        cliente.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnAgregarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarMouseClicked

    }//GEN-LAST:event_btnAgregarMouseClicked

        public void actualizarTabla(){
        List<Cliente> ListaDeClientes = clienteService.obtenerTodosLosClientes();
        DefaultTableModel model = (DefaultTableModel) tableClientes.getModel();
        model.setRowCount(0);
        for (Cliente cliente : ListaDeClientes) {
            
            model.addRow(new Object[]{
                cliente.getDNI(),
                cliente.getNombre(),
                cliente.getApellido(),
                cliente.getTelefono(),
                
            });
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable tableClientes;
    // End of variables declaration//GEN-END:variables

    @Override
    public void anadido() {
        actualizarTabla();
       System.out.println("se comunicooo");

    }

  
    
}
