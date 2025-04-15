package com.cibertec.app.entity;

import java.io.Serializable;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "cliente")
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idCliente")
	private Long idCliente;

	@Column(name = "nombreCliente", nullable = false)
	private String nombre;

	@Column(name = "correoCliente", nullable = false)
	private String correo;

	@Column(name = "claveCliente", nullable = false, length = 100)
	private String clave;

	@Column(name = "telefonoCliente", nullable = false)
	private String telefono;

	@Column(name = "direccion", nullable = false)
	private String direccion;

	public Cliente(String nombre, String correo, String clave, String telefono, String direccion) {
		this.nombre = nombre;
		this.correo = correo;
		this.clave = clave;
		this.telefono = telefono;
		this.direccion = direccion;
	}
}
