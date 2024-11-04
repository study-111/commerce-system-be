package study111.commerce.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import study111.commerce.domain.User;

import javax.persistence.EntityManager;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class UserRepository {

    private final EntityManager em;

    public Long save(User user) {
        em.persist(user);

        return user.getId();
    }

    public Optional<User> findByUsername(String username) {
        var find = em.createQuery("select u from User u where u.username = :username", User.class)
            .setParameter("username", username)
            .getSingleResult();

        return Optional.ofNullable(find);
    }
}
