package com.cibertec.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cibertec.app.entity.Local;

@Repository
public interface LocalRepository extends JpaRepository<Local, Integer> {

}
