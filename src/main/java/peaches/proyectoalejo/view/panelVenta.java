/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package peaches.proyectoalejo.view;

import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import peaches.proyectoalejo.dialog.DialogBuscarRepuesto;
import peaches.proyectoalejo.dialog.DialogBuscarServicio;
import peaches.proyectoalejo.model.Cliente;
import peaches.proyectoalejo.model.DetalleRepuesto;
import peaches.proyectoalejo.model.DetalleServicio;
import peaches.proyectoalejo.model.Repuesto;
import peaches.proyectoalejo.model.Servicio;
import peaches.proyectoalejo.model.Venta;
import peaches.proyectoalejo.service.ClienteService;
import peaches.proyectoalejo.service.DetalleRepuestoService;
import peaches.proyectoalejo.service.DetalleServicioService;
import peaches.proyectoalejo.service.RepuestoService;
import peaches.proyectoalejo.service.ServicioService;
import peaches.proyectoalejo.service.VentaService;
import peaches.proyectoalejo.util.Oyente;

/**
 *
 * @author santo
 */
public class panelVenta extends javax.swing.JPanel implements Oyente{
    
        DetalleServicioService detSerService = new DetalleServicioService();
        ServicioService serService = new ServicioService();
        ClienteService clienteService = new ClienteService();
    
    DetalleRepuestoService detRepService = new DetalleRepuestoService();
    RepuestoService repService = new RepuestoService();
    String idVenta;
    VentaService ventaService = new VentaService();

    /**
     * Creates new form panelVenta
     */
    public panelVenta() {
        initComponents();
    }
    
        public panelVenta(String idVenta) {
        initComponents();
        this.idVenta = idVenta;
        lblIdVenta.setText(idVenta);
        
      Venta venta = ventaService.obtenerVentaPorId(idVenta);
      Cliente cliente = clienteService.obtenerClientePorDni(venta.getDni());
        
        lblNombreApellido.setText(cliente.getNombre() + " " + cliente.getApellido());
        

    }
        
     void eliminarDetallesVenta(){   
         
      if (idVenta != null && !idVenta.isEmpty()) {  
        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Estás seguro que deseas eliminar esta venta?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        
        if (confirmacion == JOptionPane.YES_OPTION) {
            
            Venta ventaParaEliminar = new Venta();
            ventaParaEliminar.setIdVenta(idVenta);
            
            //eliminando los detalle Repuestos
            
        List<DetalleRepuesto> ListaDeDetallePorId=  detRepService.obtenerTodosDetalleRepuestosPorVenta(idVenta);
        
        for(DetalleRepuesto detalle : ListaDeDetallePorId){
            detRepService.eliminarDetalleRepuesto(detalle);
        }
        
                    //eliminando los detalle Servicio

        List<DetalleServicio> ListaDeServicioPorId =  detSerService.obtenerTodosDetalleServicioPorVenta(idVenta);
        
        for(DetalleServicio detalle : ListaDeServicioPorId){
            detSerService.eliminarDetalleServicio(detalle);
        }
        
        //eliminando la venta
         ventaService.eliminarVenta(ventaParaEliminar);

            JOptionPane.showMessageDialog(this, "Venta eliminada exitosamente", "Venta eliminada", JOptionPane.INFORMATION_MESSAGE);
        }
    }
      else {
        JOptionPane.showMessageDialog(this, "No se pudo obtener el ID de la venta a eliminar", "Error", JOptionPane.ERROR_MESSAGE);
                 }
      
        }
     
     
        
            void actualizarTablaRepuesto(){


              List<DetalleRepuesto> ListaDetalleRepuesto = detRepService.obtenerTodosDetalleRepuestosPorVenta(idVenta);
            DefaultTableModel model = (DefaultTableModel) tableDetalleRepuestos.getModel();
            model.setRowCount(0);
            for (DetalleRepuesto detalle : ListaDetalleRepuesto) {

                          Repuesto  repuestoEnUso = repService.obtenerRepuestoPorId(detalle.getIdRepuesto());


                model.addRow(new Object[]{
                    repuestoEnUso.getDescripcion(),
                    detalle.getPrecioUnidad(),
                    detalle.getCantidad(),
                    detalle.getTotal(),

                });
            }

            }
            
            void actualizarTablaServicio(){


              List<DetalleServicio> ListaDetalleServicio = detSerService.obtenerTodosDetalleServicioPorVenta(idVenta);
            DefaultTableModel model = (DefaultTableModel) tableDetalleServicios.getModel();
            model.setRowCount(0);
            for (DetalleServicio detalle : ListaDetalleServicio) {

                          Servicio servicioEnUso = serService.obtenerServicioPorId(detalle.getIdServicio());


                model.addRow(new Object[]{
                    servicioEnUso.getDescripcion(),
                    detalle.getPrecio()

                });
            }

            }

   public void agregarRepuesto(String id, String descripcion, int stock, double precio) {
    // Agregar lógica para agregar repuesto a la tabla
    
    DefaultTableModel model = (DefaultTableModel) tableDetalleRepuestos.getModel();
    model.addRow(new Object[]{id, descripcion, stock, precio});
}


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblIdVenta = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnSeleccionarServicio = new javax.swing.JButton();
        btnSeleccionarRepuesto = new javax.swing.JButton();
        btnProcesarVenta = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableDetalleRepuestos = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableDetalleServicios = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        lblNombreApellido = new javax.swing.JLabel();
        btnEliminar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(650, 540));
        setPreferredSize(new java.awt.Dimension(650, 540));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblIdVenta.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        lblIdVenta.setForeground(new java.awt.Color(0, 0, 0));
        add(lblIdVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 40, 210, 30));

        jLabel3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel3.setText("Servicio");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 430, 30));

        btnSeleccionarServicio.setText("SELECCIONAR");
        btnSeleccionarServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarServicioActionPerformed(evt);
            }
        });
        add(btnSeleccionarServicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 310, 120, 30));

        btnSeleccionarRepuesto.setText("SELECCIONAR");
        btnSeleccionarRepuesto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarRepuestoActionPerformed(evt);
            }
        });
        add(btnSeleccionarRepuesto, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 120, 120, 30));

        btnProcesarVenta.setText("PROCESAR");
        btnProcesarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProcesarVentaActionPerformed(evt);
            }
        });
        add(btnProcesarVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 530, 140, 30));

        tableDetalleRepuestos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Descripcion", "Precio", "Cantidad", "Total"
            }
        ));
        jScrollPane1.setViewportView(tableDetalleRepuestos);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 570, 140));

        tableDetalleServicios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Descripcion", "Precio"
            }
        ));
        jScrollPane2.setViewportView(tableDetalleServicios);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 570, 150));

        jLabel2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel2.setText("Repuesto");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 70, 30));

        lblNombreApellido.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        add(lblNombreApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 70, 210, 30));

        btnEliminar.setText("ELIMINAR");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 530, 140, 30));

        jLabel5.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel5.setText("BOLETA ");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 370, 30));

        jLabel6.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel6.setText("Cliente");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 300, 30));
    }// </editor-fold>//GEN-END:initComponents

    private void btnSeleccionarRepuestoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarRepuestoActionPerformed

  DialogBuscarRepuesto repuesto = new DialogBuscarRepuesto((JFrame) SwingUtilities.getWindowAncestor(this), true, idVenta, this );
    repuesto.setVisible(true);
    repuesto.setLocationRelativeTo(null); // Centra el JFrame en la pantalla

    
    
    }//GEN-LAST:event_btnSeleccionarRepuestoActionPerformed

    private void btnSeleccionarServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarServicioActionPerformed
      DialogBuscarServicio servicio = new DialogBuscarServicio((JFrame) SwingUtilities.getWindowAncestor(this), true ,idVenta, this);
       servicio.setVisible(true);// TODO add your handling code here: 
        servicio.setLocationRelativeTo(null); // Centra el JFrame en la pantalla

// TODO add your handling code here:
    }//GEN-LAST:event_btnSeleccionarServicioActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        
        eliminarDetallesVenta();
        actualizarTablaRepuesto();
        actualizarTablaServicio();

       lblIdVenta.setText("");
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnProcesarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProcesarVentaActionPerformed
       
                    //repService.actualizarStock(idRepuesto, cantidad);


// TODO add your handling code here:
    }//GEN-LAST:event_btnProcesarVentaActionPerformed

    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnProcesarVenta;
    private javax.swing.JButton btnSeleccionarRepuesto;
    private javax.swing.JButton btnSeleccionarServicio;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblIdVenta;
    private javax.swing.JLabel lblNombreApellido;
    private javax.swing.JTable tableDetalleRepuestos;
    private javax.swing.JTable tableDetalleServicios;
    // End of variables declaration//GEN-END:variables

    @Override
    public void anadido() {
actualizarTablaRepuesto();
actualizarTablaServicio();
    }
}
