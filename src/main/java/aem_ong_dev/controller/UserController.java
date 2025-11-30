package aem_ong_dev.controller;

import org.springframework.stereotype.Controller;

import aem_ong_dev.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import aem_ong_dev.model.User;

import java.util.List;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*") // Permite o acesse à API, pelo Frontend
public class UserController {
    @Autowired
    private UserService userService;

    // Endpoint 1 - [GET] Listar todos Usuarios
    @GetMapping
    public ResponseEntity<List<User>> listar() {
        return ResponseEntity.ok(userService.listarTodos());
    }

    // Endpoint 2 -[POST] Criar novo usuário 
    @PostMapping
    public ResponseEntity<User> criar(@RequestBody User usuario) {
        try {
            User novoUsuario = userService.salvar(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Endpoint 3 - [GET] Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<User> buscarPorId(@PathVariable Long id) {
        return userService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Endpoint 4 - [DELETE] Remover usuário
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        userService.deletar(id);
        return ResponseEntity.noContent().build();
    }
    
    // Endpoint 5 [POST] Login 
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User loginData) {
        if(userService.validarSenha(loginData.getUsername(), loginData.getSenha())) {
            return ResponseEntity.ok("Login realizado com sucesso");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
    }

    // Endpoint 6 [PUT] Atualiza Usuario
    @PutMapping("/{id}")
    public ResponseEntity<User> atualizar(@PathVariable Long id, @RequestBody User user) {
        try{
            User savedUser = userService.atualizar(id, user);
            return ResponseEntity.status(HttpStatus.OK).body(savedUser);
        }catch (RuntimeException e){
            return ResponseEntity.notFound().build();

        }
    }
}

