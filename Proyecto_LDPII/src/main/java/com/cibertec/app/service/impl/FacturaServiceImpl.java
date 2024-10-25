package com.cibertec.app.service.impl;

import com.cibertec.app.entity.Factura;
import com.cibertec.app.repository.FacturaRepository;
import com.cibertec.app.service.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacturaServiceImpl implements FacturaService {

    FacturaRepository repo;

    public FacturaServiceImpl(FacturaRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Factura> getAllFactura() {
        return repo.findAll();
    }

    @Override
    public String agregarFactura(Factura factura) {
        try{
            repo.save(factura);
            return "Factura Agregada con Exito";
        } catch(Exception e){
            e.printStackTrace();
            return "Error";
        }
    }

    @Override
    public Factura obtenerFacturaID(Integer id) {
        return repo.findById(id).get();
    }
}
