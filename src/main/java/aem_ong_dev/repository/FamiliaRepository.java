package aem_ong_dev.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import aem_ong_dev.model.Familia;

public interface FamiliaRepository extends JpaRepository<Familia, Long>{
    Optional<Familia> findByCpf(String cpf);
}
