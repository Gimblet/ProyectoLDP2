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
	@Column(name = "idCliente")
	private Integer idCliente;

	@Column(name = "nombreCliente")
	@Basic(optional = false)
	private String nombre;

	@Column(name = "correoCliente")
	@Basic(optional = false)
	private String correo;

	@Column(name = "claveCliente", nullable = false, length = 100)
	private String clave;

	@Column(name = "telefonoCliente")
	@Basic(optional = false)
	private String telefono;

	@Column(name = "direccion")
	@Basic(optional = false)
	private String direccion;

	public Cliente(String nombre, String correo, String clave, String telefono, String direccion) {
		this.nombre = nombre;
		this.correo = correo;
		this.clave = clave;
		this.telefono = telefono;
		this.direccion = direccion;
	}

	public Cliente() {

	}
}
