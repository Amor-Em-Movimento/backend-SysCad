package aem_ong_dev.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aem_ong_dev.model.User;
import aem_ong_dev.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> listarTodos() {
        return userRepository.findAll();
    }

    public User salvar(User user) {
        if(userRepository.findByUsername (user.getUsername()).isPresent()){
            throw new RuntimeException("User já cadastrado!");
        }
        return userRepository.save(user);
    }

    public Optional<User> buscarPorId(Long id) {
        return userRepository.findById(id);
    }

    public void deletar(Long id) {
        userRepository.deleteById(id);
    }
    
    public boolean validarSenha(String email, String senha) {
        Optional<User> user = userRepository.findByUsername(email);
        return user.isPresent() && user.get().getSenha().equals(senha);
    }

    public User atualizar(Long id, User userAtt){
        return userRepository.findById(id).map(userEncontrado -> {
            userEncontrado.setNome(userAtt.getNome());
            userEncontrado.setUsername(userAtt.getUsername());
            if (userAtt.getSenha() != null && !userAtt.getSenha().isEmpty()){
                userEncontrado.setSenha(userAtt.getSenha());
            }
            return userRepository.save(userEncontrado);
        })
        .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }
}
