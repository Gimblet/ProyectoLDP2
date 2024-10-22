package com.cibertec.app.controller;

import com.cibertec.app.entity.Cliente;
import com.cibertec.app.entity.Establecimiento;
import com.cibertec.app.entity.Evento;
import com.cibertec.app.service.ClienteService;
import com.cibertec.app.service.EstablecimientoService;
import com.cibertec.app.service.EventoService;
import com.cibertec.app.service.PersonalService;
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

        setEventoFecha(evento, evento.getFechaString());
        calcularMonto(evento);
        eventoService.crearEvento(evento);

        //TODO: al agregar evento sale parseado con un formato

        Integer idcliente = (Integer) request.getSession().getAttribute("id");
        model.addAttribute("eventos",eventoService.getEventoByCliente(idcliente));
        return "Cliente/eventos";
    }

    public void setEventoFecha(Evento evento, String fechaString) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date fecha = formatter.parse(fechaString);
            evento.setFecha(fecha);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void calcularMonto(Evento evento){
        Establecimiento local = evento.getEstablecimiento();

        BigDecimal precio = local.getPrecio();
        precio = precio.multiply(BigDecimal.valueOf(evento.getDuracion()));

        evento.setMonto(precio);
    }

    @GetMapping("/Cliente/eliminarEvento/{id}")
    public String borrarEvento(@PathVariable Integer id, Model model,HttpServletRequest request){
        eventoService.deleteEvento(id);
        Integer idCliente = (Integer) request.getSession().getAttribute("id");
        model.addAttribute("eventos", eventoService.getEventoByCliente(idCliente));
        return "/Cliente/eventos";
    }
}
