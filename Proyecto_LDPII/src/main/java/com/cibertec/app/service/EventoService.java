package com.cibertec.app.service;

import com.cibertec.app.dto.evento.EventoRequestDTO;
import com.cibertec.app.dto.evento.EventoResponseDTO;
import com.cibertec.app.entity.Evento;

import java.util.List;

public interface EventoService {
    List<EventoResponseDTO> getAll();
    EventoResponseDTO getById(Long id);
    EventoResponseDTO create(EventoRequestDTO request);
    EventoResponseDTO update(Long id, EventoRequestDTO request);
    void delete(Long id);
}
