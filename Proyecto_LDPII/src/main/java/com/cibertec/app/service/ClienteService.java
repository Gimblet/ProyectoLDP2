package com.cibertec.app.service;

import java.util.List;

import com.cibertec.app.dto.cliente.ClienteRequestDTO;
import com.cibertec.app.dto.cliente.ClienteResponseDTO;
import com.cibertec.app.entity.Cliente;

public interface ClienteService {
	//Metodos de Lista
	List<ClienteResponseDTO> getAllCliente();

	//Metodos de Persistencia
	ClienteResponseDTO saveCliente(ClienteRequestDTO requestDTO);

	//Metodos de Obtención
	Cliente getClienteById(Long id);
	Cliente getClienteByEmail(String email);
	Cliente getClienteByTelefono(String telefono);
	
	//Metodos de Eliminacion
	void deleteClienteById(Long id);

	//Metodos Utilitarios
	Boolean existsClienteByEmail(String email);
	Boolean existsClienteByTelefono(String telefono);
	
//	boolean login(Cliente cliente, HttpServletRequest request);
}
