package com.cibertec.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cibertec.app.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    @Query(value = "FROM Cliente AS c WHERE c.correo = :correo AND c.clave = :clave")
    Cliente findByEmailAndPassword(@Param("correo") String correo, @Param("clave") String clave);

    @Query(value = "FROM Cliente c WHERE c.correo = :correo")
    Cliente findByEmail(@Param("correo") String correo);
    
    Boolean existsByCorreo(String correo);

    @Query(value = "FROM Cliente AS c WHERE c.telefono = :telefono")
    Cliente findByTelefono(@Param("telefono") String telefono);

    Boolean existsByTelefono(String telefono);

}
