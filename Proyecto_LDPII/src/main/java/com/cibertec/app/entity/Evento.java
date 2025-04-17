package com.cibertec.app.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "evento")
public class Evento implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idEvento;

	@Basic(optional = false)
	@Column(name = "nombreEvento")
	private String nombre;

	@Transient
	private String fechaString;

	@Column(name = "fechaEvento", nullable = false)
	@DateTimeFormat(pattern = "MM-dd-yyyy")
	private LocalDateTime fecha;

	@Column(name = "horasEvento", nullable = false)
	private int duracion;

	@Column(name = "montoEvento", nullable = false, precision = 10, scale = 2)
	private BigDecimal monto;

	@ManyToOne
	@JoinColumn(name = "idPersonal")
	private Personal personal;

	@ManyToOne
	@JoinColumn(name = "idEstablecimiento")
	private Establecimiento establecimiento;

	@ManyToOne
	@JoinColumn(name = "idCliente")
	private Cliente cliente;

}
