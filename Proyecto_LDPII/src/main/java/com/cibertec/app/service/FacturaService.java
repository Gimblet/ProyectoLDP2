package com.cibertec.app.service;

import com.cibertec.app.dto.factura.FacturaRequestDTO;
import com.cibertec.app.dto.factura.FacturaResponseDTO;
import com.cibertec.app.entity.Factura;
import java.util.List;

public interface FacturaService {
    List<FacturaResponseDTO> getAllFacturas();

    FacturaResponseDTO getFacturaById(Long id);

    List<FacturaResponseDTO> getFacturaByCliente(Long idCliente);

    FacturaResponseDTO getFacturaByEvento(Long idEvento);

    FacturaResponseDTO saveFactura(FacturaRequestDTO requestDTO);
}

