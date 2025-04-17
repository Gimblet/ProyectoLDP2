package com.cibertec.app.service.impl;

import com.cibertec.app.entity.Evento;
import com.cibertec.app.entity.Factura;
import com.cibertec.app.repository.EventoRepository;
import com.cibertec.app.service.EventoService;
import com.cibertec.app.service.FacturaService;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventoServiceImpl implements EventoService {

    EventoRepository eventoRepository;
    FacturaService facturaService;

    public EventoServiceImpl(EventoRepository eventoRepository) {
        super();
        this.eventoRepository = eventoRepository;
        this.facturaService = facturaService;
    }

    @Override
    public List<Evento> getAllEvento() {
        return eventoRepository.findAll();
    }

    @Override
    public List<Evento> getEventoByCliente(Long idCLiente) {
        return eventoRepository.findEventosByCliente(idCLiente);
    }

    @Override
    public Evento crearEvento(Evento evento) {
        return eventoRepository.save(evento);
    }

    @Override
    public String deleteEvento(Long idEvento) {
        eventoRepository.deleteById(idEvento);
        return  "Evento " + String.valueOf(idEvento) + "Eliminado";
    }
    
    @Override
    public Evento buscarEventoById(Long id) {
        return eventoRepository.findById(id).get();
    }
}
