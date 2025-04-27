package com.cibertec.app.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "personal")
public class Personal implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPersonal")
    private Long id;

    @Column(name = "nombrePersonal", length = 100, nullable = false)
    private String nombre;

    @Column(name = "montoPersonal", precision = 10, scale = 2, nullable = false)
    private BigDecimal monto;

    @Column(name = "rolPersonal", length = 100, nullable = false)
    private String rol;

}
