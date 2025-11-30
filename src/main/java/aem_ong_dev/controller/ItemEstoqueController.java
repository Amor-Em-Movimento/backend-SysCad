package aem_ong_dev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import aem_ong_dev.model.ItemEstoque;
import aem_ong_dev.service.ItemEstoqueService;

@RestController
@RequestMapping("/estoque")
public class ItemEstoqueController {
    @Autowired
    private ItemEstoqueService estoqueService;

    // Endpoint 1 - [GET] Listar Estoque
    @GetMapping
    public List<ItemEstoque> listar() {
        return estoqueService.listarEstoque();
    }

    // Endpoint 2 - [POST] Cadastrar Estoque
    @PostMapping
    public ItemEstoque novoItem(@RequestBody ItemEstoque item) {
        return estoqueService.salvarItem(item);
    }
    
    // Endpoint 3 - [POST] Atualizar Estoque
    @PostMapping("/{id}/atualizar")
    public ResponseEntity<ItemEstoque> atualizarEstoque(
            @PathVariable Long id, 
            @RequestParam int qtd, 
            @RequestParam String tipo) {
        
        boolean isEntrada = tipo.equalsIgnoreCase("entrada");
        return ResponseEntity.ok(estoqueService.atualizarQuantidade(id, qtd, isEntrada));
    }
}
