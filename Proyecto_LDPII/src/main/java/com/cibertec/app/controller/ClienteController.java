package com.cibertec.app.controller;

import com.cibertec.app.dto.cliente.ClienteRequestDTO;
import com.cibertec.app.dto.cliente.ClienteResponseDTO;
import com.cibertec.app.dto.factura.FacturaResponseDTO;
import com.cibertec.app.entity.*;
import com.cibertec.app.mapper.ClienteMapper;
import com.cibertec.app.service.ClienteService;
import com.cibertec.app.service.EstablecimientoService;
import com.cibertec.app.service.EventoService;
import com.cibertec.app.service.FacturaService;
import com.cibertec.app.service.PersonalService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cliente")
public class ClienteController {
    private final ClienteService service;
    private final ClienteMapper mapper;

    @Autowired
    EventoService eventoService;

    @Autowired
    PersonalService personalService;

    @Autowired
    EstablecimientoService establecimientoService;
    
    @Autowired
    FacturaService facturaService;
   
    @GetMapping
    private List<ClienteResponseDTO> listar(){
        return service.getAllCliente();
    }
    
    @PostMapping
    private ClienteResponseDTO guardar(ClienteRequestDTO requestDTO){
        return service.saveCliente(requestDTO);
    }
   
    @GetMapping("/{id}")
    private ClienteResponseDTO obtenerPorId(@PathVariable Long id){
        return mapper.toDto(service.getClienteById(id));
    }
    
    @GetMapping("/e{email}")
    private ClienteResponseDTO obtenerPorEmail(@PathVariable String email){
        return mapper.toDto(service.getClienteByEmail(email));
    }

    @GetMapping("/t{telefono}")
    private ClienteResponseDTO obtenerPorTelefono(@PathVariable String telefono){
        return mapper.toDto(service.getClienteByTelefono(telefono));
    }
    
    @DeleteMapping("/{id}")
    private void eliminarPorId(@PathVariable Long id){
        service.deleteClienteById(id);
    }
    
//    TODO: Refactorizar Login Antiguo usando API
//    @PostMapping("/login")
//    public String iniciarSesion(Cliente cliente, HttpServletRequest request, Model model) {
//
//        boolean resultado = service.login(cliente, request);
//
//        if (resultado) {
//            Long id = (Long) request.getSession().getAttribute("id");
//            model.addAttribute("eventos", eventoService.getEventoByCliente(id));
//            return "/Cliente/eventos";
//        } else {
//            return "redirect:?error";
//        }
//    }

    @GetMapping("/Cliente/eventos")
    public String listarEventos(HttpServletRequest request, Model model) {
        Long id = (Long) request.getSession().getAttribute("id");
        model.addAttribute("eventos", eventoService.getEventoByCliente(id));
        return "/Cliente/eventos";
    }
    
//    TODO: Refactorizar cerrar Sesion Antiguo con API    
//    @GetMapping("/Cliente/cerrarSesion")
//    public String cerrarSesion(HttpServletRequest request) {
//    	request.getSession().invalidate();
//    	return "index";
//    }

    @GetMapping("/Cliente/preCreateEvento")
    public String preCreateEvento(Model model, HttpServletRequest request) {
        Long id = (Long) request.getSession().getAttribute("id");
        Evento evento = new Evento();
        Cliente cliente = new Cliente();

        cliente.setId(id);
        evento.setCliente(cliente);

        model.addAttribute("evento", evento);
        model.addAttribute("personalList", personalService.obtenerListaPersonal());
        model.addAttribute("establecimientoList", establecimientoService.getAllEstablecimiento());
        return "/Cliente/createEvento";
    }

    @PostMapping("/Cliente/createEvento")
    public String createEvento(@ModelAttribute("evento") Evento evento, BindingResult resultado, Model model, HttpServletRequest request) {

        if (evento.getNombre() == null || evento.getNombre().isEmpty()) {
            resultado.rejectValue("nombre", null, "Ingresar Nombre del Evento");
        }

        if (evento.getFechaString() == null || evento.getFechaString().isEmpty()) {
            resultado.rejectValue("fecha", null, "Ingresar fecha");
        }

        if (evento.getDuracion() <= 0) {
            resultado.rejectValue("duracion", null, "El evento debe durar como minimo una hora");
        }

        if (resultado.hasErrors()) {
            model.addAttribute("evento", evento);
            model.addAttribute("personalList", personalService.obtenerListaPersonal());
            model.addAttribute("establecimientoList", establecimientoService.getAllEstablecimiento());
            return "/Cliente/createEvento";
        }

        LocalDateTime parser = LocalDateTime.parse(evento.getFechaString());
        parser.format(DateTimeFormatter.ofPattern("yyyy-MM-dd-hh:mm"));
        evento.setFecha(parser);
        evento.setMonto(calcularMontoEstablecimientoYPersonal(evento));
        eventoService.crearEvento(evento);

        Long idcliente = (Long) request.getSession().getAttribute("id");
        model.addAttribute("eventos", eventoService.getEventoByCliente(idcliente));
        return "Cliente/eventos";
    }


    @GetMapping("/Cliente/eliminarEvento/{id}")
    public String borrarEvento(@PathVariable Long id, Model model,HttpServletRequest request){
        eventoService.deleteEvento(id);
        Long idCliente = (Long) request.getSession().getAttribute("id");
        model.addAttribute("eventos", eventoService.getEventoByCliente(idCliente));
        return "/Cliente/eventos";
    }
    
    @GetMapping("/Cliente/detallesEvento/{id}")
    public String detallesEvento(@PathVariable Long id, Model model){
        Evento detalle = eventoService.buscarEventoById(id);
        Personal personal = detalle.getPersonal();
        Establecimiento establecimiento = detalle.getEstablecimiento();
        model.addAttribute("evento", detalle);
        model.addAttribute("personal", personal);
        model.addAttribute("establecimiento", establecimiento);
        return "/Cliente/detailsEvento";
    }

    @GetMapping("/Cliente/preConfirmarEvento/{id}")
    public String preConfirmEvento(@PathVariable Long id, Model model){
        Factura factura = new Factura();

        Evento evento = eventoService.buscarEventoById(id);
        Personal personal = evento.getPersonal();
        Establecimiento establecimiento = evento.getEstablecimiento();
        Cliente cliente = evento.getCliente();

        factura.setDescuento(0.05);

        BigDecimal montoLocal = obtenerMontoLocal(evento);
        BigDecimal montoLocalYPersonal = calcularMontoEstablecimientoYPersonal(evento);
        BigDecimal montoDescuento = obtenerMontoDescuento(montoLocalYPersonal, factura);
        BigDecimal montoIGV = obtenerMontoIGV(montoLocalYPersonal, montoDescuento);
        
        factura.setPrecioFinal(calcularMontoTotal(montoLocalYPersonal,montoDescuento,montoIGV));
        factura.setEvento(evento);

        model.addAttribute("evento", evento);
        model.addAttribute("idEvento", evento.getIdEvento());
        model.addAttribute("cliente", evento.getCliente());
        model.addAttribute("personal", personal);
        model.addAttribute("establecimiento", establecimiento);
        model.addAttribute("montoLocal", montoLocal);
        model.addAttribute("montoDescuento", montoDescuento);
        model.addAttribute("montoIGV", montoIGV);
        model.addAttribute("factura", factura);

        return "/Cliente/confirmarEvento";
    }
    
    /*@GetMapping("/Cliente/facturas")
    public String listarFacturas(HttpServletRequest request, Model model) {
        Long id = (Long) request.getSession().getAttribute("id");
        List<FacturaResponseDTO> facturas = facturaService.getFacturaByCliente(id);
        model.addAttribute("facturas", facturas);
        return "/Cliente/facturas"; 
    }*/

    public BigDecimal obtenerMontoLocal(Evento evento){
        Establecimiento local = evento.getEstablecimiento();

        BigDecimal precio = local.getPrecio();
        precio = precio.multiply(BigDecimal.valueOf(evento.getDuracion()));

        return precio;
    }

    public BigDecimal calcularMontoEstablecimientoYPersonal(Evento evento){
        Establecimiento local = evento.getEstablecimiento();
        Personal personal = evento.getPersonal();

        BigDecimal precio = local.getPrecio();
        precio = precio.multiply(BigDecimal.valueOf(evento.getDuracion()));
        precio = precio.add(personal.getMonto());

        return precio;
    }

    public BigDecimal obtenerMontoDescuento(BigDecimal resultado, Factura factura){
        return resultado.multiply(BigDecimal.valueOf(factura.getDescuento()));
    }

    public BigDecimal obtenerMontoIGV(BigDecimal resultado, BigDecimal igv){
        resultado = resultado.subtract(igv);
        return resultado.multiply(BigDecimal.valueOf(0.18));
    }

    public BigDecimal calcularMontoTotal(BigDecimal resultado, BigDecimal descuento, BigDecimal IGV){
        resultado = resultado.subtract(descuento); //restando el descuento del 5%
        return resultado.add(IGV); //Devuelve el el precion con descuento + el costo del IGV

    }

    /*@PostMapping("/Cliente/confirmarEvento")
    public String confirmarEvento(@ModelAttribute("factura") Factura factura, HttpServletRequest request, Model model){
        facturaService.saveFactura(factura);
        Long id = (Long) request.getSession().getAttribute("id");
        model.addAttribute("eventos", eventoService.getEventoByCliente(id));
        return "/Cliente/eventos";

    }*/

    /*@GetMapping("/Cliente/exportar/{idFactura}")
    public void exportarPDF(@PathVariable Long idFactura, HttpServletResponse response) throws FileNotFoundException, JRException {
        File file = ResourceUtils.getFile("classpath:ReporteFactura.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(Collections.singleton(facturaService.getFacturaById(idFactura)));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);
        OutputStream outStream = null;
        try {
            outStream = response.getOutputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
    }*/
}
