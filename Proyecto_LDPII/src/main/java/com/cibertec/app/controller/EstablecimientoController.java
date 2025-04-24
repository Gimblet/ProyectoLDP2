package com.cibertec.app.controller;

import com.cibertec.app.dto.establecimiento.EstablecimientoRequestDTO;
import com.cibertec.app.dto.establecimiento.EstablecimientoResponseDTO;
import com.cibertec.app.service.EstablecimientoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/establecimientos")
@RequiredArgsConstructor
public class EstablecimientoController {
    private final EstablecimientoService establecimientoService;


    @GetMapping
    public List<EstablecimientoResponseDTO> listar(){
        return establecimientoService.listar();
    }

    @PostMapping
    public EstablecimientoResponseDTO guardar(@RequestBody EstablecimientoRequestDTO requestDTO){
        return establecimientoService.guardar(requestDTO);
    }

    @GetMapping("/{id}")
    public EstablecimientoResponseDTO obtener(@PathVariable Long id){
        return establecimientoService.obtenerPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable long id){
        establecimientoService.eliminar(id);
    }
}
