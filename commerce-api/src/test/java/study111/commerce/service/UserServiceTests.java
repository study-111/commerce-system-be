package study111.commerce.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import study111.commerce.domain.UserStub;
import study111.commerce.repository.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserServiceTests {

    UserService userService;
    UserRepository userRepository = mock(UserRepository.class);

    @BeforeEach
    void beforeEach() {
        userService = new UserService(userRepository);
    }

    @Test
    void join_case01() {
        // given
        var command = new UserJoinCommand();
        command.setUsername("user");
        command.setPassword("pass");

        var entity = command.toEntity();
        when(userRepository.save(entity)).thenReturn(
            UserStub.of(1L, command.getUsername(), command.getPassword())
        );

        // when
        var join = userService.join(command);

        // then
        assertThat(join).isEqualTo(1L);
    }
}
