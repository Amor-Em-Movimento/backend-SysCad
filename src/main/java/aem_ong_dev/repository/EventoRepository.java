package aem_ong_dev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import aem_ong_dev.model.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long>{
    List<Evento> findByDataRealizacao(int ano);
}
