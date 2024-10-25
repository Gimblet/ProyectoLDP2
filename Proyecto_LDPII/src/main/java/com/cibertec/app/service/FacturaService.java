package com.cibertec.app.service;

import com.cibertec.app.entity.Factura;
import java.util.List;

public interface FacturaService {
    public List<Factura> getAllFactura();

    public String agregarFactura(Factura factura);

    public Factura obtenerFacturaID(Integer id);
}
