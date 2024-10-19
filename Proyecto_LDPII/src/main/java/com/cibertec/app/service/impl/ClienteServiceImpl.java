package com.cibertec.app.service.impl;

import com.cibertec.app.entity.Cliente;
import com.cibertec.app.repository.ClienteRepository;
import com.cibertec.app.service.ClienteService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {
    ClienteRepository clienteRepository;

    @Override
    public List<Cliente> getAllCliente() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente getClienteById(Long id) {
        return null;
    }

    @Override
    public Cliente saveCliente(Cliente cliente) {
        Cliente usuario = new Cliente(cliente.getNombre(), cliente.getCorreo(), cliente.getTelefono(), cliente.getTelefono(), cliente.getClave());
        return clienteRepository.save(cliente);
    }

    @Override
    public boolean login(Cliente cliente) {
        boolean ingresado = false;
        Cliente entidad = clienteRepository.findByEmailAndPassword(cliente.getCorreo(), cliente.getClave());

        if(entidad != null){
            ingresado = true;
        }

        return ingresado;
    }
}
