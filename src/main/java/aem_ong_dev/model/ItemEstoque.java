package aem_ong_dev.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tb_estoque")
@Data
public class ItemEstoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nomeItem; 

    private String categoria;
    
    private Integer quantidadeAtual;

    private String unidadeMedida; 
}
