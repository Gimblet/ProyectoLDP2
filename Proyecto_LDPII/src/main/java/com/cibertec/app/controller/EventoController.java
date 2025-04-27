package com.cibertec.app.controller;


import com.cibertec.app.dto.evento.EventoRequestDTO;
import com.cibertec.app.dto.evento.EventoResponseDTO;
import com.cibertec.app.service.EventoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/eventos")
public class EventoController {

    private final EventoService service;
    public EventoController(EventoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<EventoResponseDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventoResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<EventoResponseDTO> create(@RequestBody EventoRequestDTO req) {
        EventoResponseDTO resp = service.create(req);
        return ResponseEntity.status(201).body(resp);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventoResponseDTO> update(@PathVariable Long id, @RequestBody EventoRequestDTO req) {
        return ResponseEntity.ok(service.update(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

