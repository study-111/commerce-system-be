package study111.commerce.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import study111.commerce.domain.UserStub;
import study111.commerce.repository.UserRepository;
import study111.commerce.request.UserJoinRequest;

class UserServiceTests {

    UserService userService;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    UserRepository userRepository = mock(UserRepository.class);

    @BeforeEach
    void beforeEach() {
        userService = new UserService(passwordEncoder, userRepository);
    }

    @Test
    void join_case01() {
        // given
        var command = new UserJoinRequest();
        command.setUsername("user");
        command.setPassword("pass");

        var entity = command.toEntity(passwordEncoder::encode);
        // 시나리오: entity 값을 전달받으면 UserStub.of(1L, command.getUsername(),
        // command.getPassword()) 반환된다.
        when(userRepository.save(entity)).thenReturn(
                // DB 자동 ID 할당 부분
                UserStub.of(1L, command.getUsername(), command.getPassword()));

        // when
        var join = userService.join(command);

        // then
        assertThat(join).isEqualTo(1L);
    }
}
