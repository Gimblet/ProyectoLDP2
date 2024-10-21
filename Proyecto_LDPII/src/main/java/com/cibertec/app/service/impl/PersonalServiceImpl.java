package com.cibertec.app.service.impl;

import com.cibertec.app.entity.Personal;
import com.cibertec.app.repository.PersonalRepository;
import com.cibertec.app.service.PersonalService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonalServiceImpl implements PersonalService {

    PersonalRepository personalRepository;

    public PersonalServiceImpl(PersonalRepository personalRepository) {
        this.personalRepository = personalRepository;
    }

    @Override
    public List<Personal> obtenerListaPersonal() {
        return personalRepository.findAll();
    }
}
