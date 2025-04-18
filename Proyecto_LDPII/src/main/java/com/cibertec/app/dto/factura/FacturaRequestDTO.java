package com.cibertec.app.dto.factura;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class FacturaRequestDTO {
    private Long idEvento;
    private Double descuento;
    private LocalDate fecha;
    private BigDecimal precioFinal;
}
