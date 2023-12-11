/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package peaches.pelioficial.util;

/**
 *
 * @author mtx0v
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import peaches.pelioficial.model.Pelicula;

public class SeleccionPeliculaDialog extends JDialog {
    private JTable tablaPeliculas;
    private JButton btnSeleccionar, btnCancelar;
    private Pelicula peliculaSeleccionada;
    private List<Pelicula> listaPeliculas;

    public SeleccionPeliculaDialog(Frame parent, boolean modal, List<Pelicula> peliculas) {
        super(parent, modal);
        this.listaPeliculas = peliculas;
        setTitle("Seleccionar Película");
        setSize(300, 200);
        setLayout(new BorderLayout());
        
        tablaPeliculas = new JTable();
        
        String[] columnas = {"ID", "Título"};
        DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaPeliculas.setModel(modeloTabla);

        llenarTablaConPeliculas(modeloTabla, peliculas);

        JScrollPane scrollPane = new JScrollPane(tablaPeliculas);
        add(scrollPane, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnSeleccionar = new JButton("Seleccionar");
        btnCancelar = new JButton("Cancelar");

        panelBotones.add(btnSeleccionar);
        panelBotones.add(btnCancelar);
        add(panelBotones, BorderLayout.SOUTH);

        btnSeleccionar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = tablaPeliculas.getSelectedRow();
                if (filaSeleccionada != -1) {
                    peliculaSeleccionada = listaPeliculas.get(filaSeleccionada);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(SeleccionPeliculaDialog.this,
                                                  "Debes seleccionar una película.",
                                                  "Advertencia",
                                                  JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                peliculaSeleccionada = null;
                dispose();
            }
        });

        setLocationRelativeTo(parent);
    }

    public Pelicula getPeliculaSeleccionada() {
        return peliculaSeleccionada;
    }
    
    private void llenarTablaConPeliculas(DefaultTableModel modeloTabla, List<Pelicula> peliculas) {
        for (Pelicula pelicula : peliculas) {
            Object[] fila = new Object[] {
                pelicula.getPeliculaId(),
                pelicula.getTitulo()
            };
            modeloTabla.addRow(fila);
        }
    }
}

