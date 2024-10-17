package com.cibertec.app.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "") //TODO: Cambiar nombre
public class Factura implements Serializable {

	private static final long serialVersionUID = 1L;

}
