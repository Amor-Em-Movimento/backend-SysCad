package aem_ong_dev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aem_ong_dev.model.Evento;
import aem_ong_dev.service.EventoService;

@RestController
@RequestMapping("/eventos")
public class EventoController {
    @Autowired
    private EventoService eventoService;

    // Endpoint 1 - [POST] Criar Evento
    @PostMapping
    public Evento criar(@RequestBody Evento evento) {
        return eventoService.criarEvento(evento);
    }

    // Endpoint 2 - [GET] Listar Eventos por Ano
    @GetMapping("/ano/{ano}")
    public List<Evento> listarPorAno(@PathVariable int ano) {
        return eventoService.listarPorAno(ano);
    }
}
