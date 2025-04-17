package com.cibertec.app.service;

import com.cibertec.app.entity.Factura;
import java.util.List;

public interface FacturaService {
    public List<Factura> getAllFactura();

    public Factura obtenerFacturaID(Long id);

    public List<Factura> obtenerFacturaByCliente(Long idCliente);

    public List<Factura> getFacturaByEvento(Long idEvento);

    public void guardarFactura(Factura factura);
}
