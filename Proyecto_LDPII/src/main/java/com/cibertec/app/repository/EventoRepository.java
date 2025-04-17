package com.cibertec.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cibertec.app.entity.Evento;

import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, Long> {
    @Query(value = "SELECT e.idEvento, e.nombreEvento, e.fechaEvento, e.horasEvento, e.montoEvento, e.idPersonal, e.idEstablecimiento, e.idCliente FROM evento e WHERE e.idCliente = :idcliente", nativeQuery = true)
    public List<Evento> findEventosByCliente(@Param("idcliente") Long idCliente);
}
