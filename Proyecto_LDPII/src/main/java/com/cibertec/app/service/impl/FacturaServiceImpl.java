package com.cibertec.app.service.impl;

import com.cibertec.app.dto.factura.FacturaRequestDTO;
import com.cibertec.app.dto.factura.FacturaResponseDTO;
import com.cibertec.app.entity.Evento;
import com.cibertec.app.entity.Factura;
import com.cibertec.app.mapper.factura.FacturaMapper;
import com.cibertec.app.repository.FacturaRepository;
import com.cibertec.app.service.EventoService;
import com.cibertec.app.service.FacturaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FacturaServiceImpl implements FacturaService {

    private final FacturaRepository facturaRepository;
    private final FacturaMapper facturaMapper;
    private final EventoService eventoService;

    @Override
    public List<FacturaResponseDTO> getAllFacturas() {
        return facturaRepository.findAll().stream()
                .map(facturaMapper::toDto)
                .toList();
    }

    @Override
    public FacturaResponseDTO getFacturaById(Long id) {
        return facturaRepository.findById(id)
                .map(facturaMapper::toDto)
                .orElseThrow(() -> new RuntimeException(("Factura No encontrada con id: " + id)));
    }

    @Override
    public List<FacturaResponseDTO> getFacturaByCliente(Long idCliente) {
        return facturaRepository.obtenerFacturaByCliente(idCliente)
                .stream().map(facturaMapper::toDto)
                .toList();
    }

    @Override
    public FacturaResponseDTO getFacturaByEvento(Long idEvento) {
        Factura factura = facturaRepository.findByEventoIdEvento(idEvento)
                .orElseThrow(() -> new RuntimeException("No se encontro una factura para el evento con ID: " + idEvento));
        return facturaMapper.toDto(factura);
    }

    @Override
    public FacturaResponseDTO saveFactura(FacturaRequestDTO requestDTO) {
        Evento evento = eventoService.buscarEventoById(requestDTO.getIdEvento());
        if(facturaRepository.existsByEventoIdEvento(requestDTO.getIdEvento())) {
            throw new RuntimeException("Ya existe una factura para el evento con ID: " + requestDTO.getIdEvento());
        }

        Factura factura = facturaMapper.toEntity(requestDTO, evento);
        Factura facturaGuardada = facturaRepository.save(factura);
        return facturaMapper.toDto(facturaGuardada);

    }

}



