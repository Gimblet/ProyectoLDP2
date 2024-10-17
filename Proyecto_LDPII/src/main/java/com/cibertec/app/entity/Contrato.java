package com.cibertec.app.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "") //TODO: cambiar nombre de tabla
public class Contrato implements Serializable {

	private static final long serialVersionUID = 1L;

}
