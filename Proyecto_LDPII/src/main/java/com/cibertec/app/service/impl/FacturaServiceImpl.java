package com.cibertec.app.service.impl;

import com.cibertec.app.entity.Factura;
import com.cibertec.app.repository.FacturaRepository;
import com.cibertec.app.service.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FacturaServiceImpl implements FacturaService {

    @Autowired
    FacturaRepository facturaRepository;

    @Override
    public List<Factura> getFacturaByEvento(Integer idEvento) {
        return null;
    }

    @Override
    public void guardarFactura(Factura factura) {
        facturaRepository.save(factura);
    }

    @Override
    public List<Factura> getAllFactura() {
        return List.of();
    }

    @Override
    public Factura obtenerFacturaID(Integer id) {
        return facturaRepository.findById(id).get();
    }

    @Override
    public List<Factura> obtenerFacturaByCliente(Integer idCliente) {
        return facturaRepository.obtenerFacturaByCliente(idCliente);
    }}
