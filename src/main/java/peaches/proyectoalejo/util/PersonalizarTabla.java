/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package peaches.proyectoalejo.util;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author santo
 */
public class PersonalizarTabla implements TableCellRenderer {
    
     DefaultTableCellRenderer renderer;

    public PersonalizarTabla(JTable table) {
        renderer = (DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        renderer.setBackground(Color.WHITE); // Establecer el color de fondo del encabezado en blanco
        renderer.setBorder(null); // Eliminar cualquier borde del encabezado
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        return renderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
}
