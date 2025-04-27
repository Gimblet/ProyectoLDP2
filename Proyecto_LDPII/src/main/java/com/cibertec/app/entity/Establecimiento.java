package com.cibertec.app.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Establecimiento")
public class Establecimiento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idEstablecimiento")
	private Long id;

	@Column(name = "Nombre_establecimiento", length = 50)
	private String nombre;

	@Column(name = "Direccion", nullable = false, length = 100)
	private String direccion;

	@Column(name = "Precio_alquiler", precision = 10, scale = 2)
	private BigDecimal precio;

	@OneToMany(mappedBy = "establecimiento", fetch = FetchType.LAZY)
	private List<Evento> eventos = new ArrayList<>();
}
