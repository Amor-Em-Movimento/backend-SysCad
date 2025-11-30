package aem_ong_dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import aem_ong_dev.model.User.StatusUser;
import aem_ong_dev.model.User.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String email);
    Optional<User> findByIdAndStatus(Long id, StatusUser status);
    List<User> findByStatus(StatusUser status);
    List<User> findByStatusAndNomeContaining(User status, String nome);
}
