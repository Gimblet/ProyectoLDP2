package com.cibertec.app.controller;

import com.cibertec.app.dto.personal.PersonalRequestDto;
import com.cibertec.app.dto.personal.PersonalResponseDto;
import com.cibertec.app.entity.Personal;
import com.cibertec.app.service.PersonalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/personal")
@RequiredArgsConstructor
public class PersonalController {

    private final PersonalService personalService;

    @GetMapping
    public ResponseEntity<List<Personal>> listarEntidades() {
        return ResponseEntity.ok(personalService.obtenerListaPersonal());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonalResponseDto> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(personalService.obtenerPersonalPorId(id));
    }

    @PostMapping
    public ResponseEntity<PersonalResponseDto> crear(@RequestBody PersonalRequestDto dto) {
        return ResponseEntity.ok(personalService.crearPersonal(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonalResponseDto> actualizar(@PathVariable Long id, @RequestBody PersonalRequestDto dto) {
        return ResponseEntity.ok(personalService.actualizarPersonal(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        personalService.eliminarPersonal(id);
        return ResponseEntity.noContent().build();
    }
}
