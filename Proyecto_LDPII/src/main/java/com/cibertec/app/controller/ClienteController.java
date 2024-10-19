package com.cibertec.app.controller;

import com.cibertec.app.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClienteController {

    @Autowired
    ClienteRepository clienteRepository;

    @GetMapping("/")
   public String Index() {
        return "index";
   }
}
