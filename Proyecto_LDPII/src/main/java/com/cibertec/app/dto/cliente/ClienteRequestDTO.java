package com.cibertec.app.dto.cliente;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClienteRequestDTO {
    private String nombre;
    private String correo;
    private String clave;
    private String telefono;
    private String direccion;
}
