package com.cibertec.app.mapper;

import com.cibertec.app.dto.ClienteRequestDTO;
import com.cibertec.app.dto.ClienteResponseDTO;
import com.cibertec.app.entity.Cliente;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {
    public ClienteResponseDTO toDto(Cliente cliente){
        return ClienteResponseDTO.builder()
                .id(cliente.getId())
                .nombre(cliente.getNombre())
                .correo(cliente.getCorreo())
                .clave(cliente.getClave())
                .telefono(cliente.getTelefono())
                .direccion(cliente.getDireccion())
                .build();
    }
    
    public Cliente toEntity(ClienteRequestDTO clienteRequestDTO){
        return Cliente.builder()
                .nombre(clienteRequestDTO.getNombre())
                .correo(clienteRequestDTO.getCorreo())
                .clave(clienteRequestDTO.getClave())
                .telefono(clienteRequestDTO.getTelefono())
                .direccion(clienteRequestDTO.getDireccion())
                .build();
    }
}
