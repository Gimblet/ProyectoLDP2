package com.cibertec.app.service;

import com.cibertec.app.dto.personal.PersonalRequestDto;
import com.cibertec.app.dto.personal.PersonalResponseDto;
import com.cibertec.app.entity.Personal;

import java.util.List;

public interface PersonalService {

    List<Personal> obtenerListaPersonal();


    PersonalResponseDto crearPersonal(PersonalRequestDto dto);

    PersonalResponseDto actualizarPersonal(Long id, PersonalRequestDto dto);

    void eliminarPersonal(Long id);

    PersonalResponseDto obtenerPersonalPorId(Long id);
}
