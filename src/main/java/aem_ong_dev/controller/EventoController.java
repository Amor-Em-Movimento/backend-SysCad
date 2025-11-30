package aem_ong_dev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aem_ong_dev.model.Evento;
import aem_ong_dev.model.User.User;
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

    // Endpoint 3 - [PUT] Atualizar Evento
    @PostMapping("/{id}")
    public Evento atualizar(@PathVariable Long id, @RequestBody Evento evento) {
        return eventoService.atualizarEvento(id, evento);
    }

    // Endpoint 4 - [DELETE] Remover usuário
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        eventoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoint 5 - [GET] Listar todos Eventos
    @GetMapping
    public ResponseEntity<List<Evento>> listar() {
        return ResponseEntity.ok(eventoService.listarTodos());
    }
}
