package com.cibertec.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cibertec.app.entity.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Integer> {

    @Query(value = "SELECT * FROM evento e WHERE e.idCliente = :idcliente", nativeQuery = true)
    public Evento findEventosByCliente(@Param("idcliente") Integer idCliente);
}
