package study111.commerce.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import study111.commerce.domain.User;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserRepositoryTests {

    @Autowired
    UserRepository userRepository;

    @Test
    void save_case01() {
        // given
        var user = User.of("user", "pass");

        // when
        var savedId = userRepository.save(user);

        // then
        assertThat(savedId).isNotNull();
    }
}
