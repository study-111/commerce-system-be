package study111.commerce.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import study111.commerce.domain.User;

import static org.assertj.core.api.Assertions.assertThat;

class UserRepositoryTests extends AbstractDataJpaTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void save_case01() {
        // given
        var user = User.builder().username("user").password("pass").build();

        // when
        var savedId = userRepository.save(user);

        // then
        assertThat(savedId).isNotNull();
    }
}
