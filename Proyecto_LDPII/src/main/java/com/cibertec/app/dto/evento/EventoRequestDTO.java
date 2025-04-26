package com.cibertec.app.dto.evento;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class EventoRequestDTO {
    private String nombre;
    private LocalDateTime fecha;
    private int duracion;
    private BigDecimal monto;
    private Long idPersonal;
    private Long idEstablecimiento;
    private Long idCliente;
}
