package com.cibertec.app.service.impl;

import com.cibertec.app.dto.establecimiento.EstablecimientoRequestDTO;
import com.cibertec.app.dto.establecimiento.EstablecimientoResponseDTO;
import com.cibertec.app.entity.Establecimiento;
import com.cibertec.app.repository.EstablecimientoRepository;
import com.cibertec.app.service.EstablecimientoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstablecimientoServiceImpl implements EstablecimientoService {


    @Override
    public List<EstablecimientoResponseDTO> listar() {
        return List.of();
    }

    @Override
    public EstablecimientoResponseDTO guardar(EstablecimientoRequestDTO requestDTO) {
        return null;
    }

    @Override
    public EstablecimientoResponseDTO obtenerPorId(Long id) {
        return null;
    }

    @Override
    public void eliminar(Long id) {

    }

    @Override
    public Establecimiento obtenerEntidadPorId(Long id) {
        return null;
    }
}



