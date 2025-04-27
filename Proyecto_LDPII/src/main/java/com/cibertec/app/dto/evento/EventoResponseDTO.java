package com.cibertec.app.dto.evento;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class EventoResponseDTO {
    private Long idEvento;
    private String nombreEvento;
    private LocalDateTime fecha;
    private int duracion;
    private BigDecimal monto;
    private String nombrePersonal;
    private String rolPersonal;
    private BigDecimal montoPersonal;
    private String nombreEstablecimiento;
    private String direccionEstablecimiento;
    private BigDecimal precioEstablecimiento;
    private String nombreCliente;
    private String correo;
    private String telefono;
    private String direccionCliente;
}
