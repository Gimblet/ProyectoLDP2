package com.cibertec.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cibertec.app.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    @Query(value = "SELECT * FROM cliente AS c WHERE c.correoCliente = :correo AND c.claveCliente = :clave", nativeQuery = true)
    public Cliente findByEmailAndPassword(@Param("correo") String correo, @Param("clave") String clave);

    @Query(value = "SELECT * FROM cliente c WHERE c.correoCliente = :correo", nativeQuery = true)
    public Cliente findByEmail(@Param("correo") String correo);

    @Query(value = "SELECT * FROM cliente AS c WHERE c.telefonoCliente = :telefono", nativeQuery = true)
    public Cliente findByTelefono(@Param("telefono") String telefono);

}
