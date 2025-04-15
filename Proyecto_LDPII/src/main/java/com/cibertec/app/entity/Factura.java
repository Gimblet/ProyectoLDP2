package com.cibertec.app.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Getter
@Setter
@DynamicInsert
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "factura")
public class Factura implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idFactura", nullable = false)
	private Long id;

	@OneToOne
	@JoinColumn(name = "idEvento", unique = true)
	private Evento idEvento;

	@Column(name = "descuento")
	private Double descuento;

	@ColumnDefault("(now())")
	@Column(name = "fecha")
	private LocalDate fecha;

	@Column(name = "precioFinal", precision = 10, scale = 2)
	private BigDecimal precioFinal;

}
