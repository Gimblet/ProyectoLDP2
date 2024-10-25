package com.cibertec.app.repository;

import com.cibertec.app.entity.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Integer> {
	@Query(value = "Select f.idFactura, f.descuento, f.precioFinal, f.fecha, f.idEvento from factura as f JOIN evento as e ON e.idEvento = f.idEvento JOIN cliente as c ON e.idCliente = c.idCliente\r\n"
			+ "	WHERE e.idCliente = :idCliente", nativeQuery = true)
	public List<Factura> obtenerFacturaByCliente(@Param("idCliente") Integer idCliente);
}

