package com.cibertec.app.dto.establecimiento;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class EstablecimientoRequestDTO {

    private String nombre;
    private String direccion;
    private BigDecimal precio;
}
