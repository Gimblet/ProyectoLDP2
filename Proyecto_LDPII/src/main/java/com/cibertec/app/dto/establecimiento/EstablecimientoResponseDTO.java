package com.cibertec.app.dto.establecimiento;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class EstablecimientoResponseDTO {

    private  Long id;
    private String nombre;
    private String direccion;
    private BigDecimal precio;
}
