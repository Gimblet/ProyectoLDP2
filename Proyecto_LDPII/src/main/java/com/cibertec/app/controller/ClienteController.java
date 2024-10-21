package com.cibertec.app.controller;

import com.cibertec.app.entity.Cliente;
import com.cibertec.app.entity.Evento;
import com.cibertec.app.service.ClienteService;
import com.cibertec.app.service.EventoService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @Autowired
    EventoService eventoService;

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

        System.out.println(cliente.getClave());
        System.out.println(cliente.getDireccion());

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

}
