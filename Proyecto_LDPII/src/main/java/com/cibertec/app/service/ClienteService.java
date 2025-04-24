package com.cibertec.app.service;

import java.util.List;

import com.cibertec.app.dto.cliente.ClienteRequestDTO;
import com.cibertec.app.dto.cliente.ClienteResponseDTO;
import com.cibertec.app.entity.Cliente;
import jakarta.servlet.http.HttpServletRequest;

public interface ClienteService {
	List<ClienteResponseDTO> getAllCliente();

	Cliente getClienteById(Long id);
	
	Cliente getClienteByEmail(String email);
	
	Boolean existsClienteByEmail(String email);

	Cliente getClienteByTelefono(String telefono);
	
	Boolean existsClienteByTelefono(String telefono);

	ClienteResponseDTO saveCliente(ClienteRequestDTO requestDTO);

	boolean login(Cliente cliente, HttpServletRequest request);
}
