package study111.commerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study111.commerce.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
}
