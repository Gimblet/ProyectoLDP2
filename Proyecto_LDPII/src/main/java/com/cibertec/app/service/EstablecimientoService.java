package com.cibertec.app.service;

import com.cibertec.app.dto.establecimiento.EstablecimientoRequestDTO;
import com.cibertec.app.dto.establecimiento.EstablecimientoResponseDTO;
import com.cibertec.app.entity.Establecimiento;

import java.util.List;

public interface EstablecimientoService {

    List<EstablecimientoResponseDTO> listar();

    EstablecimientoResponseDTO guardar(EstablecimientoRequestDTO requestDTO);

    EstablecimientoResponseDTO obtenerPorId(Long id);

    void eliminar(Long id);
    //
    // Establecimiento obtenerEntidadPorId(Long id);

}
