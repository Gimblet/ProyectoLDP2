package com.cibertec.app.service;

import java.util.List;

import com.cibertec.app.entity.Cliente;

public interface ClienteService {
	public List<Cliente> getAllCliente();

	public Cliente getClienteById(Integer id);

	public Cliente getClienteByEmail(String email);

	public Cliente getClienteByTelefono(String telefono);

	public Cliente saveCliente(Cliente cliente);

	public boolean login(Cliente cliente);
}
