package aem_ong_dev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import aem_ong_dev.model.Familia;
import aem_ong_dev.service.FamiliaService;

import java.util.List;

@RestController
@RequestMapping("/familias")
@CrossOrigin(origins = "*") 
public class FamiliaController {

    @Autowired
    private FamiliaService familiaService;

    // Endpoint 1- [GET] Listar todas as famílias
    @GetMapping
    public ResponseEntity<List<Familia>> listar() {
        return ResponseEntity.ok(familiaService.listarTodas());
    }

    // Endpoint 2- [GET] Buscar família específica por ID
    @GetMapping("/{id}")
    public ResponseEntity<Familia> buscarPorId(@PathVariable Long id) {
        return familiaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Endpoint 3- [POST] Cadastrar nova família
    @PostMapping
    public ResponseEntity<Familia> criar(@RequestBody Familia familia) {
        try {
            Familia novaFamilia = familiaService.salvar(familia);
            return ResponseEntity.status(HttpStatus.CREATED).body(novaFamilia);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    // Endpoint 4- [PUT] Atualizar dados da família
    @PutMapping("/{id}")
    public ResponseEntity<Familia> atualizar(@PathVariable Long id, @RequestBody Familia familia) {
        try {
            Familia familiaAtualizada = familiaService.atualizar(id, familia);
            return ResponseEntity.ok(familiaAtualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint 5- [DELETE] Remover família do cadastro
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            familiaService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}