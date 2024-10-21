package com.cibertec.app.service.impl;

import com.cibertec.app.entity.Evento;
import com.cibertec.app.repository.EventoRepository;
import com.cibertec.app.service.EventoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventoServiceImpl implements EventoService {

    EventoRepository eventoRepository;

    public EventoServiceImpl(EventoRepository eventoRepository) {
        super();
        this.eventoRepository = eventoRepository;
    }

    @Override
    public List<Evento> getAllEvento() {
        return eventoRepository.findAll();
    }

    @Override
    public List<Evento> getEventoByCliente(Integer idCLiente) {
        return eventoRepository.findEventosByCliente(idCLiente);
    }
}
