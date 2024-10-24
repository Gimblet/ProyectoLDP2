package com.cibertec.app.controller;

import com.cibertec.app.entity.*;
import com.cibertec.app.service.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Controller
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @Autowired
    EventoService eventoService;

    @Autowired
    PersonalService personalService;

    @Autowired
    EstablecimientoService establecimientoService;

    @Autowired
    FacturaService facturaService;

    @GetMapping("/")
    public String Index() {
        return "index";
    }

    @GetMapping("/Cliente/register")
    public String RegistroCliente(Model model) {
        Cliente cliente = new Cliente();
        model.addAttribute("cliente", cliente);
        return "Cliente/register";
    }

    @PostMapping("/Cliente/registerSubmited")
    public String validarRegistro(@ModelAttribute("cliente") Cliente cliente, BindingResult resultado, Model model) {
        Cliente clienteExistente = clienteService.getClienteByEmail(cliente.getCorreo());
        Cliente telefonoExistente = clienteService.getClienteByTelefono(cliente.getTelefono());

        if (cliente.getNombre() == null || cliente.getNombre().isEmpty()) {
            resultado.rejectValue("nombre", null, "Ingresar nombres");
        }

        if (clienteExistente != null && clienteExistente.getCorreo() != null && !clienteExistente.getCorreo().isEmpty()) {
            resultado.rejectValue("correo", null, "Ya existe una cuenta con este correo");
        }

        if (cliente.getClave() == null || cliente.getClave().isEmpty()) {
            resultado.rejectValue("clave", null, "Ingresar clave");
        }

        if (telefonoExistente != null && telefonoExistente.getTelefono() != null && !telefonoExistente.getTelefono().isEmpty()) {
            resultado.rejectValue("telefono", null, "Ya existe una cuenta con este telefono");
        }

        if (cliente.getDireccion() == null || cliente.getDireccion().isEmpty()) {
            resultado.rejectValue("direccion", null, "Ingresar Direccion");
        }

        if (resultado.hasErrors()) {
            model.addAttribute("cliente", cliente);
            return "/Cliente/register";
        }

        clienteService.saveCliente(cliente);
        return "redirect:/Cliente/register?success";
    }

    @PostMapping("/login")
    public String iniciarSesion(Cliente cliente, HttpServletRequest request, Model model) {

        boolean resultado = clienteService.login(cliente, request);

        if (resultado) {
            Integer id = (Integer) request.getSession().getAttribute("id");
            model.addAttribute("eventos", eventoService.getEventoByCliente(id));
            return "/Cliente/eventos";
        } else {
            return "redirect:?error";
        }
    }

    @GetMapping("/Cliente/eventos")
    public String listarEventos(HttpServletRequest request, Model model) {
        Integer id = (Integer) request.getSession().getAttribute("id");
        model.addAttribute("eventos", eventoService.getEventoByCliente(id));
        return "/Cliente/eventos";
    }

    @GetMapping("/Cliente/preCreateEvento")
    public String preCreateEvento(Model model, HttpServletRequest request) {
        Integer id = (Integer) request.getSession().getAttribute("id");
        Evento evento = new Evento();
        Cliente cliente = new Cliente();

        cliente.setIdCliente(id);
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
        evento.setMonto(calcularMontoEstablecimientoYPersonal(evento));

        Integer idcliente = (Integer) request.getSession().getAttribute("id");
        model.addAttribute("eventos",eventoService.getEventoByCliente(idcliente));
        return "Cliente/eventos";
    }

    @GetMapping("/Cliente/eliminarEvento/{id}")
    public String borrarEvento(@PathVariable Integer id, Model model,HttpServletRequest request){
        eventoService.deleteEvento(id);
        Integer idCliente = (Integer) request.getSession().getAttribute("id");
        model.addAttribute("eventos", eventoService.getEventoByCliente(idCliente));
        return "/Cliente/eventos";
    }

    @GetMapping("/Cliente/detallesEvento/{id}")
    public String detallesEvento(@PathVariable Integer id, Model model){
        Evento detalle = eventoService.buscarEventoById(id);
        Personal personal = detalle.getPersonal();
        Establecimiento establecimiento = detalle.getEstablecimiento();
        model.addAttribute("evento", detalle);
        model.addAttribute("personal", personal);
        model.addAttribute("establecimiento", establecimiento);
        return "/Cliente/detailsEvento";
    }

    @GetMapping("/Cliente/preConfirmarEvento/{id}")
    public String preConfirmEvento(@PathVariable Integer id, Model model){
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
        factura.setIdEvento(evento);

        model.addAttribute("evento", evento);
        model.addAttribute("cliente", cliente);
        model.addAttribute("personal", personal);
        model.addAttribute("establecimiento", establecimiento);
        model.addAttribute("montoLocal", montoLocal);
        model.addAttribute("montoDescuento", montoDescuento);
        model.addAttribute("montoIGV", montoIGV);
        model.addAttribute("factura", factura);

        return "/Cliente/confirmarEvento";
    }

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

    @PostMapping("/Cliente/confirmarEvento")
    public String confirmarEvento(@ModelAttribute("factura") Factura factura, HttpServletRequest request, Model model){
        String mensaje = facturaService.agregarFactura(factura);

//        System.out.println("ID:" + factura.getId() + "Fecha:" + factura.getFecha()  + factura.getIdEvento().getIdEvento() + "----------------------");

        if(mensaje.contains("Exito")){
            Integer idcliente = (Integer) request.getSession().getAttribute("id");
            model.addAttribute("eventos",eventoService.getEventoByCliente(idcliente));
            return "/Cliente/eventos";
        }
        else{
            return "confirmarEvento";
        }
    }

}
