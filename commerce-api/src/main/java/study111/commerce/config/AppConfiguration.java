package study111.commerce.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import study111.commerce.domain.User;
import study111.commerce.service.UserService;

@Configuration
public class AppConfiguration {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner run(UserService userService) {
        return args -> {
            userService.save(User.of("user", passwordEncoder.encode("pass")));
        };
    }
}
