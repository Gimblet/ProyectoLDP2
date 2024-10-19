package com.cibertec.app.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Establecimiento")
public class Establecimiento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idEstablecimiento", nullable = false)
	private Integer id;

	@Column(name = "Nombre_establecimiento", length = 50)
	private String nombre;

	@Column(name = "Direccion", nullable = false, length = 100)
	private String direccion;

	@Column(name = "Precio_alquiler", precision = 10)
	private BigDecimal precio;

	public Establecimiento() {
	}
}
