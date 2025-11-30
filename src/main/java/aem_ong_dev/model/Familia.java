package aem_ong_dev.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tb_familia")
@Data
public class Familia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeResponsavel;
    private String cpf;
    private LocalDate dataNascimento;
    private String endereco;
    private String telefone;
    private Integer qntdBeneficiarios;
    
    @ManyToOne
    @JoinColumn(name = "usuario_cadastro_id")
    private User cadastradoPor;

    @ManyToMany(mappedBy = "atendidos")
    private List<Evento> historicoAtendimentos;
}
