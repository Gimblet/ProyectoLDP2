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
@Table(name = "tbC") // TODO : Cambiar nombre
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer idCliente;

}
