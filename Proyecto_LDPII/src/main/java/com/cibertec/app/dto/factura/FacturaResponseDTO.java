package com.cibertec.app.dto.factura;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class FacturaResponseDTO {
    private Long id;
    private Long idEvento;
    private String nombreEvento;
    private Double descuento;
    private LocalDate fecha;
    private BigDecimal precioFinal;
}
