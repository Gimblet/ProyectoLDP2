package com.cibertec.app.service.impl;

import com.cibertec.app.dto.establecimiento.EstablecimientoRequestDTO;
import com.cibertec.app.dto.establecimiento.EstablecimientoResponseDTO;
import com.cibertec.app.entity.Establecimiento;
import com.cibertec.app.mapper.establecimiento.EstablecimientoMapper;
import com.cibertec.app.repository.EstablecimientoRepository;
import com.cibertec.app.service.EstablecimientoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstablecimientoServiceImpl implements EstablecimientoService {
    private final EstablecimientoRepository establecimientoRepository;
    private final EstablecimientoMapper establecimientoMapper;


    @Override
    public List<EstablecimientoResponseDTO> listar() {
        return establecimientoRepository.findAll().stream()
                .map(establecimientoMapper::toDto)
                .toList();
    }

    @Override
    public EstablecimientoResponseDTO guardar(EstablecimientoRequestDTO requestDTO) {
        Establecimiento establecimiento = establecimientoMapper.toEntity(requestDTO);
        Establecimiento establecimientoGuardado = establecimientoRepository.save(establecimiento);
        return establecimientoMapper.toDto(establecimientoGuardado);
    }

    @Override
    public EstablecimientoResponseDTO obtenerPorId(Long id) {
        return establecimientoRepository.findById(id)
                .map(establecimientoMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Establecimiento no encontrado"));
    }

    @Override
    public void eliminar(Long id) {
        if (!establecimientoRepository.existsById(id)){
            throw new RuntimeException("No existe establecimiento con ID : " + id);
        }
        establecimientoRepository.deleteById(id);
    }

    //

   // @Override
   // public Establecimiento obtenerEntidadPorId(Long id) {
       // return establecimientoRepository.findById(id)
                //.orElseThrow(() -> new RuntimeException("Establecimiento no encontrado"));
   // }

}



