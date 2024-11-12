package study111.commerce.security;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;

import study111.commerce.domain.User;
import study111.commerce.repository.UserRepository;
import study111.commerce.response.CommonResponse;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class JwtAuthenticationIntegrationTests {

    @LocalServerPort
    int port;

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @DisplayName("토큰을 발급받는다")
    @Test
    void getToken() {
        var user = User.builder().username("user").password(passwordEncoder.encode("pass")).build();
        userRepository.save(user);

        var responseEntity = restTemplate.postForEntity(
                "http://localhost:{port}/auth/token?username={user}&password={pass}",
                null,
                CommonResponse.class,
                port, "user", "pass");

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();
    }
}
