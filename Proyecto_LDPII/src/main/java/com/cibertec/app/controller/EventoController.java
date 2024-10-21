package com.cibertec.app.controller;

import com.cibertec.app.service.EventoService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EventoController {

    EventoService eventoService;

    @GetMapping("/Cliente/eventos")
    public String listarEventos(HttpServletRequest request, Model model){
        Integer id = (Integer) request.getSession().getAttribute("id");
        model.addAttribute("eventos", eventoService.getEventoByCliente(id));
        return "eventos";
    }

//    public String nuevoEvento(){
//
//    }
}
