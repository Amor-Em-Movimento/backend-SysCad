package aem_ong_dev.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aem_ong_dev.model.ItemEstoque;
import aem_ong_dev.repository.ItemEstoqueRepository;

@Service
public class ItemEstoqueService {
    @Autowired
    private ItemEstoqueRepository estoqueRepository;

    public List<ItemEstoque> listarEstoque() {
        return estoqueRepository.findAll();
    }

    public ItemEstoque salvarItem(ItemEstoque item) {
        return estoqueRepository.save(item);
    }

    public ItemEstoque atualizarQuantidade(Long id, int quantidade, boolean isEntrada) {
        ItemEstoque item = estoqueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item não encontrado"));
        
        if (isEntrada) {
            item.setQuantidadeAtual(item.getQuantidadeAtual() + quantidade);
        } else {
            if (item.getQuantidadeAtual() < quantidade) {
                throw new RuntimeException("Estoque insuficiente!");
            }
            item.setQuantidadeAtual(item.getQuantidadeAtual() - quantidade);
        }
        return estoqueRepository.save(item);
    }
}
