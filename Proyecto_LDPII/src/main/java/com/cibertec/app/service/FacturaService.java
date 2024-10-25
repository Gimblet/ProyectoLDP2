package com.cibertec.app.service;

import com.cibertec.app.entity.Factura;
import java.util.List;

public interface FacturaService {
	public List<Factura> obtenerFacturaByCliente(Integer idCliente);
    public List<Factura> getFacturaByEvento(Integer idEvento);
    public void guardarFactura(Factura factura);
}
