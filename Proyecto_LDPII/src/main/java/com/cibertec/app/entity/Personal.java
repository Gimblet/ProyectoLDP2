package com.cibertec.app.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "personal")
public class Personal implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPersonal", nullable = false)
    private Integer id;

    @Column(name = "nombrePersonal", length = 100)
    private String nombre;

    @Column(name = "montoPersonal", precision = 8, scale = 2)
    private BigDecimal monto;

    @Column(name = "rolPersonal", length = 100)
    private String rol;

    public Personal() {
    }
}
