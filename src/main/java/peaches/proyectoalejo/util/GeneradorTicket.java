    package peaches.proyectoalejo.util;
    import com.itextpdf.kernel.pdf.PdfDocument;
    import com.itextpdf.kernel.pdf.PdfWriter;
    import com.itextpdf.layout.Document;
    import com.itextpdf.layout.element.Paragraph;

    import javax.swing.*;
    import java.io.FileNotFoundException;
    import java.util.List;
    import peaches.proyectoalejo.model.Cliente;
    import peaches.proyectoalejo.model.DetalleRepuesto;
    import peaches.proyectoalejo.model.DetalleServicio;
    import peaches.proyectoalejo.model.Empleado;
import peaches.proyectoalejo.model.Repuesto;
    import peaches.proyectoalejo.model.Servicio;
    import peaches.proyectoalejo.model.Venta;
    import peaches.proyectoalejo.service.ClienteService;
    import peaches.proyectoalejo.service.DetalleRepuestoService;
    import peaches.proyectoalejo.service.DetalleServicioService;
    import peaches.proyectoalejo.service.EmpleadoService;
import peaches.proyectoalejo.service.RepuestoService;
    import peaches.proyectoalejo.service.ServicioService;
    import peaches.proyectoalejo.service.VentaService;

    public  class GeneradorTicket {

        DetalleServicioService detalleServicioService = new DetalleServicioService();
        DetalleRepuestoService detalleRepuestoService = new DetalleRepuestoService();
        EmpleadoService empleadoService = new EmpleadoService();
        ClienteService clienteService = new ClienteService();
        VentaService ventaService = new VentaService();
        ServicioService    servicioService = new ServicioService();
        RepuestoService repuestoService = new RepuestoService();
        double total=0.0, totalRepuesto=0.0, totalServicio=0.0;

        public void generarTicket(String idVenta) {
            Venta venta = ventaService.obtenerVentaPorId(idVenta);
            
            try (Document document = new Document(new PdfDocument(new PdfWriter("./voucher.pdf")))) {
               document.setMargins(20, 20, 20, 20); // Establecer márgenes

                // Encabezado del voucher
                Paragraph header = new Paragraph("SISTEMA CYR");
                header.setBold();
                document.add(header);
                document.add(new Paragraph("Av. Túpac Amaru Nº 7140 MZ. L-02 Lote Nº 13 P.J. Año Nuevo"));
                document.add(new Paragraph("Teléfono: 123-456-7890"));
                document.add(new Paragraph("Numero de Venta: " + idVenta));

                document.add(new Paragraph(""));

                List<DetalleServicio> ListaDetalleServicio = detalleServicioService.obtenerTodosDetalleServicioPorVenta(idVenta);
                List<DetalleRepuesto> ListaDetalleRepuesto = detalleRepuestoService.obtenerTodosDetalleRepuestosPorVenta(idVenta);

                Empleado empleado = empleadoService.obtenerEmpleadoDni(venta.getDniEmpleado());
                Cliente cliente = clienteService.obtenerClientePorDni(venta.getDni());

                // Detalles de la venta
                document.add(new Paragraph("Cliente: " + cliente.getNombre() + cliente.getApellido()));
                document.add(new Paragraph("Empleado: " + empleado.getNombreEmpleado()));
                document.add(new Paragraph("Fecha: " + venta.getFecha()));
                document.add(new Paragraph(""));

                // Detalles de los productos comprados
                document.add(new Paragraph("Servicios adquiridos:"));
                for (DetalleServicio detalleServicio : ListaDetalleServicio) {
                    
                    totalServicio += detalleServicio.getPrecio();
                  Servicio servicio = servicioService.obtenerServicioPorId(detalleServicio.getIdServicio());
                  document.add(new Paragraph("- " + servicio.getDescripcion()+ ": $" + detalleServicio.getPrecio()));
                }
                document.add(new Paragraph(""));
                
                      document.add(new Paragraph("Repuestos adquiridos:"));
                for (DetalleRepuesto detalleRepuesto : ListaDetalleRepuesto) {
                                        totalRepuesto += detalleRepuesto.getTotal();

                                    Repuesto repuesto = repuestoService.obtenerRepuestoPorId(detalleRepuesto.getIdRepuesto());


                    document.add(new Paragraph("- " + repuesto.getDescripcion()+ ": $" + detalleRepuesto.getTotal()+ " (Cantidad: "+ detalleRepuesto.getCantidad() + " - Precio Unidad: $" + detalleRepuesto.getPrecioUnidad()+")"));
                }

                total = totalRepuesto + totalServicio;
                // Total de la venta               
                document.add(new Paragraph(""));
                document.add(new Paragraph("Total: $" + total));

                JOptionPane.showMessageDialog(null, "Voucher generado exitosamente");
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al generar el voucher: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
