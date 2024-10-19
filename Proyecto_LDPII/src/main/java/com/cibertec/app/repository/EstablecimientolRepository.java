package com.cibertec.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cibertec.app.entity.Establecimiento;

@Repository
public interface EstablecimientolRepository extends JpaRepository<Establecimiento, Integer> {

}
