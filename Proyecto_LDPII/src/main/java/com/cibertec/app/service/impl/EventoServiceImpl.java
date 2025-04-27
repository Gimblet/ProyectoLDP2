package com.cibertec.app.service.impl;

import com.cibertec.app.dto.evento.EventoRequestDTO;
import com.cibertec.app.dto.evento.EventoResponseDTO;
import com.cibertec.app.entity.*;
import com.cibertec.app.mapper.evento.EventoMapper;
import com.cibertec.app.repository.ClienteRepository;
import com.cibertec.app.repository.EstablecimientoRepository;
import com.cibertec.app.repository.EventoRepository;
import com.cibertec.app.repository.PersonalRepository;
import com.cibertec.app.service.EventoService;
import com.cibertec.app.service.FacturaService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class EventoServiceImpl implements EventoService {

    private final EventoRepository eventoRepo;
    private final PersonalRepository personalRepo;
    private final EstablecimientoRepository estableRepo;
    private final ClienteRepository clienteRepo;
    private final EventoMapper mapper;

    public EventoServiceImpl(
            EventoRepository eventoRepo,
            PersonalRepository personalRepo,
            EstablecimientoRepository estableRepo,
            ClienteRepository clienteRepo,
            EventoMapper mapper) {
        this.eventoRepo = eventoRepo;
        this.personalRepo = personalRepo;
        this.estableRepo = estableRepo;
        this.clienteRepo = clienteRepo;
        this.mapper = mapper;
    }

    @Override
    public List<EventoResponseDTO> getAll() {
        return eventoRepo.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public EventoResponseDTO getById(Long id) {
        Evento evento = eventoRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Evento no encontrado con id " + id));
        return mapper.toDto(evento);
    }

    @Override
    public EventoResponseDTO create(EventoRequestDTO req) {
        Personal p = personalRepo.findById(req.getIdPersonal())
                .orElseThrow(() -> new EntityNotFoundException("Personal no encontrado con id " + req.getIdPersonal()));
        Establecimiento e = estableRepo.findById(req.getIdEstablecimiento())
                .orElseThrow(() -> new EntityNotFoundException("Establecimiento no encontrado con id " + req.getIdEstablecimiento()));
        Cliente c = clienteRepo.findById(req.getIdCliente())
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado con id " + req.getIdCliente()));

        Evento nuevo = mapper.toEntity(req, p, e, c);
        Evento guardado = eventoRepo.save(nuevo);
        return mapper.toDto(guardado);
    }

    @Override
    public EventoResponseDTO update(Long id, EventoRequestDTO req) {
        Evento existente = eventoRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Evento no encontrado con id " + id));

        Personal personal = personalRepo.findById(req.getIdPersonal())
                .orElseThrow(() -> new EntityNotFoundException("Personal no encontrado con id " + req.getIdPersonal()));
        Establecimiento establecimiento = estableRepo.findById(req.getIdEstablecimiento())
                .orElseThrow(() -> new EntityNotFoundException("Establecimiento no encontrado con id " + req.getIdEstablecimiento()));
        Cliente cliente = clienteRepo.findById(req.getIdCliente())
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado con id " + req.getIdCliente()));

        existente.setNombre(req.getNombre());
        existente.setFecha(req.getFecha());
        existente.setDuracion(req.getDuracion());
        existente.setMonto(req.getMonto());
        existente.setPersonal(personal);
        existente.setEstablecimiento(establecimiento);
        existente.setCliente(cliente);

        Evento modificado = eventoRepo.save(existente);
        return mapper.toDto(modificado);
    }

    @Override
    public void delete(Long id) {
        if (!eventoRepo.existsById(id)) {
            throw new EntityNotFoundException("Evento no encontrado con id " + id);
        }
        eventoRepo.deleteById(id);
    }
}
