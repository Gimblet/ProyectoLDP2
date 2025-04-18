package com.cibertec.app.dto.cliente;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ClienteResponseDTO {
    private Long id;
    private String nombre;
    private String correo;
    private String clave;
    private String telefono;
    private String direccion;
}
