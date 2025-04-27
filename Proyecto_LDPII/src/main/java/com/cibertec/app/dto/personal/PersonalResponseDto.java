package com.cibertec.app.dto.personal;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class PersonalResponseDto {

    private Long id;

    private String nombre;

    private BigDecimal monto;

    private String rol;
}
