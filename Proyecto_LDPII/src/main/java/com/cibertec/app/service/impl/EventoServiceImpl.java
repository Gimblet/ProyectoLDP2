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

import jakarta.persistence.EntityNotFoundException;

import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EventoServiceImpl implements EventoService {

    private final EventoRepository eventoRepository;
    private final PersonalRepository personalRepository;
    private final EstablecimientoRepository establecimientoRepository;
    private final ClienteRepository clienteRepository;
    private final EventoMapper eventoMapper;

    @Override
    public List<EventoResponseDTO> getAll() {
        return eventoRepository.findAll().stream()
                .map(eventoMapper::toDto)
                .toList();
    }

    @Override
    public EventoResponseDTO getById(Long id) {
        Evento evento = loadEntityById(eventoRepository, id, "Evento");
        return eventoMapper.toDto(evento);
    }


    public record AssociatedEntities(
            Personal personal,
            Establecimiento establecimiento,
            Cliente cliente,
            Optional<Evento> evento
    ) {}

    private <T> T loadEntityById(
            CrudRepository<T, Long> repository,
            Long id,
            String entityName
    ) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(entityName + " no encontrado con id " + id));
    }

    private AssociatedEntities getForCreate(EventoRequestDTO req) {
        Personal personal = loadEntityById(personalRepository, req.getIdPersonal(), "Personal");
        Establecimiento establecimiento = loadEntityById(establecimientoRepository, req.getIdEstablecimiento(), "Establecimiento");
        Cliente cliente = loadEntityById(clienteRepository, req.getIdCliente(), "Cliente");
        return new AssociatedEntities(personal, establecimiento, cliente, Optional.empty());
    }

    private AssociatedEntities getForUpdate(Long id, EventoRequestDTO req) {
        Personal personal = loadEntityById(personalRepository, req.getIdPersonal(), "Personal");
        Establecimiento establecimiento = loadEntityById(establecimientoRepository, req.getIdEstablecimiento(), "Establecimiento");
        Cliente cliente = loadEntityById(clienteRepository, req.getIdCliente(), "Cliente");
        Evento evento = loadEntityById(eventoRepository, id, "Evento");
        return new AssociatedEntities(personal, establecimiento, cliente, Optional.of(evento));
    }

    @Override
    public EventoResponseDTO create(EventoRequestDTO req) {
        AssociatedEntities asociados = getForCreate(req);

        Evento nuevo = eventoMapper.toEntity(
                req,
                asociados.personal(),
                asociados.establecimiento(),
                asociados.cliente()
        );

        Evento guardado = eventoRepository.save(nuevo);
        return eventoMapper.toDto(guardado);
    }

    @Override
    public EventoResponseDTO update(Long id, EventoRequestDTO req) {
        AssociatedEntities asociados = getForUpdate(id, req);

        Evento existente = asociados.evento()
                .orElseThrow(() -> new EntityNotFoundException("Evento no presente para actualización"));

        existente.setNombre(req.getNombre());
        existente.setFecha(req.getFecha());
        existente.setDuracion(req.getDuracion());
        existente.setMonto(req.getMonto());
        existente.setPersonal(asociados.personal());
        existente.setEstablecimiento(asociados.establecimiento());
        existente.setCliente(asociados.cliente());

        Evento modificado = eventoRepository.save(existente);
        return eventoMapper.toDto(modificado);
    }

    @Override
    public void delete(Long id) {
        if (!eventoRepository.existsById(id)) {
            throw new EntityNotFoundException("Evento no encontrado con id " + id);
        }
        eventoRepository.deleteById(id);
    }
}
