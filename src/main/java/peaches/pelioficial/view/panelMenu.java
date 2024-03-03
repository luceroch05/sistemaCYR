/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package peaches.pelioficial.view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Window;
import java.awt.event.KeyEvent;
//import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import peaches.pelioficial.model.Actor;
import peaches.pelioficial.model.Cinta;
import peaches.pelioficial.model.Director;
import peaches.pelioficial.model.Genero;
import peaches.pelioficial.model.ListaDeEspera;
import peaches.pelioficial.model.Pelicula;
import peaches.pelioficial.model.Prestamo;
import peaches.pelioficial.model.Socio;
import peaches.pelioficial.service.ActorService;
import peaches.pelioficial.service.CintaService;
import peaches.pelioficial.service.DirectorService;
import peaches.pelioficial.service.ListaEsperaService;
import peaches.pelioficial.service.PeliculaService;
import peaches.pelioficial.service.PrestamoService;
import peaches.pelioficial.service.SocioService;
import peaches.pelioficial.util.DatabaseConnector;
import peaches.pelioficial.util.Placeholders;
import peaches.pelioficial.util.SeleccionElementosDialog;
import peaches.pelioficial.util.SeleccionPeliculaDialog;

/**
 *
 * @author Lucero
 */
public class panelMenu extends javax.swing.JPanel {
    int xMouse,yMouse;
    SocioService socioService = new SocioService();
    PeliculaService peliculaService = new PeliculaService(DatabaseConnector.conectar());
    DirectorService directorService = new DirectorService(DatabaseConnector.conectar());
    ActorService actorService = new ActorService(DatabaseConnector.conectar());
    CintaService cintaService = new CintaService();
    PrestamoService prestamoService = new PrestamoService(DatabaseConnector.conectar());
    ListaEsperaService listaEsperaService = new ListaEsperaService();
    private framePrincipal framePrincipal;
        
    private Set<Director> directoresSeleccionados = new HashSet<>();
    private Set<Actor> actoresSeleccionados = new HashSet<>();
    private Set<Genero> generosSeleccionados = new HashSet<>();
    private List<Director> listaDirectores = directorService.obtenerTodosLosDirectores();
    private List<Actor> listaActores = actorService.obtenerTodosLosActores();
    private List<Genero> listaGeneros = peliculaService.obtenerTodosLosGeneros();
    private Prestamo prestamoSeleccionado;
    private List<Genero> generosSeleccionadosPeliculas = new ArrayList<>();
        
    private int peliculaLDESeleccionadaId = -1;
        
    public panelMenu(framePrincipal framePrincipal) {
        initComponents();
        this.framePrincipal = framePrincipal;
    }
     
    public JTabbedPane getTabbedPane(){
        return tabbedPane;
    }
    
    public void actualizarTablaDirectores(){
        List<Director> directores = directorService.obtenerTodosLosDirectores();
        DefaultTableModel model = (DefaultTableModel) tableDirectores.getModel();
        model.setRowCount(0);
        for(Director director : directores){
            model.addRow(new Object[]{director.getDirectorId(), director.getNombre()});
        }
    }
    
    public void actualizarTablaActores(){
        List<Actor> actores = actorService.obtenerTodosLosActores();
        DefaultTableModel model = (DefaultTableModel) tableActores.getModel();
        model.setRowCount(0);
        for(Actor actor : actores){
            model.addRow(new Object[]{actor.getActorId(), actor.getNombre()});
        }
    }
    
    public void actualizarTablaSocios() {
        DefaultTableModel model = (DefaultTableModel) tableSocios.getModel();
        model.setRowCount(0);
        List<Socio> listaSocios = socioService.obtenerTodosLosSocios();
        for (Socio socio : listaSocios) {
            String directores = String.join(", ", socio.getDirectoresFavoritos().stream()
                                                       .map(Director::getNombre)
                                                       .collect(Collectors.toList()));
            String actores = String.join(", ", socio.getActoresFavoritos().stream()
                                                    .map(Actor::getNombre)
                                                    .collect(Collectors.toList()));
            String generos = String.join(", ", socio.getGenerosFavoritos().stream()
                                                    .map(Genero::getNombre)
                                                    .collect(Collectors.toList()));

            model.addRow(new Object[]{
                socio.getSocioId(),
                socio.getNombre(),
                socio.getDireccion(),
                socio.getTelefono(),
                directores,
                actores,
                generos
            });
        }
    }
    
    private void cargarDatosSocios(int idSocio){
        Socio socio = socioService.obtenerSocioId(idSocio);
        if(socio != null){
            txtIdSocio.setText(String.valueOf(socio.getSocioId()));
            txtNombreSocio.setText(socio.getNombre());
            txtNombreSocio.setForeground(Color.black);
            txtDireccionSocio.setText(socio.getDireccion());
              txtDireccionSocio.setForeground(Color.black);

            txtTelefonoSocio.setText(socio.getTelefono());
              txtTelefonoSocio.setForeground(Color.black);

        }else{
            JOptionPane.showMessageDialog(this, "Socio no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void actualizarTablaSociosConUnSocio(Socio socio) {
        DefaultTableModel model = (DefaultTableModel) tableSocios.getModel();
        model.setRowCount(0);

        String directores = socio.getDirectoresFavoritos().stream()
                                 .map(Director::getNombre)
                                 .collect(Collectors.joining(", "));
        String actores = socio.getActoresFavoritos().stream()
                              .map(Actor::getNombre)
                              .collect(Collectors.joining(", "));
        String generos = socio.getGenerosFavoritos().stream()
                              .map(Genero::getNombre)
                              .collect(Collectors.joining(", "));

        model.addRow(new Object[]{
            socio.getSocioId(),
            socio.getNombre(),
            socio.getDireccion(),
            socio.getTelefono(),
            directores,
            actores,
            generos
        });
    }
    
    private void limpiarTablaSocios(){
        DefaultTableModel model = (DefaultTableModel) tableSocios.getModel();
        model.setRowCount(0);
    } 
    
    public void actualizarTablaPeliculas(){
        DefaultTableModel modelo = (DefaultTableModel) tablePeliculas.getModel();
        modelo.setRowCount(0);
        List<Pelicula> listaPeliculas = peliculaService.obtenerTodasLasPeliculas();
        for(Pelicula pelicula : listaPeliculas){
            List<Genero> generos = peliculaService.obtenerGenerosPorPelicula(pelicula.getPeliculaId());
            String nombresGeneros = generos.stream()
                                    .map(Genero::getNombre)
                                    .collect(Collectors.joining(", "));
            modelo.addRow(new Object[]{
                pelicula.getPeliculaId(),
                pelicula.getTitulo(),
                pelicula.getDirector().getNombre(),
                nombresGeneros
            });
        }
    }
    
    private void rellenarFormularioParaEdicion(int filaSeleccionada){
        int peliculaId = (int) tablePeliculas.getValueAt(filaSeleccionada, 0);
        Pelicula pelicula = peliculaService.obtenerPeliculaPorId(peliculaId);
        Director director = pelicula.getDirector();
        
        txtIdPelicula.setText(String.valueOf(pelicula.getPeliculaId()));
        txtTituloPelicula.setText(pelicula.getTitulo());
        for(int i = 0; i < cboDirectoresPelicula.getItemCount(); i++){
            Director dir = (Director) cboDirectoresPelicula.getItemAt(i);
            if(dir != null && dir.getDirectorId() == director.getDirectorId()){
                cboDirectoresPelicula.setSelectedIndex(i);
                break;
            }
        }
        
        actualizarTablaPeliculas();
    }
    
    public void actualizarTablaPeliculasConBusqueda(String text){
        DefaultTableModel modelo = (DefaultTableModel) tablePeliculas.getModel();
        modelo.setRowCount(0);
        
        List<Pelicula> listaPeliculas = peliculaService.buscarPeliculasPorTitulo(text);
        for(Pelicula pelicula : listaPeliculas){
            List<Genero> generos = peliculaService.obtenerGenerosPorPelicula(pelicula.getPeliculaId());
            String nombresGeneros = generos.stream()
                                    .map(Genero::getNombre)
                                    .collect(Collectors.joining(", "));
            modelo.addRow(new Object[]{
                pelicula.getPeliculaId(),
                pelicula.getTitulo(),
                pelicula.getDirector().getNombre(),
                nombresGeneros
            });
        }
    }
    
    private void filtrarTablaDirectores(String texto){
        List<Director> directoresFiltrados = directorService.obtenerTodosLosDirectores().stream()
                                                                                        .filter(d -> d.getNombre().toLowerCase().contains(texto.toLowerCase()))
                                                                                        .collect(Collectors.toList());
        
        DefaultTableModel model = (DefaultTableModel) tableDirectores.getModel();
        model.setRowCount(0);
        
        for(Director director : directoresFiltrados){
            model.addRow(new Object[]{
                director.getDirectorId(),
                director.getNombre()
            });
        }
    }
    
    private void filtrarTablaActores(String texto){
        List<Actor> actoresFiltrados = actorService.obtenerTodosLosActores().stream()
                                                                            .filter(a -> a.getNombre().toLowerCase().contains(texto.toLowerCase()))
                                                                            .collect(Collectors.toList());
        
        DefaultTableModel model = (DefaultTableModel) tableActores.getModel();
        model.setRowCount(0);
        
        for(Actor actor : actoresFiltrados){
            model.addRow(new Object[]{
                actor.getActorId(),
                actor.getNombre()
            });
        }
    }
    
    private void actualizarSeleccionDirectores(Set<Director> seleccion){
        directoresSeleccionados = seleccion;
        lblDirectoresSeleccionados.setText(seleccion.size() + " Directores seleccionados");
    }
    
    private void actualizarSeleccionActores(Set<Actor> seleccion){
        actoresSeleccionados = seleccion;
        lblActoresSeleccionados.setText(seleccion.size() + " Actores seleccionados");
    }
    
    private void actualizarSeleccionGeneros(Set<Genero> seleccion){
        generosSeleccionados = seleccion;
        lblGenerosSeleccionados.setText(seleccion.size() + " Generos seleccionados");
    }
    
    private void limpiarCamposFormularioSocio(){
        txtIdSocio.setText("");
        txtNombreSocio.setText("");
        txtDireccionSocio.setText("");
        txtTelefonoSocio.setText("");
        
        lblDirectoresSeleccionados.setText("");
        lblActoresSeleccionados.setText("");
        lblGenerosSeleccionados.setText("");
        
        directoresSeleccionados.clear();
        actoresSeleccionados.clear();
        generosSeleccionados.clear();
    }
    
    private Cinta obtenerCintaSeleccionada() {
        int filaSeleccionada = tableCintas.getSelectedRow();
        if (filaSeleccionada >= 0) {
            DefaultTableModel model = (DefaultTableModel) tableCintas.getModel();

            int cintaId = Integer.parseInt(model.getValueAt(filaSeleccionada, 0).toString());

            Cinta cinta = cintaService.obtenerCintaPorId(cintaId);

            return cinta;
        } else {
            JOptionPane.showMessageDialog(null, "No hay ninguna cinta seleccionada.");
            return null;
        }
    }
    
    private Cinta obtenerCintaSeleccionadaDeLaTabla() {
        int filaSeleccionada = tableCintas.getSelectedRow();
        if (filaSeleccionada != -1) {
            int cintaId = (int) tableCintas.getValueAt(filaSeleccionada, 0);
            return cintaService.obtenerCintaPorId(cintaId);
        } else {
            return null;
        }
    }
    
    public void actualizarTablaCintas() {
        DefaultTableModel modelo = (DefaultTableModel) tableCintas.getModel();
        modelo.setRowCount(0);
        List<Cinta> listaCintas = cintaService.obtenerTodasLasCintas();
        for (Cinta cinta : listaCintas) {
            Pelicula pelicula = peliculaService.obtenerPeliculaPorId(cinta.getPeliculaId());
            modelo.addRow(new Object[] {
                cinta.getCintaId(),
                pelicula != null ? pelicula.getTitulo() : "Desconocido",
                cinta.getEstado()
            });
        }
    }

    
    public List<Pelicula> obtenerListaPeliculas() {
        return peliculaService.obtenerTodasLasPeliculas();
    }
    
    public void actualizarTablaConResultados(List<Cinta> resultados) {
        DefaultTableModel modelo = (DefaultTableModel) tableCintas.getModel();
        modelo.setRowCount(0);
        for (Cinta cinta : resultados) {
            modelo.addRow(new Object[] {
                cinta.getCintaId(),
                cinta.getTituloPelicula(),
                cinta.getEstado()
            });
        }
    }
    
    private void actualizarTablaPrestamosConBusqueda(String textoBusqueda) {
        List<Prestamo> prestamos = prestamoService.buscarPrestamosPorNombreSocio(textoBusqueda);
        DefaultTableModel modelo = (DefaultTableModel) tablePrestamos.getModel();
        modelo.setRowCount(0);
        for (Prestamo prestamo : prestamos) {
            modelo.addRow(new Object[]{
                prestamo.getPrestamoId(),
                prestamo.getNombreSocio(),
                prestamo.getTituloPelicula(),
                prestamo.getFechaPrestamo(),
                prestamo.getFechaDevolucion() != null ? prestamo.getFechaDevolucion().toString() : "N/A",
                prestamo.getEstadoCinta()
            });
        }
    }
    
    private void actualizarTablaPrestamos() {
        List<Prestamo> prestamos = prestamoService.obtenerTodosLosPrestamos();
        DefaultTableModel modelo = (DefaultTableModel) tablePrestamos.getModel();
        modelo.setRowCount(0);

        for (Prestamo prestamo : prestamos) {
            modelo.addRow(new Object[]{
                prestamo.getPrestamoId(),
                prestamo.getNombreSocio(),
                prestamo.getTituloPelicula(),
                prestamo.getFechaPrestamo().toString(),
                prestamo.getFechaDevolucion() != null ? prestamo.getFechaDevolucion().toString() : "N/A",
                prestamo.getEstadoCinta()
            });
        }
    }
    
    public void limpiarSeleccionPrestamo() {
        lblSocioSeleccionadoPrestamo.setText("");
        lblCintaSeleccionadaPrestamo.setText("");
    }
    
    private void actualizarUIConGenerosSeleccionadosPeliculas() {
        lblGenerosSeleccionadosPeliculas.setText(generosSeleccionadosPeliculas.size() + " Géneros seleccionados");
    }
    
    private void limpiarFormularioPelicula() {
        txtTituloPelicula.setText("");
        cboDirectoresPelicula.setSelectedIndex(0);
        generosSeleccionadosPeliculas.clear();
        actualizarUIConGenerosSeleccionadosPeliculas();
    }
    
    private void cargarDatosEnTablaListaEsperaCompleta() {
        DefaultTableModel modelo = (DefaultTableModel) tableListaDeEspera.getModel();
        modelo.setRowCount(0);

        List<ListaDeEspera> listaEsperaCompleta = listaEsperaService.obtenerTodaListaDeEspera();
        for (ListaDeEspera le : listaEsperaCompleta) {
            modelo.addRow(new Object[]{
                le.getIdListaEspera(),
                le.getIdSocio(),
                le.getNombreSocio(),
                le.getFechaSolicitud().toString()
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
        jTextField1 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        btnDirectores = new javax.swing.JButton();
        btnregistrarsocio = new javax.swing.JButton();
        btnPrestamos = new javax.swing.JButton();
        btnListaDeEspera = new javax.swing.JButton();
        btnActores = new javax.swing.JButton();
        btnPeliculas = new javax.swing.JButton();
        btnCintas = new javax.swing.JButton();
        panelBarra = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        tabbedPane = new javax.swing.JTabbedPane();
        pPrestamos = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtPrestamoId = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        btnBuscarSocio = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        btnBuscarCinta = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        pickerFechaPrestamo = new com.toedter.calendar.JDateChooser();
        jLabel27 = new javax.swing.JLabel();
        pickerFechaDevolucion = new com.toedter.calendar.JDateChooser();
        btnAgregarPrestamo = new javax.swing.JButton();
        btnEliminarPrestamo = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        tablePrestamos = new javax.swing.JTable();
        txtBuscarPrestamo = new javax.swing.JTextField();
        lblSocioSeleccionadoPrestamo = new javax.swing.JLabel();
        lblCintaSeleccionadaPrestamo = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        pListaDeEspera = new javax.swing.JPanel();
        btnSeleccionarPeliculaLDE = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        tableListaDeEspera = new javax.swing.JTable();
        lblPeliculaSeleccionadaLDE = new javax.swing.JLabel();
        txtIdSocioLDE = new javax.swing.JTextField();
        btnAgregarSocioLDE = new javax.swing.JButton();
        btnNotificarSocioLDE = new javax.swing.JButton();
        btnEliminarListaLDE = new javax.swing.JButton();
        jLabel29 = new javax.swing.JLabel();
        lblPeliculaSeleccionadaLDE1 = new javax.swing.JLabel();
        pDirectores = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtNombreDirector = new javax.swing.JTextField();
        btnAgregarDirector = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableDirectores = new javax.swing.JTable();
        btnEditarDirector = new javax.swing.JButton();
        btnEliminarDirector = new javax.swing.JButton();
        txtBuscarDirector = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        pActores = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        txtNombreActor = new javax.swing.JTextField();
        txtBuscarActor = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableActores = new javax.swing.JTable();
        btnAgregarActor = new javax.swing.JButton();
        btnEditarActor = new javax.swing.JButton();
        btnEliminarActor = new javax.swing.JButton();
        jLabel34 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        pPeliculas = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        txtIdPelicula = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtTituloPelicula = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        cboDirectoresPelicula = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        btnAbrirListaGeneros = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablePeliculas = new javax.swing.JTable();
        btnAgregarPelicula = new javax.swing.JButton();
        btnEditarPelicula = new javax.swing.JButton();
        btnEliminarPelicula = new javax.swing.JButton();
        txtBuscarPelicula = new javax.swing.JTextField();
        lblGenerosSeleccionadosPeliculas = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        pCintas = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        txtIdCinta = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtPeliculaCinta = new javax.swing.JTextField();
        btnBuscarPeliculaCinta = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        cboEstadoCinta = new javax.swing.JComboBox<>();
        btnAgregarCinta = new javax.swing.JButton();
        btnEditarCinta = new javax.swing.JButton();
        btnEliminarCinta = new javax.swing.JButton();
        txtBuscarCinta = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        tableCintas = new javax.swing.JTable();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        pSocios = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnRegistrarSocio = new javax.swing.JButton();
        txtDireccionSocio = new javax.swing.JTextField();
        txtTelefonoSocio = new javax.swing.JTextField();
        txtNombreSocio = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtIdSocio = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableSocios = new javax.swing.JTable();
        btnEditarSocio = new javax.swing.JButton();
        btnEliminarSocio = new javax.swing.JButton();
        txtBuscarSocio = new javax.swing.JTextField();
        btnDirectoresSocios = new javax.swing.JButton();
        btnActoresSocios = new javax.swing.JButton();
        btnGenerosSocios = new javax.swing.JButton();
        lblDirectoresSeleccionados = new javax.swing.JLabel();
        lblActoresSeleccionados = new javax.swing.JLabel();
        lblGenerosSeleccionados = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jTextField1.setText("jTextField1");

        setPreferredSize(new java.awt.Dimension(1200, 740));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnDirectores.setBackground(new java.awt.Color(0, 0, 0));
        btnDirectores.setFont(new java.awt.Font("Poppins SemiBold", 1, 16)); // NOI18N
        btnDirectores.setForeground(new java.awt.Color(255, 255, 255));
        btnDirectores.setText("DIRECTORES");
        btnDirectores.setBorder(null);
        btnDirectores.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDirectores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDirectoresMouseClicked(evt);
            }
        });
        jPanel1.add(btnDirectores, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 180, 40));

        btnregistrarsocio.setBackground(new java.awt.Color(0, 0, 0));
        btnregistrarsocio.setFont(new java.awt.Font("Poppins SemiBold", 1, 16)); // NOI18N
        btnregistrarsocio.setForeground(new java.awt.Color(255, 255, 255));
        btnregistrarsocio.setText("SOCIO");
        btnregistrarsocio.setBorder(null);
        btnregistrarsocio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnregistrarsocio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnregistrarsocioMouseClicked(evt);
            }
        });
        jPanel1.add(btnregistrarsocio, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 180, 40));

        btnPrestamos.setBackground(new java.awt.Color(0, 0, 0));
        btnPrestamos.setFont(new java.awt.Font("Poppins SemiBold", 1, 16)); // NOI18N
        btnPrestamos.setForeground(new java.awt.Color(255, 255, 255));
        btnPrestamos.setText("PRESTAMOS");
        btnPrestamos.setBorder(null);
        btnPrestamos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPrestamos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPrestamosMouseClicked(evt);
            }
        });
        btnPrestamos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrestamosActionPerformed(evt);
            }
        });
        jPanel1.add(btnPrestamos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 180, 40));

        btnListaDeEspera.setBackground(new java.awt.Color(0, 0, 0));
        btnListaDeEspera.setFont(new java.awt.Font("Poppins SemiBold", 1, 16)); // NOI18N
        btnListaDeEspera.setForeground(new java.awt.Color(255, 255, 255));
        btnListaDeEspera.setText("LISTA DE ESPERA");
        btnListaDeEspera.setBorder(null);
        btnListaDeEspera.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnListaDeEspera.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnListaDeEsperaMouseClicked(evt);
            }
        });
        jPanel1.add(btnListaDeEspera, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 180, 40));

        btnActores.setBackground(new java.awt.Color(0, 0, 0));
        btnActores.setFont(new java.awt.Font("Poppins SemiBold", 1, 16)); // NOI18N
        btnActores.setForeground(new java.awt.Color(255, 255, 255));
        btnActores.setText("ACTORES");
        btnActores.setBorder(null);
        btnActores.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnActores.setMaximumSize(new java.awt.Dimension(84, 22));
        btnActores.setMinimumSize(new java.awt.Dimension(84, 22));
        btnActores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnActoresMouseClicked(evt);
            }
        });
        jPanel1.add(btnActores, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 330, 180, 40));

        btnPeliculas.setBackground(new java.awt.Color(0, 0, 0));
        btnPeliculas.setFont(new java.awt.Font("Poppins SemiBold", 1, 16)); // NOI18N
        btnPeliculas.setForeground(new java.awt.Color(255, 255, 255));
        btnPeliculas.setText("PELICULAS");
        btnPeliculas.setBorder(null);
        btnPeliculas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPeliculas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPeliculasMouseClicked(evt);
            }
        });
        jPanel1.add(btnPeliculas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 370, 180, 40));

        btnCintas.setBackground(new java.awt.Color(0, 0, 0));
        btnCintas.setFont(new java.awt.Font("Poppins SemiBold", 1, 16)); // NOI18N
        btnCintas.setForeground(new java.awt.Color(255, 255, 255));
        btnCintas.setText("CINTAS");
        btnCintas.setBorder(null);
        btnCintas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCintas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCintasMouseClicked(evt);
            }
        });
        jPanel1.add(btnCintas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 410, 180, 40));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 16, 180, 730));

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

        jLabel5.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imgs/Imgs Login/closeButton.png"))); // NOI18N
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelBarraLayout = new javax.swing.GroupLayout(panelBarra);
        panelBarra.setLayout(panelBarraLayout);
        panelBarraLayout.setHorizontalGroup(
            panelBarraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBarraLayout.createSequentialGroup()
                .addGap(0, 1180, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelBarraLayout.setVerticalGroup(
            panelBarraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
        );

        add(panelBarra, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, 20));

        tabbedPane.setBackground(new java.awt.Color(255, 204, 204));

        pPrestamos.setBackground(new java.awt.Color(255, 255, 255));
        pPrestamos.setForeground(new java.awt.Color(189, 189, 189));
        pPrestamos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Poppins SemiBold", 0, 48)); // NOI18N
        jLabel8.setText("PRÉSTAMOS");
        pPrestamos.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 50, -1, -1));

        txtPrestamoId.setEditable(false);
        txtPrestamoId.setBackground(new java.awt.Color(239, 239, 239));
        pPrestamos.add(txtPrestamoId, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 172, 140, 30));

        jLabel9.setFont(new java.awt.Font("Poppins SemiBold", 0, 16)); // NOI18N
        jLabel9.setText("SOCIO");
        pPrestamos.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 230, -1, -1));

        btnBuscarSocio.setBackground(new java.awt.Color(0, 0, 0));
        btnBuscarSocio.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        btnBuscarSocio.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscarSocio.setText("BUSCAR");
        btnBuscarSocio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarSocioActionPerformed(evt);
            }
        });
        pPrestamos.add(btnBuscarSocio, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 220, 140, -1));

        jLabel10.setFont(new java.awt.Font("Poppins SemiBold", 0, 16)); // NOI18N
        jLabel10.setText("CINTA");
        pPrestamos.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 280, -1, -1));

        btnBuscarCinta.setBackground(new java.awt.Color(0, 0, 0));
        btnBuscarCinta.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        btnBuscarCinta.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscarCinta.setText("BUSCAR");
        btnBuscarCinta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarCintaActionPerformed(evt);
            }
        });
        pPrestamos.add(btnBuscarCinta, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 270, 140, -1));

        jLabel26.setFont(new java.awt.Font("Poppins SemiBold", 0, 16)); // NOI18N
        jLabel26.setText("FECHA PRÉSTAMO");
        pPrestamos.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 180, -1, -1));

        pickerFechaPrestamo.setBackground(new java.awt.Color(236, 236, 236));
        pPrestamos.add(pickerFechaPrestamo, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 180, 140, 30));

        jLabel27.setFont(new java.awt.Font("Poppins SemiBold", 0, 16)); // NOI18N
        jLabel27.setText("FECHA DEVOLUCIÓN");
        pPrestamos.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 240, -1, -1));
        pPrestamos.add(pickerFechaDevolucion, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 240, 140, 30));

        btnAgregarPrestamo.setBackground(new java.awt.Color(0, 0, 0));
        btnAgregarPrestamo.setFont(new java.awt.Font("Poppins SemiBold", 0, 16)); // NOI18N
        btnAgregarPrestamo.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregarPrestamo.setText("AGREGAR");
        btnAgregarPrestamo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarPrestamoActionPerformed(evt);
            }
        });
        pPrestamos.add(btnAgregarPrestamo, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 360, 220, 40));

        btnEliminarPrestamo.setBackground(new java.awt.Color(0, 0, 0));
        btnEliminarPrestamo.setFont(new java.awt.Font("Poppins SemiBold", 0, 16)); // NOI18N
        btnEliminarPrestamo.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminarPrestamo.setText("ELIMINAR");
        btnEliminarPrestamo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarPrestamoActionPerformed(evt);
            }
        });
        pPrestamos.add(btnEliminarPrestamo, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 360, 220, 40));

        tablePrestamos.getTableHeader().setBackground(Color.black);
        tablePrestamos.getTableHeader().setForeground(Color.white);
        tablePrestamos.getTableHeader().setFont(new java.awt.Font("Poppins", 1, 16));
        tablePrestamos.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        tablePrestamos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID Prestamo", "Socio", "Pelicula", "Fecha Prestamo", "Fecha Devolucion", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(tablePrestamos);

        pPrestamos.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 430, 840, 230));

        txtBuscarPrestamo.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                buscarPrestamo();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                buscarPrestamo();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                buscarPrestamo();
            }

            public void buscarPrestamo() {
                String textoBusqueda = txtBuscarPrestamo.getText().trim();
                if (textoBusqueda.length() > 0) {
                    // Este método debe ser implementado para buscar en la base de datos
                    // y actualizar el modelo de la tabla con los resultados.
                    actualizarTablaPrestamosConBusqueda(textoBusqueda);
                } else {
                    // Este método debe ser implementado para restablecer la vista
                    // y mostrar todos los préstamos.
                    actualizarTablaPrestamos();
                }
            }
        });
        pPrestamos.add(txtBuscarPrestamo, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 360, 90, 30));

        lblSocioSeleccionadoPrestamo.setFont(new java.awt.Font("Poppins", 0, 10)); // NOI18N
        pPrestamos.add(lblSocioSeleccionadoPrestamo, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 250, 130, 20));

        lblCintaSeleccionadaPrestamo.setFont(new java.awt.Font("Poppins", 0, 10)); // NOI18N
        pPrestamos.add(lblCintaSeleccionadaPrestamo, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 300, 130, 20));

        jLabel19.setFont(new java.awt.Font("Poppins SemiBold", 0, 16)); // NOI18N
        jLabel19.setText("ID PRESTAMO");
        pPrestamos.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 180, -1, -1));

        jLabel28.setFont(new java.awt.Font("Poppins SemiBold", 0, 16)); // NOI18N
        jLabel28.setText("ID PRESTAMO");
        pPrestamos.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 180, -1, -1));

        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imgs/Imgs Login/lupa (2).png"))); // NOI18N
        pPrestamos.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 360, 30, 30));

        tabbedPane.addTab("tab2", pPrestamos);

        pListaDeEspera.setBackground(new java.awt.Color(255, 255, 255));
        pListaDeEspera.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnSeleccionarPeliculaLDE.setBackground(new java.awt.Color(0, 0, 0));
        btnSeleccionarPeliculaLDE.setFont(new java.awt.Font("Poppins SemiBold", 0, 16)); // NOI18N
        btnSeleccionarPeliculaLDE.setForeground(new java.awt.Color(255, 255, 255));
        btnSeleccionarPeliculaLDE.setText("SELECCIONAR PELÍCULA");
        btnSeleccionarPeliculaLDE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarPeliculaLDEActionPerformed(evt);
            }
        });
        pListaDeEspera.add(btnSeleccionarPeliculaLDE, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 220, 250, 30));

        tableListaDeEspera.getTableHeader().setBackground(Color.black);
        tableListaDeEspera.getTableHeader().setForeground(Color.white);
        tableListaDeEspera.getTableHeader().setFont(new java.awt.Font("Poppins", 1, 16));
        tableListaDeEspera.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        tableListaDeEspera.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID Lista Espera", "ID Socio", "Nombre Socio", "Fecha Solicitud"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane7.setViewportView(tableListaDeEspera);

        pListaDeEspera.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 420, 680, 270));

        lblPeliculaSeleccionadaLDE.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
        lblPeliculaSeleccionadaLDE.setText(".");
        pListaDeEspera.add(lblPeliculaSeleccionadaLDE, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 220, 80, 30));

        txtIdSocioLDE.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        pListaDeEspera.add(txtIdSocioLDE, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 290, 120, 30));

        btnAgregarSocioLDE.setBackground(new java.awt.Color(0, 0, 0));
        btnAgregarSocioLDE.setFont(new java.awt.Font("Poppins SemiBold", 0, 16)); // NOI18N
        btnAgregarSocioLDE.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregarSocioLDE.setText("AGREGAR SOCIO");
        btnAgregarSocioLDE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarSocioLDEActionPerformed(evt);
            }
        });
        pListaDeEspera.add(btnAgregarSocioLDE, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 350, 200, 40));

        btnNotificarSocioLDE.setBackground(new java.awt.Color(0, 0, 0));
        btnNotificarSocioLDE.setFont(new java.awt.Font("Poppins SemiBold", 0, 16)); // NOI18N
        btnNotificarSocioLDE.setForeground(new java.awt.Color(255, 255, 255));
        btnNotificarSocioLDE.setText("NOTIFICAR SOCIO");
        btnNotificarSocioLDE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNotificarSocioLDEActionPerformed(evt);
            }
        });
        pListaDeEspera.add(btnNotificarSocioLDE, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 350, 190, 40));

        btnEliminarListaLDE.setBackground(new java.awt.Color(0, 0, 0));
        btnEliminarListaLDE.setFont(new java.awt.Font("Poppins SemiBold", 0, 16)); // NOI18N
        btnEliminarListaLDE.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminarListaLDE.setText("ELIMINAR SOCIO");
        btnEliminarListaLDE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarListaLDEActionPerformed(evt);
            }
        });
        pListaDeEspera.add(btnEliminarListaLDE, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 350, 210, 40));

        jLabel29.setFont(new java.awt.Font("Poppins SemiBold", 0, 48)); // NOI18N
        jLabel29.setText("LISTA DE ESPERA");
        pListaDeEspera.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 70, 390, -1));

        lblPeliculaSeleccionadaLDE1.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        lblPeliculaSeleccionadaLDE1.setText("ID SOCIO");
        pListaDeEspera.add(lblPeliculaSeleccionadaLDE1, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 290, 80, 30));

        tabbedPane.addTab("tab3", pListaDeEspera);

        pDirectores.setBackground(new java.awt.Color(255, 255, 255));
        pDirectores.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Poppins SemiBold", 0, 16)); // NOI18N
        jLabel6.setText("NOMBRE");
        pDirectores.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 200, -1, 20));

        txtNombreDirector.setFont(new java.awt.Font("Poppins SemiBold", 0, 16)); // NOI18N
        txtNombreDirector.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreDirectorKeyTyped(evt);
            }
        });
        pDirectores.add(txtNombreDirector, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 190, 240, -1));

        btnAgregarDirector.setBackground(new java.awt.Color(0, 0, 0));
        btnAgregarDirector.setFont(new java.awt.Font("Poppins SemiBold", 0, 16)); // NOI18N
        btnAgregarDirector.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregarDirector.setText("AGREGAR");
        btnAgregarDirector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarDirectorActionPerformed(evt);
            }
        });
        pDirectores.add(btnAgregarDirector, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 190, 120, -1));

        tableDirectores.getTableHeader().setBackground(Color.black);
        tableDirectores.getTableHeader().setForeground(Color.white);
        tableDirectores.getTableHeader().setFont(new java.awt.Font("Poppins", 1, 16));
        tableDirectores.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        tableDirectores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ID", "Nombre"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableDirectores.getColumnModel().getColumn(0).setMinWidth(0);
        tableDirectores.getColumnModel().getColumn(0).setMaxWidth(0);
        tableDirectores.getColumnModel().getColumn(0).setWidth(0);
        actualizarTablaDirectores();
        jScrollPane2.setViewportView(tableDirectores);

        pDirectores.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 300, 430, 290));

        btnEditarDirector.setBackground(new java.awt.Color(0, 0, 0));
        btnEditarDirector.setFont(new java.awt.Font("Poppins SemiBold", 0, 16)); // NOI18N
        btnEditarDirector.setForeground(new java.awt.Color(255, 255, 255));
        btnEditarDirector.setText("EDITAR");
        btnEditarDirector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarDirectorActionPerformed(evt);
            }
        });
        pDirectores.add(btnEditarDirector, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 610, 170, 40));

        btnEliminarDirector.setBackground(new java.awt.Color(0, 0, 0));
        btnEliminarDirector.setFont(new java.awt.Font("Poppins SemiBold", 0, 16)); // NOI18N
        btnEliminarDirector.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminarDirector.setText("ELIMINAR");
        btnEliminarDirector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarDirectorActionPerformed(evt);
            }
        });
        pDirectores.add(btnEliminarDirector, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 610, 170, 40));

        txtBuscarDirector.getDocument().addDocumentListener(new DocumentListener(){
            @Override
            public void insertUpdate(DocumentEvent e){
                actualizarTablaDirectoresConFiltro();
            }

            @Override
            public void removeUpdate(DocumentEvent e){
                actualizarTablaDirectoresConFiltro();
            }

            @Override
            public void changedUpdate(DocumentEvent e){
                actualizarTablaDirectoresConFiltro();
            }

            private void actualizarTablaDirectoresConFiltro(){
                String texto = txtBuscarDirector.getText();
                if(texto.trim().isEmpty()){
                    actualizarTablaDirectores();
                }else{
                    filtrarTablaDirectores(texto);
                }
            }
        });
        txtBuscarDirector.setFont(new java.awt.Font("Poppins SemiBold", 0, 16)); // NOI18N
        pDirectores.add(txtBuscarDirector, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 250, 240, -1));

        jLabel30.setFont(new java.awt.Font("Poppins SemiBold", 0, 48)); // NOI18N
        jLabel30.setText("DIRECTORES");
        pDirectores.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 60, -1, -1));

        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imgs/Imgs Login/lupa (2).png"))); // NOI18N
        pDirectores.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 250, 30, 30));

        tabbedPane.addTab("tab4", pDirectores);

        pActores.setBackground(new java.awt.Color(255, 255, 255));
        pActores.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel16.setFont(new java.awt.Font("Poppins SemiBold", 0, 48)); // NOI18N
        jLabel16.setText("ACTORES");
        pActores.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 70, -1, -1));

        txtNombreActor.setFont(new java.awt.Font("Poppins SemiBold", 0, 16)); // NOI18N
        txtNombreActor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreActorKeyTyped(evt);
            }
        });
        pActores.add(txtNombreActor, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 200, 280, -1));

        txtBuscarActor.getDocument().addDocumentListener(new DocumentListener(){
            @Override
            public void insertUpdate(DocumentEvent e){
                actualizarTablaActoresConFiltro();
            }

            @Override
            public void removeUpdate(DocumentEvent e){
                actualizarTablaActoresConFiltro();
            }

            @Override
            public void changedUpdate(DocumentEvent e){
                actualizarTablaActoresConFiltro();
            }

            private void actualizarTablaActoresConFiltro(){
                String texto = txtBuscarActor.getText();
                if(texto.trim().isEmpty()){
                    actualizarTablaActores();
                }else{
                    filtrarTablaActores(texto);
                }
            }
        });
        txtBuscarActor.setFont(new java.awt.Font("Poppins SemiBold", 0, 16)); // NOI18N
        pActores.add(txtBuscarActor, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 270, 130, -1));

        tableActores.getTableHeader().setBackground(Color.black);
        tableActores.getTableHeader().setForeground(Color.white);
        tableActores.getTableHeader().setFont(new java.awt.Font("Poppins", 1, 16));
        tableActores.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        tableActores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ID", "Nombre"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableActores.getColumnModel().getColumn(0).setMinWidth(40);
        tableActores.getColumnModel().getColumn(0).setMaxWidth(40);
        tableActores.getColumnModel().getColumn(0).setWidth(40);
        jScrollPane3.setViewportView(tableActores);
        actualizarTablaActores();

        pActores.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 320, 520, 260));

        btnAgregarActor.setBackground(new java.awt.Color(0, 0, 0));
        btnAgregarActor.setFont(new java.awt.Font("Poppins SemiBold", 0, 16)); // NOI18N
        btnAgregarActor.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregarActor.setText("AGREGAR");
        btnAgregarActor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActorActionPerformed(evt);
            }
        });
        pActores.add(btnAgregarActor, new org.netbeans.lib.awtextra.AbsoluteConstraints(595, 200, 140, -1));

        btnEditarActor.setBackground(new java.awt.Color(0, 0, 0));
        btnEditarActor.setFont(new java.awt.Font("Poppins SemiBold", 0, 16)); // NOI18N
        btnEditarActor.setForeground(new java.awt.Color(255, 255, 255));
        btnEditarActor.setText("EDITAR");
        btnEditarActor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActorActionPerformed(evt);
            }
        });
        pActores.add(btnEditarActor, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 610, 240, -1));

        btnEliminarActor.setBackground(new java.awt.Color(0, 0, 0));
        btnEliminarActor.setFont(new java.awt.Font("Poppins SemiBold", 0, 16)); // NOI18N
        btnEliminarActor.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminarActor.setText("ELIMINAR");
        btnEliminarActor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActorActionPerformed(evt);
            }
        });
        pActores.add(btnEliminarActor, new org.netbeans.lib.awtextra.AbsoluteConstraints(501, 610, 230, -1));

        jLabel34.setFont(new java.awt.Font("Poppins SemiBold", 0, 16)); // NOI18N
        jLabel34.setText("NOMBRE");
        pActores.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 200, -1, -1));

        jLabel36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imgs/Imgs Login/lupa (2).png"))); // NOI18N
        pActores.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 270, -1, 30));

        tabbedPane.addTab("tab5", pActores);

        pPeliculas.setBackground(new java.awt.Color(255, 255, 255));
        pPeliculas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel20.setFont(new java.awt.Font("Poppins SemiBold", 0, 16)); // NOI18N
        jLabel20.setText("ID PELICULA");
        pPeliculas.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 140, -1, -1));

        txtIdPelicula.setEditable(false);
        txtIdPelicula.setBackground(new java.awt.Color(235, 235, 235));
        txtIdPelicula.setFont(new java.awt.Font("Poppins SemiBold", 0, 16)); // NOI18N
        pPeliculas.add(txtIdPelicula, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 140, 130, 30));

        jLabel21.setFont(new java.awt.Font("Poppins SemiBold", 0, 16)); // NOI18N
        jLabel21.setText("TITULO");
        pPeliculas.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 190, -1, -1));

        txtTituloPelicula.setBackground(new java.awt.Color(235, 235, 235));
        txtTituloPelicula.setFont(new java.awt.Font("Poppins SemiBold", 0, 16)); // NOI18N
        pPeliculas.add(txtTituloPelicula, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 190, 130, -1));

        jLabel22.setFont(new java.awt.Font("Poppins SemiBold", 0, 16)); // NOI18N
        jLabel22.setText("DIRECTOR");
        pPeliculas.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 250, -1, -1));

        cboDirectoresPelicula.setBackground(new java.awt.Color(235, 235, 235));
        cboDirectoresPelicula.setFont(new java.awt.Font("Poppins SemiBold", 0, 16)); // NOI18N
        pPeliculas.add(cboDirectoresPelicula, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 250, 130, -1));

        jLabel23.setFont(new java.awt.Font("Poppins SemiBold", 0, 48)); // NOI18N
        jLabel23.setText("PELÍCULAS");
        pPeliculas.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 50, -1, -1));

        btnAbrirListaGeneros.setBackground(new java.awt.Color(0, 0, 0));
        btnAbrirListaGeneros.setFont(new java.awt.Font("Poppins SemiBold", 0, 16)); // NOI18N
        btnAbrirListaGeneros.setForeground(new java.awt.Color(255, 255, 255));
        btnAbrirListaGeneros.setText("SELECCIONAR");
        btnAbrirListaGeneros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirListaGenerosActionPerformed(evt);
            }
        });
        pPeliculas.add(btnAbrirListaGeneros, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 330, 180, -1));

        tablePeliculas.getTableHeader().setBackground(Color.black);
        tablePeliculas.getTableHeader().setForeground(Color.white);
        tablePeliculas.getTableHeader().setFont(new java.awt.Font("Poppins", 1, 16));
        tablePeliculas.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        tablePeliculas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Titulo", "Director", "Genero"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablePeliculas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablePeliculasMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tablePeliculas);

        pPeliculas.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 420, 530, 150));

        btnAgregarPelicula.setBackground(new java.awt.Color(0, 0, 0));
        btnAgregarPelicula.setFont(new java.awt.Font("Poppins SemiBold", 0, 16)); // NOI18N
        btnAgregarPelicula.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregarPelicula.setText("AGREGAR");
        btnAgregarPelicula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarPeliculaActionPerformed(evt);
            }
        });
        pPeliculas.add(btnAgregarPelicula, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 140, 150, -1));

        btnEditarPelicula.setBackground(new java.awt.Color(0, 0, 0));
        btnEditarPelicula.setFont(new java.awt.Font("Poppins SemiBold", 0, 16)); // NOI18N
        btnEditarPelicula.setForeground(new java.awt.Color(255, 255, 255));
        btnEditarPelicula.setText("EDITAR");
        btnEditarPelicula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarPeliculaActionPerformed(evt);
            }
        });
        pPeliculas.add(btnEditarPelicula, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 190, 150, -1));

        btnEliminarPelicula.setBackground(new java.awt.Color(0, 0, 0));
        btnEliminarPelicula.setFont(new java.awt.Font("Poppins SemiBold", 0, 16)); // NOI18N
        btnEliminarPelicula.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminarPelicula.setText("ELIMINAR");
        btnEliminarPelicula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarPeliculaActionPerformed(evt);
            }
        });
        pPeliculas.add(btnEliminarPelicula, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 250, 150, -1));

        txtBuscarPelicula.getDocument().addDocumentListener(new DocumentListener(){
            @Override
            public void insertUpdate(DocumentEvent e){
                actualizarTablaConFiltro();
            }

            @Override
            public void removeUpdate(DocumentEvent e){
                actualizarTablaConFiltro();
            }

            @Override
            public void changedUpdate(DocumentEvent e){
                actualizarTablaConFiltro();
            }

            private void actualizarTablaConFiltro(){

                txtBuscarPelicula.getDocument().removeDocumentListener(this);

                String texto = txtBuscarPelicula.getText();
                if(texto.trim().isEmpty()){
                    actualizarTablaPeliculas();
                }else{
                    actualizarTablaPeliculasConBusqueda(texto);
                }

                txtBuscarPelicula.getDocument().addDocumentListener(this);
            }
        });
        txtBuscarPelicula.setFont(new java.awt.Font("Poppins SemiBold", 0, 16)); // NOI18N
        pPeliculas.add(txtBuscarPelicula, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 380, 230, 30));

        lblGenerosSeleccionadosPeliculas.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        pPeliculas.add(lblGenerosSeleccionadosPeliculas, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 340, 160, 20));

        jLabel35.setFont(new java.awt.Font("Poppins SemiBold", 0, 16)); // NOI18N
        jLabel35.setText("GÉNEROS");
        pPeliculas.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 330, -1, -1));

        jLabel37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imgs/Imgs Login/lupa (2).png"))); // NOI18N
        pPeliculas.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 380, -1, 30));

        tabbedPane.addTab("tab6", pPeliculas);

        pCintas.setBackground(new java.awt.Color(255, 255, 255));
        pCintas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setFont(new java.awt.Font("Poppins SemiBold", 0, 48)); // NOI18N
        jLabel15.setText("CINTAS");
        pCintas.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 70, 180, 70));

        txtIdCinta.setEditable(false);
        txtIdCinta.setFont(new java.awt.Font("Poppins SemiBold", 0, 16)); // NOI18N
        pCintas.add(txtIdCinta, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 160, 70, -1));

        jLabel17.setFont(new java.awt.Font("Poppins SemiBold", 0, 16)); // NOI18N
        jLabel17.setText("PELÍCULA");
        pCintas.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 220, -1, -1));

        txtPeliculaCinta.setEditable(false);
        txtPeliculaCinta.setFont(new java.awt.Font("Poppins SemiBold", 0, 16)); // NOI18N
        pCintas.add(txtPeliculaCinta, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 210, 150, -1));

        btnBuscarPeliculaCinta.setBackground(new java.awt.Color(0, 0, 0));
        btnBuscarPeliculaCinta.setFont(new java.awt.Font("Poppins SemiBold", 0, 16)); // NOI18N
        btnBuscarPeliculaCinta.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscarPeliculaCinta.setText("BUSCAR PELÍCULA");
        btnBuscarPeliculaCinta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarPeliculaCintaActionPerformed(evt);
            }
        });
        pCintas.add(btnBuscarPeliculaCinta, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 270, 180, -1));

        jLabel24.setFont(new java.awt.Font("Poppins SemiBold", 0, 16)); // NOI18N
        jLabel24.setText("ESTADO");
        pCintas.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 270, -1, -1));

        cboEstadoCinta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Disponible", "Prestado", "Dañada", "Perdida" }));
        pCintas.add(cboEstadoCinta, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 260, 150, 30));

        btnAgregarCinta.setBackground(new java.awt.Color(0, 0, 0));
        btnAgregarCinta.setFont(new java.awt.Font("Poppins SemiBold", 0, 16)); // NOI18N
        btnAgregarCinta.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregarCinta.setText("AGREGAR");
        btnAgregarCinta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarCintaActionPerformed(evt);
            }
        });
        pCintas.add(btnAgregarCinta, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 350, 120, -1));

        btnEditarCinta.setBackground(new java.awt.Color(0, 0, 0));
        btnEditarCinta.setFont(new java.awt.Font("Poppins SemiBold", 0, 16)); // NOI18N
        btnEditarCinta.setForeground(new java.awt.Color(255, 255, 255));
        btnEditarCinta.setText("EDITAR");
        btnEditarCinta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarCintaActionPerformed(evt);
            }
        });
        pCintas.add(btnEditarCinta, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 350, 120, -1));

        btnEliminarCinta.setBackground(new java.awt.Color(0, 0, 0));
        btnEliminarCinta.setFont(new java.awt.Font("Poppins SemiBold", 0, 16)); // NOI18N
        btnEliminarCinta.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminarCinta.setText("ELIMINAR");
        btnEliminarCinta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarCintaActionPerformed(evt);
            }
        });
        pCintas.add(btnEliminarCinta, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 350, 110, -1));

        txtBuscarCinta.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                buscar();
            }
            public void removeUpdate(DocumentEvent e) {
                buscar();
            }
            public void insertUpdate(DocumentEvent e) {
                buscar();
            }

            private void buscar() {
                String texto = txtBuscarCinta.getText();
                if (texto.trim().length() > 0) {
                    List<Cinta> resultados = cintaService.buscarCintasPorNombrePelicula(texto);
                    actualizarTablaConResultados(resultados);
                } else {
                    actualizarTablaCintas();
                }
            }
        });
        txtBuscarCinta.setFont(new java.awt.Font("Poppins SemiBold", 0, 16)); // NOI18N
        pCintas.add(txtBuscarCinta, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 350, 150, -1));

        tableCintas.getTableHeader().setBackground(Color.black);
        tableCintas.getTableHeader().setForeground(Color.white);
        tableCintas.getTableHeader().setFont(new java.awt.Font("Poppins", 1, 16));
        tableCintas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tableCintas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID Cinta", "Pelicula", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(tableCintas);

        pCintas.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 410, 680, 260));

        jLabel38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imgs/Imgs Login/lupa (2).png"))); // NOI18N
        pCintas.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 350, -1, 30));

        jLabel39.setFont(new java.awt.Font("Poppins SemiBold", 0, 16)); // NOI18N
        jLabel39.setText("ID CINTA");
        pCintas.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 170, -1, -1));

        tabbedPane.addTab("tab7", pCintas);

        pSocios.setBackground(new java.awt.Color(255, 255, 255));
        pSocios.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Poppins SemiBold", 0, 48)); // NOI18N
        jLabel1.setText("SOCIOS");
        pSocios.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 40, -1, -1));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Poppins SemiBold", 0, 16)); // NOI18N
        jLabel2.setText("DIRECCIÓN");
        pSocios.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 220, -1, -1));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Poppins SemiBold", 0, 16)); // NOI18N
        jLabel3.setText("TELÉFONO");
        pSocios.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 270, -1, -1));

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Poppins SemiBold", 0, 16)); // NOI18N
        jLabel4.setText("DIRECTORES FAVORITOS");
        pSocios.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 170, -1, -1));

        btnRegistrarSocio.setBackground(new java.awt.Color(0, 0, 0));
        btnRegistrarSocio.setFont(new java.awt.Font("Poppins SemiBold", 0, 16)); // NOI18N
        btnRegistrarSocio.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrarSocio.setText("REGISTRAR");
        btnRegistrarSocio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRegistrarSocio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarSocioActionPerformed(evt);
            }
        });
        pSocios.add(btnRegistrarSocio, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 360, 180, 40));

        txtDireccionSocio.setBackground(new java.awt.Color(241, 241, 241));
        txtDireccionSocio.setFont(new java.awt.Font("Poppins Medium", 0, 16)); // NOI18N
        txtDireccionSocio.setToolTipText("");
        txtDireccionSocio.setBorder(null);
        pSocios.add(txtDireccionSocio, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 220, 250, 30));

        txtTelefonoSocio.setBackground(new java.awt.Color(241, 241, 241));
        txtTelefonoSocio.setFont(new java.awt.Font("Poppins Medium", 0, 16)); // NOI18N
        txtTelefonoSocio.setBorder(null);
        txtTelefonoSocio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoSocioKeyTyped(evt);
            }
        });
        pSocios.add(txtTelefonoSocio, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 270, 250, 30));

        txtNombreSocio.setBackground(new java.awt.Color(241, 241, 241));
        txtNombreSocio.setFont(new java.awt.Font("Poppins Medium", 0, 16)); // NOI18N
        txtNombreSocio.setBorder(null);
        txtNombreSocio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreSocioKeyTyped(evt);
            }
        });
        pSocios.add(txtNombreSocio, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 170, 250, 30));

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setFont(new java.awt.Font("Poppins SemiBold", 0, 16)); // NOI18N
        jLabel11.setText("ACTORES FAVORITOS");
        pSocios.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 220, -1, -1));

        jLabel12.setBackground(new java.awt.Color(255, 255, 255));
        jLabel12.setFont(new java.awt.Font("Poppins SemiBold", 0, 16)); // NOI18N
        jLabel12.setText("GÉNEROS PREFERIDOS");
        pSocios.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 270, -1, -1));

        jLabel18.setFont(new java.awt.Font("Poppins SemiBold", 0, 16)); // NOI18N
        jLabel18.setText("ID SOCIO");
        pSocios.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 120, -1, -1));

        txtIdSocio.setEditable(false);
        pSocios.add(txtIdSocio, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 120, 120, 30));

        tableSocios.getTableHeader().setBackground(Color.black);
        tableSocios.getTableHeader().setForeground(Color.white);
        tableSocios.getTableHeader().setFont(new java.awt.Font("Poppins", 1, 14));
        tableSocios.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N
        tableSocios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "IDSocio", "Nombre", "Dirección", "Teléfono", "Directores favorito", "Actores favorito", "Generos favoritos"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableSocios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableSociosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableSocios);
        actualizarTablaSocios();

        pSocios.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 440, 930, 260));

        btnEditarSocio.setBackground(new java.awt.Color(0, 0, 0));
        btnEditarSocio.setFont(new java.awt.Font("Poppins SemiBold", 0, 16)); // NOI18N
        btnEditarSocio.setForeground(new java.awt.Color(255, 255, 255));
        btnEditarSocio.setText("EDITAR");
        btnEditarSocio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarSocioActionPerformed(evt);
            }
        });
        pSocios.add(btnEditarSocio, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 360, 180, 40));

        btnEliminarSocio.setBackground(new java.awt.Color(0, 0, 0));
        btnEliminarSocio.setFont(new java.awt.Font("Poppins SemiBold", 0, 16)); // NOI18N
        btnEliminarSocio.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminarSocio.setText("ELIMINAR");
        btnEliminarSocio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarSocioActionPerformed(evt);
            }
        });
        pSocios.add(btnEliminarSocio, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 360, 180, 40));

        txtBuscarSocio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarSocioKeyReleased(evt);
            }
        });
        pSocios.add(txtBuscarSocio, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 360, 70, 30));

        btnDirectoresSocios.setBackground(new java.awt.Color(0, 0, 0));
        btnDirectoresSocios.setFont(new java.awt.Font("Poppins SemiBold", 0, 14)); // NOI18N
        btnDirectoresSocios.setForeground(new java.awt.Color(255, 255, 255));
        btnDirectoresSocios.setText("SELECCIONAR");
        btnDirectoresSocios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDirectoresSociosActionPerformed(evt);
            }
        });
        pSocios.add(btnDirectoresSocios, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 170, 160, -1));

        btnActoresSocios.setBackground(new java.awt.Color(0, 0, 0));
        btnActoresSocios.setFont(new java.awt.Font("Poppins SemiBold", 0, 14)); // NOI18N
        btnActoresSocios.setForeground(new java.awt.Color(255, 255, 255));
        btnActoresSocios.setText("SELECCIONAR");
        btnActoresSocios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActoresSociosActionPerformed(evt);
            }
        });
        pSocios.add(btnActoresSocios, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 220, 160, -1));

        btnGenerosSocios.setBackground(new java.awt.Color(0, 0, 0));
        btnGenerosSocios.setFont(new java.awt.Font("Poppins SemiBold", 0, 14)); // NOI18N
        btnGenerosSocios.setForeground(new java.awt.Color(255, 255, 255));
        btnGenerosSocios.setText("SELECCIONAR");
        btnGenerosSocios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerosSociosActionPerformed(evt);
            }
        });
        pSocios.add(btnGenerosSocios, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 270, 160, -1));

        lblDirectoresSeleccionados.setFont(new java.awt.Font("Poppins", 0, 10)); // NOI18N
        pSocios.add(lblDirectoresSeleccionados, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 190, 110, 20));

        lblActoresSeleccionados.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        pSocios.add(lblActoresSeleccionados, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 240, 140, 20));

        lblGenerosSeleccionados.setFont(new java.awt.Font("Poppins", 0, 10)); // NOI18N
        pSocios.add(lblGenerosSeleccionados, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 290, 120, 20));

        jLabel13.setBackground(new java.awt.Color(255, 255, 255));
        jLabel13.setFont(new java.awt.Font("Poppins SemiBold", 0, 16)); // NOI18N
        jLabel13.setText("NOMBRE");
        pSocios.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 170, -1, -1));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imgs/Imgs Login/lupa (2).png"))); // NOI18N
        pSocios.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 360, 30, 30));

        tabbedPane.addTab("tab1", pSocios);

        add(tabbedPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, -20, 1020, 760));
    }// </editor-fold>//GEN-END:initComponents

    private void btnregistrarsocioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnregistrarsocioMouseClicked
        tabbedPane.setSelectedIndex(0);
        actualizarTablaSocios();
    }//GEN-LAST:event_btnregistrarsocioMouseClicked

    private void btnPrestamosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPrestamosMouseClicked
        tabbedPane.setSelectedIndex(1);      
        actualizarTablaPrestamos();
    }//GEN-LAST:event_btnPrestamosMouseClicked

    private void btnListaDeEsperaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnListaDeEsperaMouseClicked
        tabbedPane.setSelectedIndex(2);
        cargarDatosEnTablaListaEsperaCompleta();
    }//GEN-LAST:event_btnListaDeEsperaMouseClicked

    private void btnDirectoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDirectoresMouseClicked
        tabbedPane.setSelectedIndex(3); 
        actualizarTablaDirectores();
    }//GEN-LAST:event_btnDirectoresMouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel5MouseClicked

    private void panelBarraMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBarraMouseDragged
        int x = evt.getXOnScreen();
        int y  = evt.getYOnScreen();
        this.setLocation(x -xMouse, y- yMouse);
    }//GEN-LAST:event_panelBarraMouseDragged

    private void panelBarraMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBarraMousePressed
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_panelBarraMousePressed

    private void btnRegistrarSocioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarSocioActionPerformed
        String nombre = txtNombreSocio.getText();
        String direccion = txtDireccionSocio.getText();
        String telefono = txtTelefonoSocio.getText();
        
        Socio nuevoSocio = new Socio();
        nuevoSocio.setNombre(nombre);
        nuevoSocio.setDireccion(direccion);
        nuevoSocio.setTelefono(telefono);
        
        List<Director> listaDirectoresFavoritos = new ArrayList<>(directoresSeleccionados);
        List<Actor> listaActoresFavoritos = new ArrayList<>(actoresSeleccionados);
        List<Genero> listaGenerosFavoritos = new ArrayList<>(generosSeleccionados);

        socioService.agregarSocioConFavoritos(nuevoSocio, listaDirectoresFavoritos, listaActoresFavoritos, listaGenerosFavoritos);

        actualizarTablaSocios();
        limpiarCamposFormularioSocio();
    }//GEN-LAST:event_btnRegistrarSocioActionPerformed

    private void btnAgregarDirectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarDirectorActionPerformed
        String nombre = txtNombreDirector.getText();
        if(!nombre.trim().isEmpty()){
            directorService.agregarDirector(nombre);
            actualizarTablaDirectores();
        }else{
            JOptionPane.showMessageDialog(null, "El nombre del director no puede estar vacio.");
        }
    }//GEN-LAST:event_btnAgregarDirectorActionPerformed

    private void btnEditarDirectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarDirectorActionPerformed
        int row = tableDirectores.getSelectedRow();
        if(row != -1){
            int directorId = Integer.parseInt(tableDirectores.getValueAt(row, 0).toString());
            String nuevoNombre = JOptionPane.showInputDialog(null, "Ingrese el nuevo nombre del director: ", "Editar Director", JOptionPane.PLAIN_MESSAGE);
            if(nuevoNombre != null && !nuevoNombre.trim().isEmpty()){
                directorService.editarDirector(directorId, nuevoNombre);
                actualizarTablaDirectores();
            }else{
                JOptionPane.showMessageDialog(null, "El nombre no puede estar vacio.");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Debe seleccionar un director de la lista para editar.");
        }
    }//GEN-LAST:event_btnEditarDirectorActionPerformed

    private void btnEliminarDirectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarDirectorActionPerformed
        int row = tableDirectores.getSelectedRow();
        if(row != -1){
            int confirmacion = JOptionPane.showConfirmDialog(null, "Está seguro de que desea eliminar este director?", "Eliminar Director", JOptionPane.YES_NO_OPTION);
            if(confirmacion == JOptionPane.YES_OPTION){
                int directorId = Integer.parseInt(tableDirectores.getValueAt(row, 0).toString());
                directorService.eliminarDirector(directorId);
                actualizarTablaDirectores();
            }
        }else{
            JOptionPane.showMessageDialog(null, "Debe seleccionar un director de la lista para eliminar.");
        }
    }//GEN-LAST:event_btnEliminarDirectorActionPerformed

    private void btnActoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnActoresMouseClicked
        tabbedPane.setSelectedIndex(4); 
    }//GEN-LAST:event_btnActoresMouseClicked

    private void btnAgregarActorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActorActionPerformed
        String nombre = txtNombreActor.getText();
        if(!nombre.trim().isEmpty()){
            actorService.agregarActor(nombre);
            actualizarTablaActores();
        }else{
            JOptionPane.showMessageDialog(null, "El nombre del actor no puede estar vacio.");
        }
    }//GEN-LAST:event_btnAgregarActorActionPerformed

    private void btnEditarActorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActorActionPerformed
        int row = tableActores.getSelectedRow();
        if(row != -1){
            int actorId = Integer.parseInt(tableActores.getValueAt(row, 0).toString());
            String nuevoNombre = JOptionPane.showInputDialog(null, "Ingrese el nuevo nombre del actor: ", "Editar Actor", JOptionPane.PLAIN_MESSAGE);
            if(nuevoNombre != null && !nuevoNombre.trim().isEmpty()){
                actorService.editarActor(actorId, nuevoNombre);
                actualizarTablaActores();
            }else{
                JOptionPane.showMessageDialog(null, "El nombre no puede estar vacio.");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Debe seleccionar un actor de la lista para editar.");
        }
    }//GEN-LAST:event_btnEditarActorActionPerformed

    private void btnEliminarActorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActorActionPerformed
        int row = tableActores.getSelectedRow();
        if(row != -1){
            int confirmacion = JOptionPane.showConfirmDialog(null, "Está seguro de que desea eliminar este actor?", "Eliminar Actor", JOptionPane.YES_NO_OPTION);
            if(confirmacion == JOptionPane.YES_OPTION){
                int actorId = Integer.parseInt(tableActores.getValueAt(row, 0).toString());
                actorService.eliminarActor(actorId);
                actualizarTablaActores();
            }
        }else{
            JOptionPane.showMessageDialog(null, "Debe seleccionar un actor de la lista para eliminar.");
        }
    }//GEN-LAST:event_btnEliminarActorActionPerformed

    private void btnEditarSocioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarSocioActionPerformed
        int idSocio = Integer.parseInt(txtIdSocio.getText());
        Socio socio = socioService.obtenerSocioId(idSocio);
        if(socio != null){
            socio.setNombre(txtNombreSocio.getText());
            socio.setDireccion(txtDireccionSocio.getText());
            socio.setTelefono(txtTelefonoSocio.getText());
            socioService.actualizarSocio(socio);
            actualizarTablaSocios();
            JOptionPane.showMessageDialog(this, "Socio actualizado correctamente.", "Informacion", JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(this, "Error al actualizar el socio.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEditarSocioActionPerformed

    private void btnEliminarSocioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarSocioActionPerformed
        int fila = tableSocios.getSelectedRow();
        if(fila != -1){
            int idSocio = Integer.parseInt(tableSocios.getValueAt(fila, 0).toString());
            int confirmacion = JOptionPane.showConfirmDialog(null, "Estás seguro de que deseas eliminar el socio seleccionado?", "Eliminar Socio", JOptionPane.YES_NO_OPTION);
            if(confirmacion == JOptionPane.YES_OPTION){
                Socio socioParaEliminar = new Socio();
                socioParaEliminar.setSocioId(idSocio);
                socioService.eliminarSocio(socioParaEliminar);
                actualizarTablaSocios();
                JOptionPane.showMessageDialog(null, "Socio eliminado con exito.", "Eliminar Socio", JOptionPane.INFORMATION_MESSAGE);
                
                txtNombreSocio.setText("");
                txtDireccionSocio.setText("");
                txtTelefonoSocio.setText("");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Por favor, seleccione un socio de la tabla.", "Eliminar Socio", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEliminarSocioActionPerformed

    private void tableSociosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableSociosMouseClicked
        if(evt.getClickCount() == 2){
            int fila  = tableSocios.getSelectedRow();
            if(fila != -1){
                int idSocio = Integer.parseInt(tableSocios.getValueAt(fila, 0).toString());
                cargarDatosSocios(idSocio);
            }
        }
    }//GEN-LAST:event_tableSociosMouseClicked

    private void txtBuscarSocioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarSocioKeyReleased
        String text = txtBuscarSocio.getText();
        if(!text.trim().isEmpty()){
            try{
                int idBusqueda = Integer.parseInt(text);
                Socio socioEncontrado = socioService.obtenerSocioId(idBusqueda);
                if(socioEncontrado != null){
                    actualizarTablaSociosConUnSocio(socioEncontrado);
                }else{
                    limpiarTablaSocios();
                }
            }catch(NumberFormatException ex){
                limpiarTablaSocios();
            }
        }else{
            actualizarTablaSocios();
        }
    }//GEN-LAST:event_txtBuscarSocioKeyReleased

    private void txtNombreSocioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreSocioKeyTyped
        validacionTexto(evt);
    }//GEN-LAST:event_txtNombreSocioKeyTyped

    private void txtTelefonoSocioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoSocioKeyTyped
        validacionNumerica(evt);
    }//GEN-LAST:event_txtTelefonoSocioKeyTyped

    private void txtNombreActorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreActorKeyTyped
        validacionTexto(evt);
    }//GEN-LAST:event_txtNombreActorKeyTyped

    private void txtNombreDirectorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreDirectorKeyTyped
        validacionTexto(evt);
    }//GEN-LAST:event_txtNombreDirectorKeyTyped

    private void btnPeliculasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPeliculasMouseClicked
        tabbedPane.setSelectedIndex(5); 
        actualizarTablaPeliculas();
    }//GEN-LAST:event_btnPeliculasMouseClicked

    private void btnAbrirListaGenerosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirListaGenerosActionPerformed
    List<Genero> todosLosGeneros = peliculaService.obtenerTodosLosGeneros();
    SeleccionElementosDialog<Genero> seleccionGenerosDialog = new SeleccionElementosDialog<>(
            this.framePrincipal,
            true,
            todosLosGeneros,
            new HashSet<>(generosSeleccionadosPeliculas),
            Genero::getNombre,
            seleccion -> {
                generosSeleccionadosPeliculas.clear();
                generosSeleccionadosPeliculas.addAll(seleccion);
                actualizarUIConGenerosSeleccionadosPeliculas();
            }
    );
    seleccionGenerosDialog.setVisible(true);
    }//GEN-LAST:event_btnAbrirListaGenerosActionPerformed

    private void btnAgregarPeliculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarPeliculaActionPerformed
        String titulo = txtTituloPelicula.getText().trim();
        Director director = (Director) cboDirectoresPelicula.getSelectedItem();

        if(titulo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El título de la película no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(director == null) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un director.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(generosSeleccionadosPeliculas.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione al menos un género.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Pelicula nuevaPelicula = new Pelicula();
        nuevaPelicula.setTitulo(titulo);
        nuevaPelicula.setDirector(director);
        int peliculaId = peliculaService.agregarPeliculaConGeneros(nuevaPelicula, generosSeleccionadosPeliculas);

        if(peliculaId > 0) {
            JOptionPane.showMessageDialog(this, "Película agregada correctamente.");
            actualizarTablaPeliculas();
            limpiarFormularioPelicula();
        } else {
            JOptionPane.showMessageDialog(this, "Error al agregar la película.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAgregarPeliculaActionPerformed

    private void btnEliminarPeliculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarPeliculaActionPerformed
        int filaSeleccionada = tablePeliculas.getSelectedRow();
        if(filaSeleccionada == -1){
            JOptionPane.showMessageDialog(this, "Por favor, selecciona una pelicula para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int peliculaId = (int) tablePeliculas.getModel().getValueAt(filaSeleccionada, 0);
        int confirmacion = JOptionPane.showConfirmDialog(this, "Estas seguro de que deseas eliminar la pelicula seleccionada?", "Confirmar Eliminacion", JOptionPane.YES_NO_OPTION);
        if(confirmacion == JOptionPane.YES_OPTION){
            peliculaService.eliminarPelicula(peliculaId);
            actualizarTablaPeliculas();
        }
    }//GEN-LAST:event_btnEliminarPeliculaActionPerformed

    private void tablePeliculasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablePeliculasMouseClicked
        if(evt.getClickCount() == 2 && tablePeliculas.getSelectedRow() != -1){
            rellenarFormularioParaEdicion(tablePeliculas.getSelectedRow());
        }
    }//GEN-LAST:event_tablePeliculasMouseClicked

    private void btnEditarPeliculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarPeliculaActionPerformed
        if(txtIdPelicula.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Por favor, selecciona una película para editar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int peliculaId = Integer.parseInt(txtIdPelicula.getText());
        String titulo = txtTituloPelicula.getText();
        Director director = (Director) cboDirectoresPelicula.getSelectedItem();
        List<Genero> generosSeleccionadosParaEditar = new ArrayList<>(generosSeleccionadosPeliculas);

        Pelicula peliculaEditada = new Pelicula();
        peliculaEditada.setPeliculaId(peliculaId);
        peliculaEditada.setTitulo(titulo);
        peliculaEditada.setDirector(director);

        boolean exito = peliculaService.editarPeliculaConGeneros(peliculaEditada, generosSeleccionadosParaEditar);

        if(exito){
            actualizarTablaPeliculas(); 
            limpiarFormularioPelicula();
            JOptionPane.showMessageDialog(this, "Película actualizada correctamente.", "Información", JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(this, "Hubo un error al editar la película.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEditarPeliculaActionPerformed

    private void btnDirectoresSociosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDirectoresSociosActionPerformed
        Window windowAncestor = SwingUtilities.getWindowAncestor(this);
        if(windowAncestor instanceof Frame){
            Frame owner = (Frame) windowAncestor;
            SeleccionElementosDialog<Director> dialogo = new SeleccionElementosDialog<>(
                    owner,
                    true,
                    listaDirectores,
                    directoresSeleccionados,
                    Director::getNombre,
                    this::actualizarSeleccionDirectores
            );
            
            dialogo.setTitle("Seleccionar Directores");
            dialogo.setVisible(true);
        }else{
            
        }
    }//GEN-LAST:event_btnDirectoresSociosActionPerformed

    private void btnActoresSociosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActoresSociosActionPerformed
        Window windowAncestor = SwingUtilities.getWindowAncestor(this);
        if(windowAncestor instanceof Frame){
            Frame owner = (Frame) windowAncestor;
            SeleccionElementosDialog<Actor> dialogo = new SeleccionElementosDialog<>(
                    owner,
                    true,
                    listaActores,
                    actoresSeleccionados,
                    Actor::getNombre,
                    this::actualizarSeleccionActores
            );
            
            dialogo.setTitle("Seleccionar Actores");
            dialogo.setVisible(true);
        }else{
            
        }
    }//GEN-LAST:event_btnActoresSociosActionPerformed

    private void btnGenerosSociosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerosSociosActionPerformed
        Window windowAncestor = SwingUtilities.getWindowAncestor(this);
        if(windowAncestor instanceof Frame){
            Frame owner = (Frame) windowAncestor;
            SeleccionElementosDialog<Genero> dialogo = new SeleccionElementosDialog<>(
                    owner,
                    true,
                    listaGeneros,
                    generosSeleccionados,
                    Genero::getNombre,
                    this::actualizarSeleccionGeneros
            );
            
            dialogo.setTitle("Seleccionar Generos");
            dialogo.setVisible(true);
        }else{
            
        }
    }//GEN-LAST:event_btnGenerosSociosActionPerformed

    private void btnCintasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCintasMouseClicked
        tabbedPane.setSelectedIndex(6); 
        actualizarTablaCintas();
    }//GEN-LAST:event_btnCintasMouseClicked

    private void btnAgregarCintaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarCintaActionPerformed
        String peliculaTexto = txtPeliculaCinta.getText();
        String estado = (String) cboEstadoCinta.getSelectedItem();

        int peliculaId = cintaService.obtenerPeliculaIdPorNombre(peliculaTexto);
        if (peliculaId == -1) {
            JOptionPane.showMessageDialog(null, "Película no encontrada.");
            return;
        }

        Cinta nuevaCinta = new Cinta();
        nuevaCinta.setPeliculaId(peliculaId);
        nuevaCinta.setEstado(estado);

        cintaService.agregarCinta(nuevaCinta);

        if (nuevaCinta.getCintaId() > 0) {
            actualizarTablaCintas();
            JOptionPane.showMessageDialog(null, "Cinta agregada exitosamente.");
        } else {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al agregar la cinta.");
        }
    }//GEN-LAST:event_btnAgregarCintaActionPerformed

    private void btnEditarCintaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarCintaActionPerformed
        Cinta cintaSeleccionada = obtenerCintaSeleccionada();
        if (cintaSeleccionada == null) {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione una cinta para editar.");
            return;
        }

        String peliculaTexto = txtPeliculaCinta.getText();
        int peliculaId = cintaService.obtenerPeliculaIdPorNombre(peliculaTexto);
        String estado = (String) cboEstadoCinta.getSelectedItem();
        
        cintaSeleccionada.setPeliculaId(peliculaId);
        cintaSeleccionada.setEstado(estado);

        cintaService.actualizarCinta(cintaSeleccionada);

        actualizarTablaCintas();
        JOptionPane.showMessageDialog(null, "Cinta actualizada exitosamente.");
    }//GEN-LAST:event_btnEditarCintaActionPerformed

    private void btnEliminarCintaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarCintaActionPerformed
        Cinta cintaSeleccionada = obtenerCintaSeleccionadaDeLaTabla();

        if (cintaSeleccionada != null) {
            int confirmacion = JOptionPane.showConfirmDialog(this, 
                "¿Estás seguro de que quieres eliminar la cinta seleccionada?", 
                "Eliminar Cinta", 
                JOptionPane.YES_NO_OPTION);

            if (confirmacion == JOptionPane.YES_OPTION) {
                boolean exito = cintaService.eliminarCinta(cintaSeleccionada.getCintaId());

                if (exito) {
                    JOptionPane.showMessageDialog(this, "Cinta eliminada con éxito.", 
                        "Eliminar Cinta", JOptionPane.INFORMATION_MESSAGE);
                    actualizarTablaCintas();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al eliminar la cinta.", 
                        "Eliminar Cinta", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona una cinta para eliminar.", 
                "Eliminar Cinta", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnEliminarCintaActionPerformed

    private void btnBuscarPeliculaCintaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPeliculaCintaActionPerformed
        List<Pelicula> peliculas = obtenerListaPeliculas();

        Frame frameAncestor = (Frame) SwingUtilities.getWindowAncestor(this);

        SeleccionPeliculaDialog dialog = new SeleccionPeliculaDialog(frameAncestor, true, peliculas);
        dialog.setVisible(true);

        Pelicula peliculaSeleccionada = dialog.getPeliculaSeleccionada();
        if (peliculaSeleccionada != null) {
            txtPeliculaCinta.setText(peliculaSeleccionada.getTitulo());
        }
    }//GEN-LAST:event_btnBuscarPeliculaCintaActionPerformed

    private void btnAgregarPrestamoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarPrestamoActionPerformed
        int socioId = Integer.parseInt(txtIdSocio.getText());
        int cintaId = Integer.parseInt(txtIdCinta.getText());
        LocalDate fechaPrestamo = pickerFechaPrestamo.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate fechaDevolucion = pickerFechaDevolucion.getDate() != null ?
            pickerFechaDevolucion.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate() : null;

        Prestamo nuevoPrestamo = new Prestamo();
        nuevoPrestamo.setSocioId(socioId);
        nuevoPrestamo.setCintaId(cintaId);
        nuevoPrestamo.setFechaPrestamo(fechaPrestamo);
        nuevoPrestamo.setFechaDevolucion(fechaDevolucion);

        boolean exito = prestamoService.insertarPrestamo(nuevoPrestamo);

        if (exito) {
            JOptionPane.showMessageDialog(this, "Préstamo agregado exitosamente.");
            actualizarTablaPrestamos();
            limpiarSeleccionPrestamo();
        } else {
            JOptionPane.showMessageDialog(this, "Error al agregar el préstamo.");
        }
    }//GEN-LAST:event_btnAgregarPrestamoActionPerformed

    private void btnBuscarCintaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarCintaActionPerformed
        JDialog buscarCintaDialog = new JDialog(this.framePrincipal, "Buscar Cinta", true); // 'this.frame' debería ser tu JFrame principal
        buscarCintaDialog.setLayout(new BorderLayout());

        DefaultTableModel modeloTablaCintas = new DefaultTableModel(new Object[]{"ID", "Título", "Estado"}, 0);
        JTable tablaCintas = new JTable(modeloTablaCintas);

        List<Cinta> cintasDisponibles = cintaService.obtenerCintasDisponibles();
        for (Cinta cinta : cintasDisponibles) {
            modeloTablaCintas.addRow(new Object[]{cinta.getCintaId(), cinta.getTituloPelicula(), cinta.getEstado()});
        }

        JScrollPane scrollPane = new JScrollPane(tablaCintas);
        buscarCintaDialog.add(scrollPane, BorderLayout.CENTER);

        JPanel botonPanel = new JPanel();
        JButton btnSeleccionar = new JButton("Seleccionar");
        btnSeleccionar.addActionListener(e -> {
            int filaSeleccionada = tablaCintas.getSelectedRow();
            if (filaSeleccionada >= 0) {
                int cintaId = (int) modeloTablaCintas.getValueAt(filaSeleccionada, 0);
                String tituloCinta = (String) modeloTablaCintas.getValueAt(filaSeleccionada, 1);
                txtIdCinta.setText(String.valueOf(cintaId));
                lblCintaSeleccionadaPrestamo.setText(tituloCinta);
                lblCintaSeleccionadaPrestamo.setVisible(true);
                buscarCintaDialog.dispose();
            }
        });
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> {
            buscarCintaDialog.dispose();
            lblCintaSeleccionadaPrestamo.setText("");
        });

        botonPanel.add(btnSeleccionar);
        botonPanel.add(btnCancelar);
        buscarCintaDialog.add(botonPanel, BorderLayout.SOUTH);

        buscarCintaDialog.pack();
        buscarCintaDialog.setLocationRelativeTo(null);
        buscarCintaDialog.setVisible(true);
    }//GEN-LAST:event_btnBuscarCintaActionPerformed

    private void btnBuscarSocioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarSocioActionPerformed
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);

        JDialog buscarSocioDialog = new JDialog(frame, "Buscar Socio", true);
        buscarSocioDialog.setLayout(new BorderLayout());

        DefaultTableModel modeloTablaSocios = new DefaultTableModel(new Object[]{"ID", "Nombre", "Dirección", "Teléfono"}, 0);
        JTable tablaSocios = new JTable(modeloTablaSocios);

        List<Socio> socios = socioService.obtenerTodosLosSocios();
        for (Socio socio : socios) {
            modeloTablaSocios.addRow(new Object[]{socio.getSocioId(), socio.getNombre(), socio.getDireccion(), socio.getTelefono()});
        }

        JScrollPane scrollPane = new JScrollPane(tablaSocios);
        buscarSocioDialog.add(scrollPane, BorderLayout.CENTER);

        JPanel botonPanel = new JPanel();
        JButton btnSeleccionar = new JButton("Seleccionar");
        btnSeleccionar.addActionListener(e -> {
            int filaSeleccionada = tablaSocios.getSelectedRow();
            if (filaSeleccionada >= 0) {
                int socioId = (int) modeloTablaSocios.getValueAt(filaSeleccionada, 0);
                String nombreSocio = (String) modeloTablaSocios.getValueAt(filaSeleccionada, 1);
                txtIdSocio.setText(String.valueOf(socioId));
                lblSocioSeleccionadoPrestamo.setText(nombreSocio);
                buscarSocioDialog.dispose();
            }
        });
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> {
            buscarSocioDialog.dispose();
            lblSocioSeleccionadoPrestamo.setText("");
        });

        botonPanel.add(btnSeleccionar);
        botonPanel.add(btnCancelar);
        buscarSocioDialog.add(botonPanel, BorderLayout.SOUTH);

        buscarSocioDialog.pack();
        buscarSocioDialog.setLocationRelativeTo(null);
        buscarSocioDialog.setVisible(true);
    }//GEN-LAST:event_btnBuscarSocioActionPerformed

    private void btnEliminarPrestamoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarPrestamoActionPerformed
        int selectedRow = tablePrestamos.getSelectedRow();
        if (selectedRow >= 0) {
            int confirm = JOptionPane.showConfirmDialog(
                    null,
                    "¿Está seguro que desea eliminar el préstamo seleccionado?",
                    "Eliminar Préstamo",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    int prestamoId = Integer.parseInt(tablePrestamos.getValueAt(selectedRow, 0).toString());
                    boolean exito = prestamoService.eliminarPrestamo(prestamoId);

                    if (exito) {
                        ((DefaultTableModel) tablePrestamos.getModel()).removeRow(selectedRow);

                        JOptionPane.showMessageDialog(null, "Préstamo eliminado correctamente.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al eliminar el préstamo.");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al eliminar el préstamo: " + ex.getMessage());
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione un préstamo para eliminar.");
        }
    }//GEN-LAST:event_btnEliminarPrestamoActionPerformed

    private void btnSeleccionarPeliculaLDEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarPeliculaLDEActionPerformed
        List<Pelicula> listaPeliculas = obtenerListaPeliculas();

        SeleccionPeliculaDialog dialogoSeleccion = new SeleccionPeliculaDialog(JFrame.getFrames()[0], true, listaPeliculas);
        dialogoSeleccion.setVisible(true);

        Pelicula peliculaSeleccionada = dialogoSeleccion.getPeliculaSeleccionada();
        if (peliculaSeleccionada != null) {
            lblPeliculaSeleccionadaLDE.setText(peliculaSeleccionada.getTitulo());
            peliculaLDESeleccionadaId = peliculaSeleccionada.getPeliculaId();
        }
    }//GEN-LAST:event_btnSeleccionarPeliculaLDEActionPerformed

    private void btnAgregarSocioLDEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarSocioLDEActionPerformed
        String idSocioTexto = txtIdSocioLDE.getText();
        if (idSocioTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, introduce el ID del socio.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int idSocio;
        try {
            idSocio = Integer.parseInt(idSocioTexto);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El ID del socio debe ser un número.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (peliculaLDESeleccionadaId == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona una película primero.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean exito = listaEsperaService.agregarSocioAListaEspera(peliculaLDESeleccionadaId, idSocio);

        if (exito) {
            JOptionPane.showMessageDialog(this, "Socio agregado a la lista de espera con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            cargarDatosEnTablaListaEsperaCompleta();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo agregar al socio a la lista de espera.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAgregarSocioLDEActionPerformed

    private void btnNotificarSocioLDEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNotificarSocioLDEActionPerformed
        int filaSeleccionada = tableListaDeEspera.getSelectedRow();
        if(filaSeleccionada == -1){
            JOptionPane.showMessageDialog(this, "Por favor, selecciona un socio de la lista de espera.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int idSocio = (int) tableListaDeEspera.getValueAt(filaSeleccionada, 1);
        
        JOptionPane.showMessageDialog(this, "El socio con ID " + idSocio + " ha sido notificado.", "Notificacion", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btnNotificarSocioLDEActionPerformed

    private void btnEliminarListaLDEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarListaLDEActionPerformed
        int filaSeleccionada = tableListaDeEspera.getSelectedRow();
        if(filaSeleccionada == -1){
            JOptionPane.showMessageDialog(this, "Por favor, selecciona un socio de la lista de espera.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int listaEsperaId = (int) tableListaDeEspera.getValueAt(filaSeleccionada, 0);
        
        boolean exito = listaEsperaService.eliminarDeListaEspera(listaEsperaId);
        
        if(exito){
            JOptionPane.showMessageDialog(this, "Socio eliminado de la lista de espera con exito.", "Exito", JOptionPane.INFORMATION_MESSAGE);
            cargarDatosEnTablaListaEsperaCompleta();
        }else{
            JOptionPane.showMessageDialog(this, "No se puedo eliminar el socio de la lista de espera.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEliminarListaLDEActionPerformed

    private void btnPrestamosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrestamosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPrestamosActionPerformed
    void validacionTexto(java.awt.event.KeyEvent evt){
        char c = evt.getKeyChar();

        if ((Character.isLetter(c) || c == KeyEvent.VK_SPACE || c == KeyEvent.VK_BACK_SPACE)) {
        } else {
           evt.consume();
           JOptionPane.showMessageDialog(null, "Solo ingresar caracteres de tipo texto");
        } 
    }

    void validacionNumerica(java.awt.event.KeyEvent evt){
        char c = evt.getKeyChar();

        if ((Character.isDigit(c) || c == KeyEvent.VK_SPACE || c == KeyEvent.VK_BACK_SPACE)) {
        } else {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Solo ingresar caracteres de tipo numérico");  
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbrirListaGeneros;
    private javax.swing.JButton btnActores;
    private javax.swing.JButton btnActoresSocios;
    private javax.swing.JButton btnAgregarActor;
    private javax.swing.JButton btnAgregarCinta;
    private javax.swing.JButton btnAgregarDirector;
    private javax.swing.JButton btnAgregarPelicula;
    private javax.swing.JButton btnAgregarPrestamo;
    private javax.swing.JButton btnAgregarSocioLDE;
    private javax.swing.JButton btnBuscarCinta;
    private javax.swing.JButton btnBuscarPeliculaCinta;
    private javax.swing.JButton btnBuscarSocio;
    private javax.swing.JButton btnCintas;
    private javax.swing.JButton btnDirectores;
    private javax.swing.JButton btnDirectoresSocios;
    private javax.swing.JButton btnEditarActor;
    private javax.swing.JButton btnEditarCinta;
    private javax.swing.JButton btnEditarDirector;
    private javax.swing.JButton btnEditarPelicula;
    private javax.swing.JButton btnEditarSocio;
    private javax.swing.JButton btnEliminarActor;
    private javax.swing.JButton btnEliminarCinta;
    private javax.swing.JButton btnEliminarDirector;
    private javax.swing.JButton btnEliminarListaLDE;
    private javax.swing.JButton btnEliminarPelicula;
    private javax.swing.JButton btnEliminarPrestamo;
    private javax.swing.JButton btnEliminarSocio;
    private javax.swing.JButton btnGenerosSocios;
    private javax.swing.JButton btnListaDeEspera;
    private javax.swing.JButton btnNotificarSocioLDE;
    private javax.swing.JButton btnPeliculas;
    private javax.swing.JButton btnPrestamos;
    private javax.swing.JButton btnRegistrarSocio;
    private javax.swing.JButton btnSeleccionarPeliculaLDE;
    private javax.swing.JButton btnregistrarsocio;
    private javax.swing.JComboBox<Director> cboDirectoresPelicula;
    private javax.swing.JComboBox<String> cboEstadoCinta;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lblActoresSeleccionados;
    private javax.swing.JLabel lblCintaSeleccionadaPrestamo;
    private javax.swing.JLabel lblDirectoresSeleccionados;
    private javax.swing.JLabel lblGenerosSeleccionados;
    private javax.swing.JLabel lblGenerosSeleccionadosPeliculas;
    private javax.swing.JLabel lblPeliculaSeleccionadaLDE;
    private javax.swing.JLabel lblPeliculaSeleccionadaLDE1;
    private javax.swing.JLabel lblSocioSeleccionadoPrestamo;
    private javax.swing.JPanel pActores;
    private javax.swing.JPanel pCintas;
    private javax.swing.JPanel pDirectores;
    private javax.swing.JPanel pListaDeEspera;
    private javax.swing.JPanel pPeliculas;
    private javax.swing.JPanel pPrestamos;
    private javax.swing.JPanel pSocios;
    private javax.swing.JPanel panelBarra;
    private com.toedter.calendar.JDateChooser pickerFechaDevolucion;
    private com.toedter.calendar.JDateChooser pickerFechaPrestamo;
    public javax.swing.JTabbedPane tabbedPane;
    private javax.swing.JTable tableActores;
    private javax.swing.JTable tableCintas;
    private javax.swing.JTable tableDirectores;
    private javax.swing.JTable tableListaDeEspera;
    private javax.swing.JTable tablePeliculas;
    private javax.swing.JTable tablePrestamos;
    private javax.swing.JTable tableSocios;
    private javax.swing.JTextField txtBuscarActor;
    private javax.swing.JTextField txtBuscarCinta;
    private javax.swing.JTextField txtBuscarDirector;
    private javax.swing.JTextField txtBuscarPelicula;
    private javax.swing.JTextField txtBuscarPrestamo;
    private javax.swing.JTextField txtBuscarSocio;
    private javax.swing.JTextField txtDireccionSocio;
    private javax.swing.JTextField txtIdCinta;
    private javax.swing.JTextField txtIdPelicula;
    private javax.swing.JTextField txtIdSocio;
    private javax.swing.JTextField txtIdSocioLDE;
    private javax.swing.JTextField txtNombreActor;
    private javax.swing.JTextField txtNombreDirector;
    private javax.swing.JTextField txtNombreSocio;
    private javax.swing.JTextField txtPeliculaCinta;
    private javax.swing.JTextField txtPrestamoId;
    private javax.swing.JTextField txtTelefonoSocio;
    private javax.swing.JTextField txtTituloPelicula;
    // End of variables declaration//GEN-END:variables
   }
