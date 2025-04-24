package com.cibertec.app.controller;

import com.cibertec.app.dto.factura.FacturaRequestDTO;
import com.cibertec.app.dto.factura.FacturaResponseDTO;
import com.cibertec.app.service.FacturaService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/factura")
@RequiredArgsConstructor
public class FacturaController {
    private final FacturaService facturaService;

    @GetMapping
    public List<FacturaResponseDTO> listarFacturas() {
        return facturaService.getAllFacturas();
    }

    @GetMapping("/{id}")
    public FacturaResponseDTO obtenerFacturaPorId(@PathVariable Long id) {
        return facturaService.getFacturaById(id);
    }

    @PostMapping
    public FacturaResponseDTO guardarFactura(@RequestBody FacturaRequestDTO requestDTO) {
        return facturaService.saveFactura(requestDTO);
    }

    @GetMapping("/cliente/{idCliente}")
    public List<FacturaResponseDTO> obtenerFacturaPorCliente(@PathVariable Long idCliente) {
        return facturaService.getFacturaByCliente(idCliente);
    }

    @GetMapping("/evento/{idEvento}")
    public FacturaResponseDTO obtenerFacturaPorEvento(@PathVariable Long idEvento) {
        return facturaService.getFacturaByEvento(idEvento);
    }

}
