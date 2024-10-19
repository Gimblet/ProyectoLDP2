package com.cibertec.app.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "evento")
public class Evento implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idEvento;

	@Basic(optional = false)
	@Column(name = "nombreEvento")
	private String nombre;

	@Temporal(TemporalType.DATE)
	@Basic(optional = false)
	@Column(name = "fechaEvento")
	private Date fecha;

	@Basic(optional = false)
	@Column(name = "horasEvento")
	private int duracion;

	@Basic(optional = false)
	@Column(name = "montoEvento")
	private BigDecimal monto;

	@ManyToOne
	@JoinColumn(name = "idPersonal")
	private Personal personal;

	@ManyToOne
	@JoinColumn(name = "idEstablecimiento")
	private Establecimiento establecimiento;

	public Evento() {
	}
}
