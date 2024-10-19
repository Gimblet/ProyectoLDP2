package com.cibertec.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cibertec.app.entity.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    @Query(value = "SELECT * FROM cliente AS c WHERE c.correoCliente = :correo AND c.claveCliente = :clave", nativeQuery = true)
    public Cliente findByEmailAndPassword(@Param("correo") String correo, @Param("clave") String clave);
}
