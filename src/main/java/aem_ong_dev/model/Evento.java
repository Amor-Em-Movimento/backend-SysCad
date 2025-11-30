package aem_ong_dev.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

import aem_ong_dev.model.User.User;

@Entity
@Table(name = "tb_eventos")
@Data
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;
    
    @Column(nullable = false)
    private LocalDate dataRealizacao;

    @ManyToMany
    @JoinTable(
        name = "evento_equipe",
        joinColumns = @JoinColumn(name = "evento_id"),
        inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    private List<User> equipe;

    @ManyToMany
    @JoinTable(
        name = "evento_familias",
        joinColumns = @JoinColumn(name = "evento_id"),
        inverseJoinColumns = @JoinColumn(name = "familia_id")
    )
    private List<Familia> atendidos;

    @ManyToMany
    @JoinTable(
        name = "evento_itens",
        joinColumns = @JoinColumn(name = "evento_id"),
        inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private List<ItemEstoque> itensUtilizados;
} 
