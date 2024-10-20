package com.cibertec.app.service.impl;

import com.cibertec.app.entity.Cliente;
import com.cibertec.app.repository.ClienteRepository;
import com.cibertec.app.service.ClienteService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {
    ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository){
        super();
        this.clienteRepository = clienteRepository;
    }

    @Override
    public List<Cliente> getAllCliente() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente getClienteById(Integer id) {
        return clienteRepository.findById(id).get();
    }

    @Override
    public Cliente getClienteByEmail(String email) {
        return clienteRepository.findByEmail(email);
    }

    @Override
    public Cliente getClienteByTelefono(String telefono) {
        return clienteRepository.findByTelefono(telefono);
    }

    @Override
    public Cliente saveCliente(Cliente cliente) {
        Cliente usuario = new Cliente(cliente.getNombre(), cliente.getCorreo(), cliente.getClave(), cliente.getTelefono(), cliente.getDireccion());
        return clienteRepository.save(usuario);
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
