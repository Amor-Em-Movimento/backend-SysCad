package aem_ong_dev.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aem_ong_dev.model.Familia;
import aem_ong_dev.model.User;
import aem_ong_dev.repository.FamiliaRepository;
import aem_ong_dev.repository.UserRepository;

@Service
public class FamiliaService {
    @Autowired
    private FamiliaRepository familiaRepository;
    @Autowired
    private UserRepository usuarioRepository;

    public List<Familia> listarTodas() {
        return familiaRepository.findAll();
    }

    public Optional<Familia> buscarPorId(Long id) {
        return familiaRepository.findById(id);
    }

    public Familia salvar(Familia familia) {
        if (familia.getCpf() != null && familiaRepository.findByCpf(familia.getCpf()).isPresent()) {
            throw new RuntimeException("CPF já cadastrado no sistema!");
        }
        if (familia.getCadastradoPor() != null && familia.getCadastradoPor().getId() != null) {
            User voluntario = usuarioRepository.findById(familia.getCadastradoPor().getId())
                    .orElseThrow(() -> new RuntimeException("Usuário voluntário não encontrado no banco de dados."));
            
            familia.setCadastradoPor(voluntario);
        }
        return familiaRepository.save(familia);
    }

    public Familia atualizar(Long id, Familia novosDados) {
        return familiaRepository.findById(id)
            .map(familiaExistente -> {
                familiaExistente.setNomeResponsavel(novosDados.getNomeResponsavel());
                familiaExistente.setCpf(novosDados.getCpf());
                familiaExistente.setEndereco(novosDados.getEndereco());
                familiaExistente.setTelefone(novosDados.getTelefone());
                familiaExistente.setDataNascimento(novosDados.getDataNascimento());
                
                return familiaRepository.save(familiaExistente);
            })
            .orElseThrow(() -> new RuntimeException("Família não encontrada com o ID: " + id));
    }

    public void deletar(Long id) {
        if (!familiaRepository.existsById(id)) {
            throw new RuntimeException("Família não encontrada para exclusão");
        }
        familiaRepository.deleteById(id);
    }
}
