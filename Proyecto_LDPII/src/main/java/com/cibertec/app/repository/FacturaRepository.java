package com.cibertec.app.repository;

import com.cibertec.app.entity.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FacturaRepository extends JpaRepository<Factura, Long> {
    @Query(value = "Select f.idFactura, f.descuento, f.precioFinal, f.fecha, f.idEvento from factura as f JOIN evento as e ON e.idEvento = f.idEvento JOIN cliente as c ON e.idCliente = c.idCliente\r\n"
            + "	WHERE e.idCliente = :idCliente", nativeQuery = true)
    public List<Factura> obtenerFacturaByCliente(@Param("idCliente") Long idCliente);

    Optional<Factura> findByEventoIdEvento(Long idEvento);
    boolean existsByEventoIdEvento(Long idEvento);
}

