package com.cibertec.app.service;

import java.util.List;

import com.cibertec.app.dto.cliente.ClienteRequestDTO;
import com.cibertec.app.dto.cliente.ClienteResponseDTO;
import com.cibertec.app.entity.Cliente;
import jakarta.servlet.http.HttpServletRequest;

public interface ClienteService {
	public List<ClienteResponseDTO> getAllCliente();

	public Cliente getClienteById(Long id);

	public Cliente getClienteByEmail(String email);

	public Cliente getClienteByTelefono(String telefono);

	public ClienteResponseDTO saveCliente(ClienteRequestDTO requestDTO);

	public boolean login(Cliente cliente, HttpServletRequest request);
}
