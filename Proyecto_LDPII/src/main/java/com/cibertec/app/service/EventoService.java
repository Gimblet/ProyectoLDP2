package com.cibertec.app.service;

import com.cibertec.app.entity.Evento;

import java.util.List;

public interface EventoService {
    public List<Evento> getAllEvento();

    public List<Evento> getEventoByCliente(Integer idCliente);

    public Evento crearEvento(Evento evento);

    public String deleteEvento(Integer idEvento);

}
