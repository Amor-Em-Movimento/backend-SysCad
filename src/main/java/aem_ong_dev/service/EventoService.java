package aem_ong_dev.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aem_ong_dev.model.Evento;
import aem_ong_dev.model.User.StatusUser;
import aem_ong_dev.model.User.User;
import aem_ong_dev.repository.EventoRepository;

@Service
public class EventoService {
    @Autowired
    private EventoRepository eventoRepository;

    public Evento criarEvento(Evento evento) {
        return eventoRepository.save(evento);
    }

    public List<Evento> listarPorAno(int ano) {
        return eventoRepository.findByDataRealizacao(ano);
    }

    public Evento atualizarEvento(Long id, Evento evento) {
        return eventoRepository.findById(id).map(eventoEncontrado -> {
            eventoEncontrado.setNome(evento.getNome());
            eventoEncontrado.setDescricao(evento.getDescricao());
            eventoEncontrado.setDataRealizacao(evento.getDataRealizacao());
            eventoEncontrado.setEquipe(evento.getEquipe());
            eventoEncontrado.setAtendidos(evento.getAtendidos());
            eventoEncontrado.setItensUtilizados(evento.getItensUtilizados());
            return eventoRepository.save(eventoEncontrado);
        })
        .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public void deletar(Long id) {
        Evento evento = eventoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Evento não encontrado"));

        eventoRepository.deleteById(id);
    }

    public List<Evento> listarTodos(){
        return eventoRepository.findAll();
    }
}
