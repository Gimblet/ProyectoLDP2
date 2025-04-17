package com.cibertec.app.mapper.factura;

import com.cibertec.app.dto.factura.FacturaRequestDTO;
import com.cibertec.app.dto.factura.FacturaResponseDTO;
import com.cibertec.app.entity.Evento;
import com.cibertec.app.entity.Factura;
import org.springframework.stereotype.Component;

@Component
public class FacturaMapper {
    public FacturaResponseDTO toDto(Factura factura) {
        return FacturaResponseDTO.builder()
                .id(factura.getId())
                .idEvento(factura.getEvento().getIdEvento())
                .nombreEvento(factura.getEvento().getNombre())
                .descuento(factura.getDescuento())
                .fecha(factura.getFecha())
                .precioFinal(factura.getPrecioFinal())
                .build();
    }

    public Factura toEntity(FacturaRequestDTO requestDTO, Evento evento) {
        return Factura.builder()
                .evento(evento)
                .descuento(requestDTO.getDescuento())
                .fecha(requestDTO.getFecha())
                .precioFinal(requestDTO.getPrecioFinal())
                .build();
    }
}
