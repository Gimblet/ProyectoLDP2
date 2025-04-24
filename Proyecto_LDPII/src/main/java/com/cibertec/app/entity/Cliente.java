package com.cibertec.app.entity;

import java.io.Serial;
import java.io.Serializable;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idCliente")
	private Long id;

	@Column(name = "nombreCliente", nullable = false, length = 100)
	private String nombre;

	@Column(name = "correoCliente", nullable = false, length = 200, unique = true)
	private String correo;

	@Column(name = "claveCliente", nullable = false, length = 100)
	private String clave;

	@Column(name = "telefonoCliente", nullable = false, length = 9, unique = true)
	private String telefono;

	@Column(name = "direccion", nullable = false, length = 150)
	private String direccion;
}
