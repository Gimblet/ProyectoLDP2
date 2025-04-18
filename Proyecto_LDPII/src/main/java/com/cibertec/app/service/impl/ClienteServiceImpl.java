package com.cibertec.app.service.impl;

import com.cibertec.app.dto.cliente.ClienteRequestDTO;
import com.cibertec.app.dto.cliente.ClienteResponseDTO;
import com.cibertec.app.entity.Cliente;
import com.cibertec.app.mapper.ClienteMapper;
import com.cibertec.app.repository.ClienteRepository;
import com.cibertec.app.service.ClienteService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {
    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    @Override
    public List<ClienteResponseDTO> getAllCliente() {
        return clienteRepository.findAll()
                .stream()
                .map(clienteMapper::toDto)
                .toList();
    }

    @Override
    public Cliente getClienteById(Long id) {
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
    public ClienteResponseDTO saveCliente(ClienteRequestDTO requestDTO) {
        Cliente cliente = clienteMapper.toEntity(requestDTO);
        return clienteMapper.toDto(clienteRepository.save(cliente));
    }

    @Override
    public boolean login(Cliente cliente, HttpServletRequest request) {
        boolean ingresado = false;
        Cliente entidad = clienteRepository.findByEmailAndPassword(cliente.getCorreo(), cliente.getClave());

        if(entidad != null){
            ingresado = true;
            request.getSession().setAttribute("id", entidad.getId());
        }

        System.out.println(request.getSession().getAttribute("id"));

        return ingresado;
    }
}
