package com.cibertec.app.mapper.evento;

import com.cibertec.app.dto.evento.EventoRequestDTO;
import com.cibertec.app.dto.evento.EventoResponseDTO;
import com.cibertec.app.entity.Cliente;
import com.cibertec.app.entity.Establecimiento;
import com.cibertec.app.entity.Evento;
import com.cibertec.app.entity.Personal;
import org.springframework.stereotype.Component;

@Component
public class EventoMapper {
    public EventoResponseDTO toDto(Evento evento){
        return EventoResponseDTO.builder()
                .idEvento(evento.getIdEvento())
                .nombreEvento(evento.getNombre())
                .fecha(evento.getFecha())
                .duracion(evento.getDuracion())
                .monto(evento.getMonto())
                .nombrePersonal(evento.getPersonal().getNombre())
                .rolPersonal(evento.getPersonal().getRol())
                .montoPersonal(evento.getPersonal().getMonto())
                .nombreEstablecimiento(evento.getEstablecimiento().getNombre())
                .direccionEstablecimiento(evento.getEstablecimiento().getDireccion())
                .precioEstablecimiento(evento.getEstablecimiento().getPrecio())
                .nombreCliente(evento.getCliente().getNombre())
                .correo(evento.getCliente().getCorreo())
                .telefono(evento.getCliente().getTelefono())
                .direccionCliente(evento.getCliente().getDireccion())
                .build();
    }
    public Evento toEntity(EventoRequestDTO requestDTO, Personal personal,
                           Establecimiento establecimiento, Cliente cliente){
        return Evento.builder()
                .nombre(requestDTO.getNombre())
                .fecha(requestDTO.getFecha())
                .duracion(requestDTO.getDuracion())
                .monto(requestDTO.getMonto())
                .personal(personal)
                .establecimiento(establecimiento)
                .cliente(cliente)
                .build();
    }
}
