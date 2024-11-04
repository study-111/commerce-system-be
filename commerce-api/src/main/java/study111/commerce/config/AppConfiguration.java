package study111.commerce.config;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import study111.commerce.service.UserJoinCommand;
import study111.commerce.service.UserService;

@Configuration
public class AppConfiguration {

    @Bean
    ApplicationRunner run(UserService userService, PasswordEncoder passwordEncoder) {
        UserJoinCommand command = new UserJoinCommand();
        command.setUsername("user");
        command.setPassword("pass");

        return args -> {
            userService.join(command);
        };
    }
}
