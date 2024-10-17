package com.cibertec.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cibertec.app.entity.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Integer> {

}