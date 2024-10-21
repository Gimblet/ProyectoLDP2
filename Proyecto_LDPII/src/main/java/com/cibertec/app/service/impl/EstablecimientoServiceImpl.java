package com.cibertec.app.service.impl;

import com.cibertec.app.entity.Establecimiento;
import com.cibertec.app.repository.EstablecimientoRepository;
import com.cibertec.app.service.EstablecimientoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstablecimientoServiceImpl implements EstablecimientoService {

    EstablecimientoRepository establecimientoRepository;

    public EstablecimientoServiceImpl(EstablecimientoRepository establecimientoRepository) {
        this.establecimientoRepository = establecimientoRepository;
    }

    @Override
    public List<Establecimiento> getAllEstablecimiento() {
        return establecimientoRepository.findAll();
    }
}
