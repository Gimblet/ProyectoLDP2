package com.cibertec.app.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

@Getter
@Setter
@Entity
@DynamicInsert
@Table(name = "factura")
public class Factura implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idFactura", nullable = false)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idEvento")
	private Evento idEvento;

	@Column(name = "descuento")
	private Double descuento;

	@ColumnDefault("(now())")
	@Column(name = "fecha")
	private LocalDate fecha;

	@Column(name = "precioFinal", precision = 8, scale = 2)
	private BigDecimal precioFinal;

	public Factura() {
	}
}
