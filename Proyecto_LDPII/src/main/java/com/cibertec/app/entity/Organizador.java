package com.cibertec.app.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tbO") // TODO: cambiar nombre de tabla
public class Organizador implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private int idOrganizador;
}
