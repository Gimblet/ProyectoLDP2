package com.cibertec.app.service.impl;

import com.cibertec.app.dto.personal.PersonalRequestDto;
import com.cibertec.app.dto.personal.PersonalResponseDto;
import com.cibertec.app.entity.Personal;
import com.cibertec.app.mapper.personal.PersonalMapper;
import com.cibertec.app.repository.PersonalRepository;
import com.cibertec.app.service.PersonalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonalServiceImpl implements PersonalService {

    private final PersonalRepository personalRepository;


    @Override
    public List<Personal> obtenerListaPersonal() {
        return personalRepository.findAll();
    }

    @Override
    public PersonalResponseDto crearPersonal(PersonalRequestDto dto) {
        Personal entity = PersonalMapper.toEntity(dto);
        Personal saved = personalRepository.save(entity);
        return PersonalMapper.toDto(saved);
    }

    @Override
    public PersonalResponseDto actualizarPersonal(Long id, PersonalRequestDto dto) {
        Optional<Personal> optional = personalRepository.findById(id);
        if (optional.isPresent()) {
            Personal entity = optional.get();
            entity.setNombre(dto.getNombre());
            entity.setMonto(dto.getMonto());
            entity.setRol(dto.getRol());
            Personal updated = personalRepository.save(entity);
            return PersonalMapper.toDto(updated);
        }
        throw new RuntimeException("Personal no encontrado con ID: " + id);
    }

    @Override
    public void eliminarPersonal(Long id) {
        personalRepository.deleteById(id);
    }

    @Override
    public PersonalResponseDto obtenerPersonalPorId(Long id) {
        return personalRepository.findById(id)
                .map(PersonalMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Personal no encontrado con ID: " + id));
    }
}
