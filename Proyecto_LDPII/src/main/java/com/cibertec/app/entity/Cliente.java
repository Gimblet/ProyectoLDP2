package com.cibertec.app.entity;

import java.io.Serializable;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCliente;

	@Column(name = "nombreCliente")
	@Basic(optional = false)
	private String nombre;

	@Column(name = "correoCliente")
	@Basic(optional = false)
	private String correo;

	@Column(name = "telefonoCLiente")
	@Basic(optional = false)
	private String telefono;

	@Column(name = "direccion")
	@Basic(optional = false)
	private String direccion;

	@Column(name = "claveCliente", nullable = false, length = 100)
	private String clave;

	public Cliente(String nombre, String correo, String telefono, String direccion, String clave) {
		this.nombre = nombre;
		this.correo = correo;
		this.telefono = telefono;
		this.direccion = direccion;
		this.clave = clave;
	}

	public Cliente() {

	}
}
