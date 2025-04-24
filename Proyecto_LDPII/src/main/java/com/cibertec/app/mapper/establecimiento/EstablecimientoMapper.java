package com.cibertec.app.mapper.establecimiento;


import com.cibertec.app.dto.establecimiento.EstablecimientoRequestDTO;
import com.cibertec.app.dto.establecimiento.EstablecimientoResponseDTO;
import com.cibertec.app.entity.Establecimiento;
import org.springframework.stereotype.Component;

@Component
public class EstablecimientoMapper {

    public EstablecimientoResponseDTO toDto(Establecimiento establecimiento){
        return EstablecimientoResponseDTO.builder()
                .id(establecimiento.getId())
                .nombre(establecimiento.getNombre())
                .direccion(establecimiento.getDireccion())
                .precio(establecimiento.getPrecio())
                .build();
    }
    public  Establecimiento toEntity(EstablecimientoRequestDTO requestDTO){
        return Establecimiento.builder()
                .nombre(requestDTO.getNombre())
                .direccion(requestDTO.getDireccion())
                .precio(requestDTO.getPrecio())
                .build();
    }
}


